package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaOdinPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Odin extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Odin.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final int BLOCK = 10;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 0;

    public Odin() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ArcanaEnums.Arcana.EMPEROR, new PersonaOdinPower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = BLOCK;
    }
}
