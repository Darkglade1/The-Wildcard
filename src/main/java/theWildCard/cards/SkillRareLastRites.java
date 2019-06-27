package theWildCard.cards;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.HealAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.DefaultMod;
import theWildCard.characters.TheDefault;

import static theWildCard.DefaultMod.makeCardPath;

public class SkillRareLastRites extends AbstractDefaultCard {

    public static final String ID = DefaultMod.makeID(SkillRareLastRites.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SkillRareLastRites.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 2;

    private static final int DAMAGE = 5;
    private static final int UPGRADE_PLUS_DMG = 2;

    private static final int HEAL = 3;
    private static final int UPGRADE_PLUS_HEAL = 1;

    public SkillRareLastRites() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = HEAL;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int amount = p.exhaustPile.group.size();
        for(int i = 0; i < amount; i++) {
            AbstractDungeon.actionManager.addToTop(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.FIRE));
        }
        AbstractDungeon.actionManager.addToBottom(new HealAction(p, p, magicNumber * amount));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_HEAL);
            initializeDescription();
        }
    }
}