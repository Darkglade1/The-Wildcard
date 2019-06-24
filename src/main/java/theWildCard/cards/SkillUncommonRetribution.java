package theWildCard.cards;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class SkillUncommonRetribution extends AbstractDefaultCard {

    public static final String ID = DefaultMod.makeID(SkillUncommonRetribution.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SkillUncommonRetribution.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    public SkillUncommonRetribution() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        isMultiDamage = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int damage;
        if (!m.isDeadOrEscaped()) {
            if (m.intent == AbstractMonster.Intent.ATTACK || m.intent == AbstractMonster.Intent.ATTACK_DEFEND || m.intent == AbstractMonster.Intent.ATTACK_DEBUFF || m.intent == AbstractMonster.Intent.ATTACK_BUFF) {
                damage = (Integer) ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentDmg");
                if ((Boolean) ReflectionHacks.getPrivate(m, AbstractMonster.class, "isMultiDmg")) {
                    damage *= (Integer) ReflectionHacks.getPrivate(m, AbstractMonster.class, "intentMultiAmt");
                }
                int size = AbstractDungeon.getCurrRoom().monsters.monsters.size();
                int[] newMultiDamage = new int[size];
                for (int i = 0; i < newMultiDamage.length; i++) {
                    newMultiDamage[i] = damage;
                }
                AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.1F));
                AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, newMultiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
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
