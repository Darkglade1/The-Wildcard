package theWildCard.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theWildCard.WildcardMod;
import theWildCard.cards.Persona.Sakuya;
import theWildCard.tags.Tags;
import theWildCard.util.TextureLoader;

import java.util.Iterator;

import static theWildCard.WildcardMod.makePowerPath;


public class PersonaSakuyaPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = WildcardMod.makeID("PersonaSakuyaPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final int WEAK = Sakuya.WEAK_POWER;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("SakuyaPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("SakuyaPower32.png"));

    public PersonaSakuyaPower(final AbstractCreature owner, final AbstractCreature source) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        description = DESCRIPTIONS[0] + WEAK + DESCRIPTIONS[1];
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card) {
        if (!card.hasTag(Tags.PERSONA)) {
            this.flash();
            Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while(iterator.hasNext()) {
                AbstractMonster mo = (AbstractMonster)iterator.next();
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, owner, new WeakPower(mo, WEAK, false), WEAK, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }
}
