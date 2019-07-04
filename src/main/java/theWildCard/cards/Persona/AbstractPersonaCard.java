package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.cards.Arcana.AbstractArcanaCard;
import theWildCard.tags.Tags;
import theWildCard.variables.ArcanaEnums;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractPersonaCard extends AbstractDefaultCard {

    private static String activePersona;
    public ArcanaEnums.Arcana cardArcana;

    public AbstractPersonaCard(final String id,
                               final String img,
                               final int cost,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {

        super(id, img, cost, type, color, rarity, target);

        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        this.retain = true;
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
        removePersonaPower(p);
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
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        this.retain = true;
    }

    public void removePersonaPower(AbstractPlayer p) {
        if (activePersona != null) {
            AbstractDungeon.actionManager.addToTop(new RemoveSpecificPowerAction(p, p, activePersona));
        }
    }

    public static void changePersona(String persona) {
        activePersona = persona;
    }

    private void transformArcana(AbstractCard card) {
        if (card.hasTag(Tags.ARCANA)) {
            ((AbstractArcanaCard)card).transform();
        }
    }
}