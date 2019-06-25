package theWildCard.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;

import java.util.Iterator;

import static theWildCard.DefaultMod.makeCardPath;

public class AttackUncommonQuietPassing extends AbstractDefaultCard {

    public static final String ID = DefaultMod.makeID(AttackUncommonQuietPassing.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("AttackUncommonQuietPassing.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    private static final int DAMAGE = 7;

    public AttackUncommonQuietPassing() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        this.exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_VERTICAL));
        int weak = 0;
        if (m.hasPower("Weakened")) {
            weak = m.getPower("Weakened").amount;
        }
        if (weak > 0) {
            Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while(iterator.hasNext()) {
                AbstractMonster mo = (AbstractMonster)iterator.next();
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new StrengthPower(mo, -weak), -weak));
            }
        }
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
