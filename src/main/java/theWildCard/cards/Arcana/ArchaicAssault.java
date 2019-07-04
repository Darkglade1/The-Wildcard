package theWildCard.cards.Arcana;

import theWildCard.WildcardMod;
import theWildCard.cards.Attack.Uncommon.AncientSeal;
import theWildCard.cards.Attack.Uncommon.CleansingBlow;
import theWildCard.cards.Attack.Uncommon.DivineWhirl;
import theWildCard.cards.Attack.Uncommon.JackOfAllTrades;
import theWildCard.cards.Attack.Uncommon.SuddenDeath;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class ArchaicAssault extends AbstractArcanaCard {


    public static final String ID = WildcardMod.makeID(ArchaicAssault.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SeveringSlash.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public ArchaicAssault() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new AncientSeal();
        emperorCard = new CleansingBlow();
        foolCard = new JackOfAllTrades();
        judgementCard = new DivineWhirl();
        deathCard = new SuddenDeath();
    }
}
