package theWildCard.cards.Arcana;

import theWildCard.WildcardMod;
import theWildCard.cards.Skill.Uncommon.RampantBloodlust;
import theWildCard.cards.Skill.Uncommon.SincereOffering;
import theWildCard.cards.Skill.Uncommon.SleightOfHand;
import theWildCard.cards.Skill.Uncommon.SpikyShield;
import theWildCard.cards.Skill.Uncommon.Vengeance;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class ArcanaArtistry extends AbstractArcanaCard {


    public static final String ID = WildcardMod.makeID(ArcanaArtistry.class.getSimpleName());
    public static final String IMG = makeCardPath("ArcanaSkill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final int COST = -1;

    public ArcanaArtistry() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        priestessCard = new SincereOffering();
        emperorCard = new SpikyShield();
        foolCard = new SleightOfHand();
        judgementCard = new Vengeance();
        deathCard = new RampantBloodlust();
    }
}
