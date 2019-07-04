package theWildCard.cards.Arcana;

import theWildCard.DefaultMod;
import theWildCard.cards.Power.Uncommon.BalancingAct;
import theWildCard.cards.Power.Uncommon.RegalBearing;
import theWildCard.cards.Power.Uncommon.RighteousFury;
import theWildCard.cards.Power.Uncommon.SubduingPresence;
import theWildCard.cards.Power.Uncommon.VileAura;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class PowerOfTheArcana extends AbstractArcanaCard {


    public static final String ID = DefaultMod.makeID(PowerOfTheArcana.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SeveringSlash.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;
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
