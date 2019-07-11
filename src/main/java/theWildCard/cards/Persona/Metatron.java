package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaMetatronPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Metatron extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Metatron.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 0;

    public Metatron() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ArcanaEnums.Arcana.JUDGEMENT, new PersonaMetatronPower(AbstractDungeon.player, AbstractDungeon.player));
    }
}
