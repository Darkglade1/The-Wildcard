package theWildCard.cards;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class SkillUncommonMassOffering extends AbstractDefaultCard {

    public static final String ID = DefaultMod.makeID(SkillUncommonMassOffering.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SkillUncommonMassOffering.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 2;

    private static final int HEAL = 2;
    private static final int UPGRADE_PLUS_HEAL = 1;

    private static final int DRAW = 3;
    private static final int UPGRADE_PLUS_DRAW = 1;

    public SkillUncommonMassOffering() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = HEAL;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DRAW;
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int count = AbstractDungeon.player.hand.size() - 1;  // -1 to prevent the card from counting itself
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, magicNumber * count));

        for(int i = 0; i < count; ++i) {
            AbstractDungeon.actionManager.addToTop(new ExhaustAction(AbstractDungeon.player, AbstractDungeon.player, 1, true, true));
        }
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, defaultSecondMagicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_HEAL);
            upgradeDefaultSecondMagicNumber(UPGRADE_PLUS_DRAW);
            initializeDescription();
        }
    }
}
