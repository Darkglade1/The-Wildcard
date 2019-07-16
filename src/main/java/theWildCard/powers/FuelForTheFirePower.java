package theWildCard.powers;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWildCard.WildcardMod;
import theWildCard.cards.OnDiscardArcanaPower;
import theWildCard.cards.Persona.AbstractPersonaCard;


public class FuelForTheFirePower extends AbstractPower implements OnDiscardArcanaPower {

    public static final String POWER_ID = WildcardMod.makeID("FuelForTheFirePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FuelForTheFirePower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.loadRegion("flameBarrier");

        updateDescription();
    }

    @Override
    public void onExhaust(AbstractCard card) {
        if (card instanceof  AbstractPersonaCard) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, amount));
        }
    }

    @Override
    public void onDiscardArcana() {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, amount));
    }

    @Override
    public void updateDescription() {
        if (amount == 1) {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[2];
        }
    }
}
