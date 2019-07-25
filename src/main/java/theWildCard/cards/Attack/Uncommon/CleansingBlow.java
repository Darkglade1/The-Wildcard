package theWildCard.cards.Attack.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.unique.RemoveDebuffsAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.WildcardMod;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.characters.WildcardCharacter;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theWildCard.WildcardMod.makeCardPath;

public class CleansingBlow extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(CleansingBlow.class.getSimpleName());
    public static final String IMG = makeCardPath("EmperorAttack.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 1;

    private static final int DAMAGE = 5;

    public CleansingBlow() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        AbstractDungeon.actionManager.addToBottom(new RemoveDebuffsAction(AbstractDungeon.player));
        ArrayList<AbstractCard> exhaustList = new ArrayList<>();
        for (AbstractCard card : p.hand.group) {
            if (card.type == CardType.CURSE || card.type == CardType.STATUS) {
                exhaustList.add(card);
            }
        }
        for (AbstractCard card : exhaustList) {
            p.hand.moveToExhaustPile(card);
        }
        exhaustList.clear();

        for (AbstractCard card : p.drawPile.group) {
            if (card.type == CardType.CURSE || card.type == CardType.STATUS) {
                exhaustList.add(card);
            }
        }
        for (AbstractCard card : exhaustList) {
            p.drawPile.moveToExhaustPile(card);
        }
        exhaustList.clear();

        for (AbstractCard card : p.discardPile.group) {
            if (card.type == CardType.CURSE || card.type == CardType.STATUS) {
                exhaustList.add(card);
            }
        }
        for (AbstractCard card : exhaustList) {
            p.discardPile.moveToExhaustPile(card);
        }
        exhaustList.clear();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            this.exhaust = false;
            rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
            initializeDescription();
        }
    }
}
