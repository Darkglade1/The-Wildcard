package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaThanatosPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Thanatos extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Thanatos.class.getSimpleName());
    public static final String IMG = makeCardPath("Thanatos.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final ArcanaEnums.Arcana ARCANA = ArcanaEnums.Arcana.DEATH;

    private static final int COST = 0;
    public static final int HP_LOSS = 2;

    public Thanatos() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ARCANA, new PersonaThanatosPower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = HP_LOSS;
    }
}
