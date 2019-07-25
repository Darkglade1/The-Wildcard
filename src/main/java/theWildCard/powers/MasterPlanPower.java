package theWildCard.powers;

import com.megacrit.cardcrawl.actions.common.GainEnergyAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.unique.RetainCardsAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWildCard.WildcardMod;
import theWildCard.cards.Skill.Uncommon.MasterPlan;


public class MasterPlanPower extends AbstractPower {

    public static final String POWER_ID = WildcardMod.makeID("MasterPlanPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static int RETAIN_POWER = MasterPlan.RETAIN;

    public MasterPlanPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.loadRegion("ai");

        updateDescription();
    }

    // Update the description when you apply this power.
    @Override
    public void updateDescription() {
        if (amount == 0) {
            description = DESCRIPTIONS[2] + RETAIN_POWER + DESCRIPTIONS[3];
        } else {
            description = DESCRIPTIONS[0];
            for (int i = 0; i < amount; i++) {
                description += " [E]";
            }
            description += DESCRIPTIONS[1] + DESCRIPTIONS[2] + RETAIN_POWER + DESCRIPTIONS[3];
        }
    }

    @Override
    public void atStartOfTurnPostDraw () {
        this.flash();
        AbstractDungeon.actionManager.addToBottom(new GainEnergyAction(amount));
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(owner, owner, this.ID));
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (isPlayer && !AbstractDungeon.player.hand.isEmpty() && !AbstractDungeon.player.hasRelic("Runic Pyramid") && !AbstractDungeon.player.hasPower("Equilibrium")) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RetainCardsAction(owner, RETAIN_POWER));
        }
    }
}
