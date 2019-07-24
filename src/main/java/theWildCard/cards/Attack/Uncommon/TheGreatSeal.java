package theWildCard.cards.Attack.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theWildCard.WildcardMod;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.cards.Persona.AbstractPersonaCard;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class TheGreatSeal extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(TheGreatSeal.class.getSimpleName());
    public static final String IMG = makeCardPath("TheGreatSeal.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 1;

    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 1;

    private static final int STR_GAIN = 2;
    private static final int STR_GAIN_UPGRADE = 1;

    private static final int STR_DEBUFF = 2;
    private static final int STR_DEBUFF_UPGRADE = 1;

    public TheGreatSeal() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = STR_GAIN;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = STR_DEBUFF;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_DIAGONAL));
        for (AbstractCard card : p.hand.group) {
            if (card instanceof AbstractPersonaCard) {
                return;
            }
        }
        for (AbstractCard card : p.drawPile.group) {
            if (card instanceof AbstractPersonaCard) {
                return;
            }
        }
        for (AbstractCard card : p.discardPile.group) {
            if (card instanceof AbstractPersonaCard) {
                return;
            }
        }
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p, new StrengthPower(p, this.magicNumber), this.magicNumber));
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(m, p, new StrengthPower(m, -this.defaultSecondMagicNumber), -this.defaultSecondMagicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(STR_GAIN_UPGRADE);
            upgradeDefaultSecondMagicNumber(STR_DEBUFF_UPGRADE);
            initializeDescription();
        }
    }
}
