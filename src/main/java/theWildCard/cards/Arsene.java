package theWildCard.cards;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;
import theWildCard.powers.ArsenePower;
import theWildCard.tags.Tags;

import static theWildCard.DefaultMod.makeCardPath;

public class Arsene extends AbstractDynamicCard {

    public static final String ID = DefaultMod.makeID(Arsene.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    private static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);
    public static final String UPGRADE_DESCRIPTION = cardStrings.UPGRADE_DESCRIPTION;


    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 0;
    private static final int UPGRADED_COST = 0;

    private static final int STRENGTH = 1;
    private static final int DEXTERITY = 1;



    public Arsene() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = STRENGTH;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DEXTERITY;
        this.retain = true;
        tags.add(Tags.PERSONA);
    }


    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new StrengthPower(p, magicNumber), magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new DexterityPower(p, defaultSecondMagicNumber), defaultSecondMagicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                new ArsenePower(p, p), 0));
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        this.retain = true;
    }


    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADED_COST);
            rawDescription = UPGRADE_DESCRIPTION;
            initializeDescription();
            this.isInnate = true;
        }
    }
}
