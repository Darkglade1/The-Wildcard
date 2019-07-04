package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;
import theWildCard.powers.PersonaCaesarPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.DefaultMod.makeCardPath;

public class Caesar extends AbstractPersonaCard {

    public static final String ID = DefaultMod.makeID(Caesar.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final int INITIAL_ARTIFACT = 1;
    public static final int ARTIFACT = 2;

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 0;

    public Caesar() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = ARTIFACT;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = INITIAL_ARTIFACT;
        cardArcana = ArcanaEnums.Arcana.EMPEROR;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new ArtifactPower(p, INITIAL_ARTIFACT), INITIAL_ARTIFACT));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new PersonaCaesarPower(p, p, ARTIFACT), 0));
        changePersona(PersonaCaesarPower.POWER_ID);
    }
}
