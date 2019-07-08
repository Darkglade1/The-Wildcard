package theWildCard.cards.Skill.Uncommon;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.WildcardMod;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.UnendingRitualPower;
import theWildCard.powers.UpgradedUnendingRitualPower;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theWildCard.WildcardMod.makeCardPath;

public class UnendingRitual extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(UnendingRitual.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("UnendingRitual.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 1;
    private static final int CARDS = 1;

    public UnendingRitual() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = CARDS;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (upgraded) {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new UpgradedUnendingRitualPower(p, this.magicNumber), this.magicNumber));
        } else {
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new UnendingRitualPower(p, this.magicNumber), this.magicNumber));
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
