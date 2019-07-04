package theWildCard.cards.Arcana;

import theWildCard.DefaultMod;
import theWildCard.cards.Skill.Common.BurstOfStrength;
import theWildCard.cards.Skill.Common.DebilitatingSpell;
import theWildCard.cards.Skill.Common.Fortify;
import theWildCard.cards.Skill.Common.PiercingGaze;
import theWildCard.cards.Skill.Common.TwoForOne;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class ArcaneArts extends AbstractArcanaCard {


    public static final String ID = DefaultMod.makeID(ArcaneArts.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SeveringSlash.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public ArcaneArts() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new DebilitatingSpell();
        emperorCard = new Fortify();
        foolCard = new TwoForOne();
        judgementCard = new BurstOfStrength();
        deathCard = new PiercingGaze();
    }
}
