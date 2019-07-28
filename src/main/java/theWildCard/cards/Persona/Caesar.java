package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaCaesarPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Caesar extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Caesar.class.getSimpleName());
    public static final String IMG = makeCardPath("Caesar.png");

    public static final int ARTIFACT_COUNTER = 2;
    public static final int ARTIFACT = 1;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final ArcanaEnums.Arcana ARCANA = ArcanaEnums.Arcana.EMPEROR;

    private static final int COST = 0;

    public Caesar() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ARCANA, new PersonaCaesarPower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = ARTIFACT;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = ARTIFACT_COUNTER;
    }
}
