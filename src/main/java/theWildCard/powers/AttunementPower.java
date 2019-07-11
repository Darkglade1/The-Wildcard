package theWildCard.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theWildCard.WildcardMod;
import theWildCard.cards.Persona.AbstractPersonaCard;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makePowerPath;


public class AttunementPower extends AbstractPower {

    public static final String POWER_ID = WildcardMod.makeID("AttunementPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public AttunementPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power.ID.equals(AbstractPersonaCard.getActivePersona())) {
            this.flash();
            AbstractPlayer p = AbstractDungeon.player;
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                    new StrengthPower(p, amount), amount));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                    new DexterityPower(p, amount), amount));
        }
    }

//    @Override
//    public void onAfterCardPlayed(AbstractCard card) {
//        if (card instanceof AbstractPersonaCard) {
//            if (((AbstractPersonaCard)card).cardID.equals(AbstractPersonaCard.getActivePersona())) {
//
//            }
//        }
//        this.flash();
//        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, amount));
//    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1] + amount + DESCRIPTIONS[2];
    }
}
