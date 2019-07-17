package theWildCard.powers;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWildCard.WildcardMod;


public class CrushPower extends AbstractPower {

    public static final String POWER_ID = WildcardMod.makeID("CrushPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public CrushPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.loadRegion("energized_blue");

        updateDescription();
    }

    // Update the description when you apply this power.
    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0];
        for (int i = 0; i < amount; i++) {
            description += " [E]";
        }
        description += DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurnPostDraw () {
        this.flash();
        AbstractDungeon.player.gainEnergy(amount);
        AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
    }
}
