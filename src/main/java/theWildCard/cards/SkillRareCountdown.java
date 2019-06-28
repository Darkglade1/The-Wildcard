package theWildCard.cards;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import theWildCard.DefaultMod;
import theWildCard.actions.KillAction;
import theWildCard.characters.TheDefault;

import java.util.Iterator;

import static theWildCard.DefaultMod.makeCardPath;

public class SkillRareCountdown extends AbstractDefaultCard {

    public static final String ID = DefaultMod.makeID(SkillRareCountdown.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");// "public static final String IMG = makeCardPath("SkillRareCountdown.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = TheDefault.Enums.COLOR_BLUE;

    private static final int COST = 10;
    private static final int UPGRADED_COST = 9;

    private AbstractArcanaCard arcanaCard; //Reference to the Arcana card that can transform into this

    public SkillRareCountdown() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        exhaust = true;
        retain = true;
    }

    public SkillRareCountdown(AbstractArcanaCard arcanaCard) {
        this();
        this.arcanaCard = arcanaCard;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_COLLECTOR_DEBUFF"));
        AbstractDungeon.actionManager.addToBottom(new VFXAction(new CollectorCurseEffect(m.hb.cX, m.hb.cY), 2.0F));
        AbstractDungeon.actionManager.addToBottom(new KillAction(m));
    }

    @Override
    public void atTurnStart() {
        if (arcanaCard != null) {
            Iterator iterator = AbstractDungeon.player.hand.group.iterator();
            while(iterator.hasNext()) {
                AbstractCard c = (AbstractCard)iterator.next();
                if (c == arcanaCard) {
                    c.retain = true;
                    c.modifyCostForCombat(-1); //Decreases the cost of the Arcana card via this card's effect
                }
            }
        } else {
            this.retain = true;
            modifyCostForCombat(-1); //in case the player somehow obtains a standalone version of the card
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
