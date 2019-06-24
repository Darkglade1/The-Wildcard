package theWildCard.cards;

import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class ArcanaMagicMettle extends AbstractArcanaCard {


    public static final String ID = DefaultMod.makeID(ArcanaMagicMettle.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("ArcanaSeveringSlash.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public ArcanaMagicMettle() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new SkillUncommonMassOffering();
        emperorCard = new SkillUncommonFortification();
        foolCard = new SkillUncommonOnTheRoad();
        judgementCard = new SkillUncommonRetribution();
        deathCard = new SkillUncommonExecute();
    }
}
