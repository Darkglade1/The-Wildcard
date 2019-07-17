package theWildCard.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import theWildCard.WildcardMod;
import theWildCard.util.TextureLoader;

import java.util.Iterator;

import static theWildCard.WildcardMod.makePowerPath;


public class PersonaMetatronPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = WildcardMod.makeID("PersonaMetatronPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("MetatronPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("MetatronPower32.png"));

    private static boolean isValid = false;
    private static AbstractCard usedCard;

    public PersonaMetatronPower(final AbstractCreature owner, final AbstractCreature source) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        description = DESCRIPTIONS[0];
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card.type == AbstractCard.CardType.ATTACK) {
            if (card.target == AbstractCard.CardTarget.ENEMY || card.target == AbstractCard.CardTarget.SELF_AND_ENEMY) {
                isValid = true;
                usedCard = card;
            }
        }
    }

    @Override
    public void onAttack(DamageInfo info, int damageAmount, AbstractCreature target) {
        AbstractPlayer p = AbstractDungeon.player;
        if (isValid && usedCard != null && info.owner == p) {
            Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while (iterator.hasNext()) {
                AbstractMonster mo = (AbstractMonster) iterator.next();
                if (target != mo && !mo.isDeadOrEscaped()) {
                    AbstractDungeon.actionManager.addToTop(new SFXAction("ATTACK_HEAVY"));
                    AbstractDungeon.actionManager.addToTop(new VFXAction(p, new CleaveEffect(), 0.1F));
                    int multiplier = damageAmount / usedCard.damage; //Accounts for instances where the card modifies the damage in the use method so that it differs from its damage value
                    usedCard.calculateCardDamage(mo); //Allows Metatron to adjust damage based on if Vulnerable/other effects are on the other monsters
                    int newDamage = usedCard.damage * multiplier;
                    //sets damageInfo's owner to mo so the damage method doesn't call this onAttack and create an infinite loop
                    //Prefer this over null to ensure that certain monster powers still work (like the bug's Curl Up)
                    DamageInfo damageInfo = new DamageInfo(mo, newDamage, info.type);
                    mo.damage(damageInfo);
                }
            }
        }
    }

    @Override
    public void onAfterUseCard(AbstractCard card, UseCardAction action) {
        if (isValid) {
            isValid = false;
            usedCard = null;
        }
    }
}
