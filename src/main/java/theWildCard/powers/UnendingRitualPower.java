package theWildCard.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWildCard.WildcardMod;
import theWildCard.actions.BetterMakeTempCardInHandAction;
import theWildCard.cards.Arcana.AbstractArcanaCard;
import theWildCard.tags.Tags;

import static theWildCard.cards.Arcana.AbstractArcanaCard.returnTrulyRandomArcana;


public class UnendingRitualPower extends AbstractPower {

    public static final String POWER_ID = WildcardMod.makeID("UnendingRitualPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public UnendingRitualPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.loadRegion("evolve");

        updateDescription();
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(Tags.ARCANA)) {
            this.flash();
            for (int i = 0; i < amount; i++) {
                AbstractArcanaCard c = (AbstractArcanaCard)returnTrulyRandomArcana().makeCopy();
                c.changeArcana();
                AbstractDungeon.actionManager.addToBottom(new BetterMakeTempCardInHandAction(c, true));
            }
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, ID));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
