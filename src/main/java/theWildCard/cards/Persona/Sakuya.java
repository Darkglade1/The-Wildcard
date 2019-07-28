package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaSakuyaPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Sakuya extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Sakuya.class.getSimpleName());
    public static final String IMG = makeCardPath("Sakuya.png");

    public static final int WEAK_POWER = 1;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final ArcanaEnums.Arcana ARCANA = ArcanaEnums.Arcana.PRIESTESS;

    private static final int COST = 0;

    public Sakuya() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ARCANA, new PersonaSakuyaPower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = WEAK_POWER;
    }
}
