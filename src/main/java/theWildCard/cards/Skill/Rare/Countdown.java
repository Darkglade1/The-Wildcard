package theWildCard.cards.Skill.Rare;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import theWildCard.WildcardMod;
import theWildCard.actions.KillAction;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.cards.Arcana.AbstractArcanaCard;
import theWildCard.characters.WildcardCharacter;

import java.util.Iterator;

import static theWildCard.WildcardMod.makeCardPath;

public class Countdown extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(Countdown.class.getSimpleName());
    public static final String IMG = makeCardPath("DeathSkill.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 13;
    private static final int UPGRADED_COST = 12;

    private AbstractArcanaCard arcanaCard; //Reference to the Arcana card that can transform into this

    public Countdown() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        exhaust = true;
        retain = true;
    }

    public Countdown(AbstractArcanaCard arcanaCard) {
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
