package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaScathachPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Scathach extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Scathach.class.getSimpleName());
    public static final String IMG = makeCardPath("Scathach.png");

    public static final int HEAL = 1;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final ArcanaEnums.Arcana ARCANA = ArcanaEnums.Arcana.PRIESTESS;

    private static final int COST = 0;

    public Scathach() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ARCANA, new PersonaScathachPower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = HEAL;
    }
}
