package theWildCard.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.cards.Arcana.AbstractArcanaCard;

import java.util.ArrayList;
import java.util.Iterator;

public class LockArcanaAction extends AbstractGameAction {
    public static final String[] TEXT;
    private AbstractPlayer p;
    private ArrayList<AbstractCard> notArcana = new ArrayList();

    public LockArcanaAction(AbstractCreature source, int amount) {
        this.setValues(AbstractDungeon.player, source, amount);
        this.p = AbstractDungeon.player;
        this.actionType = AbstractGameAction.ActionType.CARD_MANIPULATION;
    }

    public void update() {
        if (this.duration == 0.5F) {

            if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
                this.isDone = true;
                return;
            }

            Iterator iterator = this.p.hand.group.iterator();
            while(iterator.hasNext()) {
                AbstractCard c = (AbstractCard)iterator.next();
                if (c instanceof AbstractArcanaCard && ((AbstractArcanaCard) c).isLocked) {
                    this.notArcana.add(c);
                }
                if (!(c instanceof AbstractArcanaCard)) {
                    this.notArcana.add(c);
                }
            }

            if (this.notArcana.size() == this.p.hand.group.size()) {
                this.isDone = true;
                return;
            }

            if (this.p.hand.group.size() - this.notArcana.size() <= this.amount) {
                Iterator var1 = this.p.hand.group.iterator();
                while(var1.hasNext()) {
                    AbstractCard c = (AbstractCard)var1.next();
                    if (c instanceof AbstractArcanaCard) {
                        ((AbstractArcanaCard) c).isLocked = true;
                    }
                }
                this.isDone = true;
                return;
            }

            this.p.hand.group.removeAll(this.notArcana);
            AbstractDungeon.handCardSelectScreen.open(TEXT[0], this.amount, false, false, false, false, false);
            AbstractDungeon.actionManager.addToBottom(new WaitAction(0.25F));
            this.tickDuration();
        } else {
            if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
                AbstractCard c;
                for(Iterator var1 = AbstractDungeon.handCardSelectScreen.selectedCards.group.iterator(); var1.hasNext(); AbstractDungeon.player.hand.addToTop(c)) {
                    c = (AbstractCard)var1.next();
                    if (c instanceof AbstractArcanaCard) {
                        ((AbstractArcanaCard) c).isLocked = true;
                    }
                }
                returnCards();
                AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
            }

            this.tickDuration();
        }
    }

    private void returnCards() {
        Iterator var1 = this.notArcana.iterator();

        while(var1.hasNext()) {
            AbstractCard c = (AbstractCard)var1.next();
            this.p.hand.addToTop(c);
        }

        this.p.hand.refreshHandLayout();
    }

    static {
        String[] text = {"Lock"};
        TEXT = text;
    }
}
