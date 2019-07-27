package theWildCard.cards.Skill.Rare;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theWildCard.WildcardMod;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.LastStandPower;

import static theWildCard.WildcardMod.makeCardPath;

public class LastStand extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(LastStand.class.getSimpleName());
    public static final String IMG = makeCardPath("JudgementSkill.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 0;

    private static final int STRENGTH = 5;
    private static final int UPGRADE_PLUS_STRENGTH = 2;

    private static final int HP_LOSS = 8;
    private static final int HP_LOSS_DECREASE = -2;

    public LastStand() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = STRENGTH;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = HP_LOSS;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new StrengthPower(p, magicNumber), magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new LastStandPower(p, p, defaultSecondMagicNumber), defaultSecondMagicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_STRENGTH);
            upgradeDefaultSecondMagicNumber(HP_LOSS_DECREASE);
            initializeDescription();
        }
    }
}
