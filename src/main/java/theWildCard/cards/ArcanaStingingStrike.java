package theWildCard.cards;

import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class ArcanaStingingStrike extends AbstractArcanaCard {


    public static final String ID = DefaultMod.makeID(ArcanaStingingStrike.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("ArcanaSeveringSlash.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public ArcanaStingingStrike() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new AttackCommonWitchsStrike();
        emperorCard = new AttackCommonBristle();
        foolCard = new AttackCommonEbbAndFlow();
        judgementCard = new AttackCommonFlurryOfBlows();
        deathCard = new AttackCommonCrush();
    }
}