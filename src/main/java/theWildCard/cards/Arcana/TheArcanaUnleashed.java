package theWildCard.cards.Arcana;

import theWildCard.DefaultMod;
import theWildCard.cards.Power.Rare.Bulwark;
import theWildCard.cards.Power.Rare.Encore;
import theWildCard.cards.Power.Rare.Harvest;
import theWildCard.cards.Power.Rare.Momentum;
import theWildCard.cards.Power.Rare.Reincarnation;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class TheArcanaUnleashed extends AbstractArcanaCard {

    public static final String ID = DefaultMod.makeID(TheArcanaUnleashed.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SeveringSlash.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public TheArcanaUnleashed() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new Reincarnation();
        emperorCard = new Bulwark();
        foolCard = new Encore();
        judgementCard = new Momentum();
        deathCard = new Harvest();
    }
}
