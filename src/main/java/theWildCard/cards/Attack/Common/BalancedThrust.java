package theWildCard.cards.Attack.Common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.WildcardMod;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class BalancedThrust extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(BalancedThrust.class.getSimpleName());
    public static final String IMG = makeCardPath("FoolAttack.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 1;

    private static final int DAMAGE = 6;
    private static final int UPGRADE_PLUS_DMG = 2;
    private static final int BLOCK = 6;
    private static final int UPGRADE_PLUS_BLOCK = 2;


    public BalancedThrust() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
