package theWildCard.cards.Arcana;

import theWildCard.WildcardMod;
import theWildCard.cards.Attack.Common.BalancedThrust;
import theWildCard.cards.Attack.Common.HealersStrike;
import theWildCard.cards.Attack.Common.SavageBlow;
import theWildCard.cards.Attack.Common.SweepingSlash;
import theWildCard.cards.Attack.Common.WardingBlow;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class SeveringSlash extends AbstractArcanaCard {


    public static final String ID = WildcardMod.makeID(SeveringSlash.class.getSimpleName());
    public static final String IMG = makeCardPath("SeveringSlash.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public SeveringSlash() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new HealersStrike();
        emperorCard = new WardingBlow();
        foolCard = new BalancedThrust();
        judgementCard = new SweepingSlash();
        deathCard = new SavageBlow();
    }
}
