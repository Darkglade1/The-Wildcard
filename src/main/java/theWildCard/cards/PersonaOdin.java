package theWildCard.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;
import theWildCard.powers.PersonaOdinPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.DefaultMod.makeCardPath;

public class PersonaOdin extends AbstractPersonaCard {

    public static final String ID = DefaultMod.makeID(PersonaOdin.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final int BLOCK = 10;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 0;

    public PersonaOdin() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = BLOCK;
        cardArcana = ArcanaEnums.Arcana.EMPEROR;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new PersonaOdinPower(p, p), 0));
        changePersona(PersonaOdinPower.POWER_ID);
    }
}
