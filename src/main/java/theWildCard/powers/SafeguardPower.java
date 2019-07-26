package theWildCard.powers;

import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theWildCard.WildcardMod;
import theWildCard.cards.Arcana.AbstractArcanaCard;


public class SafeguardPower extends AbstractPower {

    public static final String POWER_ID = WildcardMod.makeID("SafeguardPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SafeguardPower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.loadRegion("armor");

        updateDescription();
    }

    @Override
    public void onPlayCard(AbstractCard card, AbstractMonster m) {
        if (card instanceof AbstractArcanaCard) {
            if (card.freeToPlayOnce) {
                return;
            }
            int cost;
            if (card.isCostModifiedForTurn) {
                cost = card.costForTurn;
            } else if (card.cost == -1){
                cost = EnergyPanel.totalCount;
            } else {
                cost = card.cost;
            }
            if (cost > 0) {
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new GainBlockAction(owner, owner, amount * cost));
            }
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
