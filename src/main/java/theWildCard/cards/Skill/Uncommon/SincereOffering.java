package theWildCard.cards.Skill.Uncommon;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.actions.common.ExhaustAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.WildcardMod;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.characters.WildcardCharacter;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theWildCard.WildcardMod.makeCardPath;

public class SincereOffering extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(SincereOffering.class.getSimpleName());
    public static final String IMG = makeCardPath("PriestessSkill.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 1;

    private static final int HEALING = 3;
    private static final int UPGRADE_PLUS_HEALING = 1;
    private static final int DRAW = 1;

    public SincereOffering() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = HEALING;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DRAW;
        tags.add(CardTags.HEALING);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (this.upgraded) {
            AbstractDungeon.actionManager.addToBottom(new ExhaustAction( 1, false, false));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ExhaustAction( 1, true, false));
        }
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, magicNumber));
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, defaultSecondMagicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_HEALING);
            rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
