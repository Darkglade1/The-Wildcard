package theWildCard.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.tags.Tags;
import theWildCard.variables.ArcanaEnums;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractPersonaCard extends CustomCard {

    public static String activePersona;
    public ArcanaEnums.Arcana cardArcana;

    public AbstractPersonaCard(final String id,
                               final String img,
                               final int cost,
                               final CardType type,
                               final CardColor color,
                               final CardRarity rarity,
                               final CardTarget target) {

        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);

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
        ArcanaEnums.changeArcana(cardArcana);
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
}