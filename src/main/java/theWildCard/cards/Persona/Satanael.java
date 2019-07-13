package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaSatanaelPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Satanael extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Satanael.class.getSimpleName());
    public static final String IMG = makeCardPath("Satanael.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 0;
    public static int RETAIN = 10;

    public Satanael() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ArcanaEnums.Arcana.FOOL, new PersonaSatanaelPower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = RETAIN;
    }
}
