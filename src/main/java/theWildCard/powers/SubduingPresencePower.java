package theWildCard.powers;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theWildCard.WildcardMod;

import java.util.Iterator;


public class SubduingPresencePower extends AbstractPower {

    public static final String POWER_ID = WildcardMod.makeID("SubduingPresencePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SubduingPresencePower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.loadRegion("wraithForm");

        updateDescription();
    }

    @Override
    public void atStartOfTurn () {
        this.flash();
        AbstractPlayer p = AbstractDungeon.player;
        Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(iterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)iterator.next();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, amount, false), amount, true, AbstractGameAction.AttackEffect.NONE));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
