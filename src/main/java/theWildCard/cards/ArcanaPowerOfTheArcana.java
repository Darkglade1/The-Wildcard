package theWildCard.cards;

import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class ArcanaPowerOfTheArcana extends AbstractArcanaCard {


    public static final String ID = DefaultMod.makeID(ArcanaPowerOfTheArcana.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("ArcanaSeveringSlash.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public ArcanaPowerOfTheArcana() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new PowerUncommonSubduingPresence();
        emperorCard = new PowerUncommonRegalBearing();
        foolCard = new PowerUncommonBalancingAct();
        judgementCard = new PowerUncommonRighteousFury();
        deathCard = new PowerUncommonVileAura();
    }
}
