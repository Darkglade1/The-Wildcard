package theWildCard.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;
import theWildCard.powers.LastStandPower;

import static theWildCard.DefaultMod.makeCardPath;

public class SkillRareLastStand extends AbstractDefaultCard {

    public static final String ID = DefaultMod.makeID(SkillRareLastStand.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SkillRareLastStand.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 0;

    private static final int STRENGTH = 10;
    private static final int UPGRADE_PLUS_STRENGTH = 5;

    public SkillRareLastStand() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = STRENGTH;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new StrengthPower(p, magicNumber), magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new LastStandPower(p, p), 0));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_STRENGTH);
            initializeDescription();
        }
    }
}
