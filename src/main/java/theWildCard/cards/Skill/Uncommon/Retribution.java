package theWildCard.cards.Skill.Uncommon;

import basemod.ReflectionHacks;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import theWildCard.WildcardMod;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.characters.WildcardCharacter;

import java.util.Iterator;

import static theWildCard.WildcardMod.makeCardPath;

public class Retribution extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(Retribution.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("Retribution.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    public Retribution() {
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
                AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
                AbstractDungeon.actionManager.addToBottom(new VFXAction(p, new CleaveEffect(), 0.0F));
                Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
                while(iterator.hasNext()) {
                    AbstractMonster mo = (AbstractMonster)iterator.next();
                    mo.damage(new DamageInfo(mo, damage, DamageInfo.DamageType.HP_LOSS));
                }
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
