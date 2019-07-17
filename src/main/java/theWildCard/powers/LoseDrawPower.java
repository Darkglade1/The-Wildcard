package theWildCard.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWildCard.WildcardMod;


public class LoseDrawPower extends AbstractPower {

    public static final String POWER_ID = WildcardMod.makeID("LoseDrawPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public LoseDrawPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.DEBUFF;
        isTurnBased = false;

        //loads textures
        this.loadRegion("lessdraw");

        //caps the debuff at 5 since that's functionally how far it can go
        if (this.amount > 5) {
            this.amount = 5;
        }

        updateDescription();
    }

    @Override
    public void stackPower(int stackAmount) {
        super.stackPower(stackAmount);
        //caps the debuff at 5 since that's functionally how far it can go
        if (this.amount > 5) {
            this.amount = 5;
        }
    }

    @Override
    public void atStartOfTurn() {
        AbstractDungeon.player.gameHandSize -= amount;
    }

    @Override
    public void atStartOfTurnPostDraw () {
        this.flash();
        AbstractDungeon.player.gameHandSize += amount;
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
