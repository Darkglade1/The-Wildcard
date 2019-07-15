package theWildCard.cards.Arcana;

import theWildCard.WildcardMod;
import theWildCard.cards.Power.Uncommon.BalancingAct;
import theWildCard.cards.Power.Uncommon.RegalBearing;
import theWildCard.cards.Power.Uncommon.RighteousFury;
import theWildCard.cards.Power.Uncommon.SubduingPresence;
import theWildCard.cards.Power.Uncommon.VileAura;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class PowerOfTheArcana extends AbstractArcanaCard {


    public static final String ID = WildcardMod.makeID(PowerOfTheArcana.class.getSimpleName());
    public static final String IMG = makeCardPath("ArcanaPower.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public PowerOfTheArcana() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new SubduingPresence();
        emperorCard = new RegalBearing();
        foolCard = new BalancingAct();
        judgementCard = new RighteousFury();
        deathCard = new VileAura();
    }
}
