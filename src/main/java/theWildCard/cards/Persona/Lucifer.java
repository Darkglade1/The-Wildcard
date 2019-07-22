package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaLuciferPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Lucifer extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Lucifer.class.getSimpleName());
    public static final String IMG = makeCardPath("Lucifer.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final ArcanaEnums.Arcana ARCANA = ArcanaEnums.Arcana.JUDGEMENT;

    private static final int COST = 0;
    public static final int DEX_LOSS = 2;

    public Lucifer() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ARCANA, new PersonaLuciferPower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = DEX_LOSS;
    }
}
