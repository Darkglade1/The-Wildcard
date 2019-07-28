package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaLokiPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Loki extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Loki.class.getSimpleName());
    public static final String IMG = makeCardPath("Loki.png");

    public static final int DRAW_POWER = 1;
    public static final int DRAW_COUNTER = 2;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final ArcanaEnums.Arcana ARCANA = ArcanaEnums.Arcana.FOOL;

    private static final int COST = 0;

    public Loki() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ARCANA, new PersonaLokiPower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = DRAW_POWER;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DRAW_COUNTER;
    }
}
