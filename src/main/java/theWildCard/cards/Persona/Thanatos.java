package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;
import theWildCard.powers.PersonaThanatosPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.DefaultMod.makeCardPath;

public class Thanatos extends AbstractPersonaCard {

    public static final String ID = DefaultMod.makeID(Thanatos.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 0;

    public Thanatos() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        cardArcana = ArcanaEnums.Arcana.DEATH;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new PersonaThanatosPower(p, p), 0));
        changePersona(PersonaThanatosPower.POWER_ID);
//        System.out.println(AbstractDungeon.commonCardPool);
//        System.out.println(AbstractDungeon.srcCommonCardPool);
//        System.out.println(AbstractDungeon.uncommonCardPool);
//        System.out.println(AbstractDungeon.srcUncommonCardPool);
//        System.out.println(AbstractDungeon.rareCardPool);
//        System.out.println(AbstractDungeon.srcRareCardPool);
    }
}
