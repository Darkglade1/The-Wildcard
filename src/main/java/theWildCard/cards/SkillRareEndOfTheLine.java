package theWildCard.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DiscardAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.NoDrawPower;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class SkillRareEndOfTheLine extends AbstractDefaultCard {

    public static final String ID = DefaultMod.makeID(SkillRareEndOfTheLine.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SkillRareEndOfTheLine.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 1;
    private static final int UPGRADED_COST = 0;

    private static final int DRAW = 10;

    public SkillRareEndOfTheLine() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = DRAW;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DiscardAction(p, p, p.hand.group.size(), true));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new NoDrawPower(p)));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            initializeDescription();
        }
    }
}
