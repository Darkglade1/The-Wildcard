package theWildCard.cards.Attack.Rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.WildcardMod;
import theWildCard.actions.DiscardArcanaAction;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.cards.OnDiscardArcanaCard;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class Overwhelm extends AbstractDefaultCard implements OnDiscardArcanaCard {

    public static final String ID = WildcardMod.makeID(Overwhelm.class.getSimpleName());
    public static final String IMG = makeCardPath("Overwhelm.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 3;

    private static final int DAMAGE = 20;
    private static final int UPGRADE_PLUS_DMG = 5;

    private static final int DAMAGE_UP = 20;
    private static final int UPGRADE_PLUS_DMG_UP = 5;

    public Overwhelm() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = DAMAGE_UP;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SMASH));
        AbstractDungeon.actionManager.addToBottom(new DiscardArcanaAction(p, p, -1, false, false, this));
    }

    @Override
    public void onDiscardArcana() {
        AbstractDungeon.actionManager.addToBottom(new ModifyDamageAction(this.uuid, this.magicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_DMG_UP);
            initializeDescription();
        }
    }
}
