package theWildCard.cards;

import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class ArcanaArcaneArts extends AbstractArcanaCard {


    public static final String ID = DefaultMod.makeID(ArcanaArcaneArts.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("ArcanaSeveringSlash.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public ArcanaArcaneArts() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new SkillCommonDebilitatingSpell();
        emperorCard = new SkillCommonFortify();
        foolCard = new SkillCommonTwoForOne();
        judgementCard = new SkillCommonBurstOfStrength();
        deathCard = new SkillCommonPiercingGaze();
    }
}
