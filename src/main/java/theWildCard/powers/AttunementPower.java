package theWildCard.powers;

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


public class AttunementPower extends AbstractPower {

    public static final String POWER_ID = WildcardMod.makeID("AttunementPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public AttunementPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.loadRegion("echo");

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
