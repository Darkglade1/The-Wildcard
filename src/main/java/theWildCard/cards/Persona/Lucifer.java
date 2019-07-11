package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaLuciferPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Lucifer extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Lucifer.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 0;

    public Lucifer() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ArcanaEnums.Arcana.JUDGEMENT, new PersonaLuciferPower(AbstractDungeon.player, AbstractDungeon.player));
    }
}
