package theWildCard.cards;

import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class ArcanaTheArcanaUnleashed extends AbstractArcanaCard {

    public static final String ID = DefaultMod.makeID(ArcanaTheArcanaUnleashed.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("ArcanaSeveringSlash.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public ArcanaTheArcanaUnleashed() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new PowerRareReincarnation();
        emperorCard = new PowerRareBulwark();
        foolCard = new PowerRareEncore();
        judgementCard = new PowerRareMomentum();
        deathCard = new PowerRareHarvest();
    }
}
