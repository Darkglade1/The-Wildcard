package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaAlicePower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Alice extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Alice.class.getSimpleName());
    public static final String IMG = makeCardPath("Alice.png");

    public static final int ENERGY_GAIN = 2;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 0;

    public Alice() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ArcanaEnums.Arcana.DEATH, new PersonaAlicePower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = ENERGY_GAIN;
    }
}
