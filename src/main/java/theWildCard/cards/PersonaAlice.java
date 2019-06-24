package theWildCard.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;
import theWildCard.powers.PersonaAlicePower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.DefaultMod.makeCardPath;

public class PersonaAlice extends AbstractPersonaCard {

    public static final String ID = DefaultMod.makeID(PersonaAlice.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final int ENERGYGAIN = 2;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 0;

    public PersonaAlice() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = ENERGYGAIN;
        cardArcana = ArcanaEnums.Arcana.DEATH;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new PersonaAlicePower(p, p, ENERGYGAIN), 0));
        changePersona(PersonaAlicePower.POWER_ID);
    }
}
