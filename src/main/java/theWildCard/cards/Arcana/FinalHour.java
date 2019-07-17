package theWildCard.cards.Arcana;

import theWildCard.WildcardMod;
import theWildCard.cards.Skill.Rare.BlessingOfKings;
import theWildCard.cards.Skill.Rare.Countdown;
import theWildCard.cards.Skill.Rare.EndOfTheLine;
import theWildCard.cards.Skill.Rare.LastRites;
import theWildCard.cards.Skill.Rare.LastStand;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class FinalHour extends AbstractArcanaCard {


    public static final String ID = WildcardMod.makeID(FinalHour.class.getSimpleName());
    public static final String IMG = makeCardPath("FinalHour.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public FinalHour() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new LastRites();
        emperorCard = new BlessingOfKings();
        foolCard = new EndOfTheLine();
        judgementCard = new LastStand();
        deathCard = new Countdown(this);
    }
}
