package theWildCard.cards.Attack.Uncommon;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.AttackDamageRandomEnemyAction;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;
import theWildCard.WildcardMod;
import theWildCard.actions.DiscardArcanaAction;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.cards.OnDiscardArcanaCard;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class LadyLuck extends AbstractDefaultCard implements OnDiscardArcanaCard {

    public static final String ID = WildcardMod.makeID(LadyLuck.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("LadyLuck.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = -1;

    private static final int DAMAGE = 13;
    private static final int UPGRADE_PLUS_DMG = 5;

    public LadyLuck() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
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
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, effect));
            AbstractDungeon.actionManager.addToBottom(new DiscardArcanaAction(p, p, effect, false, false, this));
            if (!this.freeToPlayOnce) {
                p.energy.use(EnergyPanel.totalCount);
            }
        }
    }

    @Override
    public void onDiscardArcana() {
        AbstractDungeon.actionManager.addToBottom(new AttackDamageRandomEnemyAction(this, AbstractGameAction.AttackEffect.SLASH_HORIZONTAL));
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
