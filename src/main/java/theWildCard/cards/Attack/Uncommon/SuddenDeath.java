package theWildCard.cards.Attack.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import theWildCard.WildcardMod;
import theWildCard.actions.KillAction;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class SuddenDeath extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(SuddenDeath.class.getSimpleName());
    public static final String IMG = makeCardPath("DeathAttack.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = -1;

    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 2;

    private static final int THRESHOLD = 7;
    private static final int UPGRADE_LOWER_THRESHOLD = -1;

    public SuddenDeath() {
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
                AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_COLLECTOR_DEBUFF"));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(new CollectorCurseEffect(m.hb.cX, m.hb.cY), 2.0F));
                AbstractDungeon.actionManager.addToBottom(new KillAction(m));
            } else {
                AbstractDungeon.actionManager.addToBottom(
                        new DamageAction(m, new DamageInfo(p, damage * effect, damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
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
