package theWildCard.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;
import theWildCard.powers.PersonaScathachPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.DefaultMod.makeCardPath;

public class PersonaScathach extends AbstractPersonaCard {

    public static final String ID = DefaultMod.makeID(PersonaScathach.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final int HEAL = 5;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 0;

    public PersonaScathach() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = HEAL;
        cardArcana = ArcanaEnums.Arcana.PRIESTESS;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new PersonaScathachPower(p, p, HEAL), 0));
        changePersona(PersonaScathachPower.POWER_ID);
    }
}
