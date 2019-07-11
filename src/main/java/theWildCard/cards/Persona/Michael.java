package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaMichaelPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Michael extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Michael.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final int STRENGTH = 2;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 0;


    public Michael() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ArcanaEnums.Arcana.JUDGEMENT, new PersonaMichaelPower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = STRENGTH;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        if (AbstractPersonaCard.canChangePersona) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                    new StrengthPower(p, magicNumber), magicNumber));
        }
    }
}
