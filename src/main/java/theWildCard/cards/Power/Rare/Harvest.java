package theWildCard.cards.Power.Rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.WildcardMod;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.HarvestPower;

import static theWildCard.WildcardMod.makeCardPath;

public class Harvest extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(Harvest.class.getSimpleName());
    public static final String IMG = makeCardPath("DeathPower.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    private static final int ENERGY = 1;

    public Harvest() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = ENERGY;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new HarvestPower(p, magicNumber), magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
