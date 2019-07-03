package theWildCard.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.cards.AbstractPersonaCard;

public class ExhaustPersonaAction extends AbstractGameAction {
    private AbstractPlayer p;

    public ExhaustPersonaAction(AbstractCreature target, AbstractCreature source, int amount) {
        this.p = (AbstractPlayer)target;
        this.setValues(target, source, amount);
        this.duration = Settings.ACTION_DUR_FAST;
        this.actionType = AbstractGameAction.ActionType.EXHAUST;
    }

    //Randomly exhausts Persona in the player's hand
    public void update() {
        CardGroup personaCards = new CardGroup(CardGroup.CardGroupType.UNSPECIFIED);
        for (AbstractCard card : this.p.hand.group) {
            if (card instanceof AbstractPersonaCard) {
                personaCards.addToTop(card);
            }
        }
        if (this.duration == Settings.ACTION_DUR_FAST) {
            if (personaCards.size() == 0) {
                this.isDone = true;
                return;
            }
            if (this.amount > personaCards.size()) {
                this.amount = personaCards.size();
            }
            for(int i = 0; i < this.amount; ++i) {
                AbstractCard cardToExhaust = personaCards.getRandomCard(AbstractDungeon.cardRandomRng);
                this.p.hand.moveToExhaustPile(cardToExhaust);
                personaCards.removeCard(cardToExhaust);
            }

            CardCrawlGame.dungeon.checkForPactAchievement();
        }

        this.tickDuration();
    }
}
