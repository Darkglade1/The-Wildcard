package theWildCard.cards;

import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class ArcanaArchaicAssault extends AbstractArcanaCard {


    public static final String ID = DefaultMod.makeID(ArcanaArchaicAssault.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("ArcanaSeveringSlash.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public ArcanaArchaicAssault() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new AttackUncommonAncientSeal();
        emperorCard = new AttackUncommonCleansingBlow();
        foolCard = new AttackUncommonJackOfAllTrades();
        judgementCard = new AttackUncommonDivineWhirl();
        deathCard = new AttackUncommonSuddenDeath();
    }
}
