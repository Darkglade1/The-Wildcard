package theWildCard.cards.Attack.Common;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.CleaveEffect;
import theWildCard.WildcardMod;
import theWildCard.actions.DiscardArcanaAction;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.cards.OnDiscardPersonaArcanaCard;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class EnergizedStroke extends AbstractDefaultCard implements OnDiscardPersonaArcanaCard {

    public static final String ID = WildcardMod.makeID(EnergizedStroke.class.getSimpleName());
    public static final String IMG = makeCardPath("EnergizedStroke.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 1;

    private static final int DAMAGE = 7;
    private static final int UPGRADE_PLUS_DMG = 2;

    public EnergizedStroke() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
        AbstractDungeon.actionManager.addToBottom(new DiscardArcanaAction(p, p, -1, false, false, this));
    }

    @Override
    public void onDiscardPersonaArcana() {
        int size = AbstractDungeon.getCurrRoom().monsters.monsters.size();

        int[] newMultiDamage = new int[size];
        for (int i = 0; i < size; i++) {
            this.calculateCardDamage(AbstractDungeon.getCurrRoom().monsters.monsters.get(i));
            newMultiDamage[i] = damage;
        }
        AbstractDungeon.actionManager.addToBottom(new SFXAction("ATTACK_HEAVY"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(AbstractDungeon.player, new CleaveEffect(), 0.1F));
        AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(AbstractDungeon.player, newMultiDamage, this.damageTypeForTurn, AbstractGameAction.AttackEffect.NONE));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
