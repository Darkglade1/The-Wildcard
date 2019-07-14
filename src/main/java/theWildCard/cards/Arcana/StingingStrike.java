package theWildCard.cards.Arcana;

import theWildCard.WildcardMod;
import theWildCard.cards.Attack.Common.Bristle;
import theWildCard.cards.Attack.Common.Crush;
import theWildCard.cards.Attack.Common.EbbAndFlow;
import theWildCard.cards.Attack.Common.FlurryOfBlows;
import theWildCard.cards.Attack.Common.WitchsStrike;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class StingingStrike extends AbstractArcanaCard {


    public static final String ID = WildcardMod.makeID(StingingStrike.class.getSimpleName());
    public static final String IMG = makeCardPath("ArcanaAttack.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public StingingStrike() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new WitchsStrike();
        emperorCard = new Bristle();
        foolCard = new EbbAndFlow();
        judgementCard = new FlurryOfBlows();
        deathCard = new Crush();
    }
}
