package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.cards.Arcana.AbstractArcanaCard;
import theWildCard.tags.Tags;
import theWildCard.variables.ArcanaEnums;

import java.util.ArrayList;
import java.util.Iterator;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractPersonaCard extends AbstractDefaultCard {

    private static String activePersona;
    public static boolean canChangePersona = true;
    private ArcanaEnums.Arcana cardArcana;
    private AbstractPower personaPower;

    public AbstractPersonaCard(final String id,
                               final String img,
                               final int cost,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target,
                               final ArcanaEnums.Arcana cardArcana,
                               final AbstractPower personaPower) {

        super(id, img, cost, type, color, rarity, target);

        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        this.retain = true;
        this.cardArcana = cardArcana;
        this.personaPower = personaPower;
        tags.add(Tags.PERSONA);
    }

    // Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            rawDescription = languagePack.getCardStrings(cardID).UPGRADE_DESCRIPTION;
            initializeDescription();
            this.isInnate = true;
        }
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (canChangePersona) {
            removePersonaPower(p);
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                    personaPower, 0));
            activePersona = personaPower.ID;
            if (ArcanaEnums.getActiveArcana() != cardArcana) {
                ArcanaEnums.changeArcana(cardArcana);
                for (AbstractCard card: AbstractDungeon.player.hand.group) {
                    transformArcana(card);
                }
                for (AbstractCard card: AbstractDungeon.player.drawPile.group) {
                    transformArcana(card);
                }
                for (AbstractCard card: AbstractDungeon.player.discardPile.group) {
                    transformArcana(card);
                }
                for (AbstractCard card: AbstractDungeon.player.exhaustPile.group) {
                    transformArcana(card);
                }
            }
        } else {
            //Needed to make Attunement work
            if (personaPower.ID.equals(activePersona)) {
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(p, p,
                        personaPower, 0));
            }
        }
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        this.retain = true;
    }

    private void removePersonaPower(AbstractPlayer p) {
        if (activePersona != null) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, activePersona));
        }
    }

    public static void changePersona(String persona) {
        activePersona = persona;
    }

    public static String getActivePersona() {
        return activePersona;
    }

    public static boolean checkForPersonaInHand() {
        for (AbstractCard card : AbstractDungeon.player.hand.group) {
            if (card instanceof AbstractPersonaCard) {
                return true;
            }
        }
        return false;
    }

    public static AbstractPersonaCard returnTrulyRandomPersona() {
        ArrayList<AbstractCard> list = new ArrayList();
        Iterator iterator = AbstractDungeon.srcCommonCardPool.group.iterator();

        AbstractCard c;
        while(iterator.hasNext()) {
            c = (AbstractCard)iterator.next();
            if (c instanceof AbstractPersonaCard && !(list.contains(c))) {
                list.add(c);
            }
        }

        iterator = AbstractDungeon.srcUncommonCardPool.group.iterator();

        while(iterator.hasNext()) {
            c = (AbstractCard)iterator.next();
            if (c instanceof AbstractPersonaCard && !(list.contains(c))) {
                list.add(c);
            }
        }

        iterator = AbstractDungeon.srcRareCardPool.group.iterator();

        while(iterator.hasNext()) {
            c = (AbstractCard)iterator.next();
            if (c instanceof AbstractPersonaCard && !(list.contains(c))) {
                list.add(c);
            }
        }

        return (AbstractPersonaCard)list.get(AbstractDungeon.cardRandomRng.random(list.size() - 1));
    }

    private void transformArcana(AbstractCard card) {
        if (card.hasTag(Tags.ARCANA)) {
            AbstractArcanaCard arcana = (AbstractArcanaCard) card;
            arcana.changeArcana();
        }
    }
}