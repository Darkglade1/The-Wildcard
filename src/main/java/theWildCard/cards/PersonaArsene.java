package theWildCard.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;
import theWildCard.powers.PersonaArsenePower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.DefaultMod.makeCardPath;

public class PersonaArsene extends AbstractPersonaCard {

    public static final String ID = DefaultMod.makeID(PersonaArsene.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public int defaultSecondMagicNumber;        // Just like magic number, or any number for that matter, we want our regular, modifiable stat
    public int defaultBaseSecondMagicNumber;    // And our base stat - the number in it's base state. It will reset to that by default.

    public static final int STRENGTH = 1;
    public static final int DEXTERITY = 1;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 0;


    public PersonaArsene() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = STRENGTH;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DEXTERITY;
        cardArcana = ArcanaEnums.Arcana.FOOL;
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new StrengthPower(p, magicNumber), magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new DexterityPower(p, defaultSecondMagicNumber), defaultSecondMagicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new PersonaArsenePower(p, p), 0));
        activePersona = PersonaArsenePower.POWER_ID;
    }
}
