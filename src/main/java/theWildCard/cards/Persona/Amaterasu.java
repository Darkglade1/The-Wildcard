package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaAmaterasuPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Amaterasu extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Amaterasu.class.getSimpleName());
    public static final String IMG = makeCardPath("Amaterasu.png");

    public static final int HEAL = 2;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 0;

    public Amaterasu() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ArcanaEnums.Arcana.PRIESTESS, new PersonaAmaterasuPower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = HEAL;
    }
}
