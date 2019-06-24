package theWildCard.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;
import theWildCard.powers.PersonaMetatronPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.DefaultMod.makeCardPath;

public class PersonaMetatron extends AbstractPersonaCard {

    public static final String ID = DefaultMod.makeID(PersonaMetatron.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");


    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 0;

    public PersonaMetatron() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        cardArcana = ArcanaEnums.Arcana.JUDGEMENT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new PersonaMetatronPower(p, p), 0));
        changePersona(PersonaMetatronPower.POWER_ID);
    }
}
