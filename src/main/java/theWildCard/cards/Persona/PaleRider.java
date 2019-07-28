package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaPaleRiderPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class PaleRider extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(PaleRider.class.getSimpleName());
    public static final String IMG = makeCardPath("PaleRider.png");

    public static final int VULNERABLE_POWER = 1;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final ArcanaEnums.Arcana ARCANA = ArcanaEnums.Arcana.DEATH;

    private static final int COST = 0;

    public PaleRider() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ARCANA, new PersonaPaleRiderPower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = VULNERABLE_POWER;
    }
}
