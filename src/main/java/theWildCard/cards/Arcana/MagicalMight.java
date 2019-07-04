package theWildCard.cards.Arcana;

import theWildCard.DefaultMod;
import theWildCard.cards.Attack.Uncommon.BrutalDemise;
import theWildCard.cards.Attack.Uncommon.OneTwo;
import theWildCard.cards.Attack.Uncommon.QuietPassing;
import theWildCard.cards.Attack.Uncommon.RelentlessBarrage;
import theWildCard.cards.Attack.Uncommon.ShieldCrash;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class MagicalMight extends AbstractArcanaCard {


    public static final String ID = DefaultMod.makeID(MagicalMight.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SeveringSlash.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public MagicalMight() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new QuietPassing();
        emperorCard = new ShieldCrash();
        foolCard = new OneTwo();
        judgementCard = new RelentlessBarrage();
        deathCard = new BrutalDemise();
    }
}
