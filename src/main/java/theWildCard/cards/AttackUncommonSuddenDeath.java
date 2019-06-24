package theWildCard.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theWildCard.DefaultMod;
import theWildCard.actions.KillAction;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class AttackUncommonSuddenDeath extends AbstractDefaultCard {

    public static final String ID = DefaultMod.makeID(AttackUncommonSuddenDeath.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("AttackUncommonSuddenDeath.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = -1;

    private static final int DAMAGE = 10;
    private static final int UPGRADE_PLUS_DMG = 2;

    private static final int THRESHOLD = 7;
    private static final int UPGRADE_LOWER_THRESHOLD = -1;

    public AttackUncommonSuddenDeath() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = THRESHOLD;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int effect = EnergyPanel.totalCount;
        if (energyOnUse > 0) {
            effect = energyOnUse;
        }
        if (p.hasRelic("Chemical X")) {
            effect += 2;
            p.getRelic("Chemical X").flash();
        }
        if (effect > 0) {
            if (effect >= magicNumber && m.type != AbstractMonster.EnemyType.BOSS) {
                AbstractDungeon.actionManager.addToBottom(new KillAction(m));
            } else {
                int totalDamage = damage * effect;
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, totalDamage, damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
            }
            if (!this.freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_LOWER_THRESHOLD);
            initializeDescription();
        }
    }
}
