package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaArsenePower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Arsene extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Arsene.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final int STRENGTH = 1;
    public static final int DEXTERITY = 1;

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 0;


    public Arsene() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ArcanaEnums.Arcana.FOOL, new PersonaArsenePower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = STRENGTH;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DEXTERITY;
    }


    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        if (AbstractPersonaCard.canChangePersona) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                    new StrengthPower(p, magicNumber), magicNumber));
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                    new DexterityPower(p, defaultSecondMagicNumber), defaultSecondMagicNumber));
        }
    }
}
