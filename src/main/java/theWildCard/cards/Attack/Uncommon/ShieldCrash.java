package theWildCard.cards.Attack.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.WildcardMod;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class ShieldCrash extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(ShieldCrash.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("ShieldCrash.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 2;
    private static final int UPGRADED_COST = 1;

    public ShieldCrash() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    public void use(AbstractPlayer p, AbstractMonster m) {
        baseDamage = p.currentBlock;
        //Have to construct new multi-damage array or else this won't work
        int size = AbstractDungeon.getCurrRoom().monsters.monsters.size();
        int[] newMultiDamage = new int[size];
        for (int i = 0; i < newMultiDamage.length; i++) {
            newMultiDamage[i] = damage;
        }
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(p, newMultiDamage, damageTypeForTurn, AbstractGameAction.AttackEffect.BLUNT_HEAVY));
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
