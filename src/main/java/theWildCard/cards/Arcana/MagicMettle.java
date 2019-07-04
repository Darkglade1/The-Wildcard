package theWildCard.cards.Arcana;

import theWildCard.DefaultMod;
import theWildCard.cards.Skill.Uncommon.Execute;
import theWildCard.cards.Skill.Uncommon.Fortification;
import theWildCard.cards.Skill.Uncommon.MassOffering;
import theWildCard.cards.Skill.Uncommon.OnTheRoad;
import theWildCard.cards.Skill.Uncommon.Retribution;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class MagicMettle extends AbstractArcanaCard {


    public static final String ID = DefaultMod.makeID(MagicMettle.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SeveringSlash.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public MagicMettle() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new MassOffering();
        emperorCard = new Fortification();
        foolCard = new OnTheRoad();
        judgementCard = new Retribution();
        deathCard = new Execute();
    }
}
