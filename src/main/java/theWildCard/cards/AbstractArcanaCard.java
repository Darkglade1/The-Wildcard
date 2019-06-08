package theWildCard.cards;

import basemod.abstracts.CustomCard;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.tags.Tags;
import theWildCard.variables.ArcanaEnums;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractArcanaCard extends CustomCard {

    public ArcanaEnums.Arcana cardArcana;
    public AbstractCard priestessCard;
    public AbstractCard emperorCard;
    public AbstractCard foolCard;
    public AbstractCard judgementCard;
    public AbstractCard deathCard;
    public AbstractCard cardToTransform;

    public AbstractArcanaCard(final String id,
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
        tags.add(Tags.ARCANA);
    }

    public void triggerOnOtherCardPlayed(AbstractCard c) {
        if (c.tags.contains(Tags.PERSONA)) {
            transform();
        }
    }

    public void transform() {
        cardArcana = ArcanaEnums.getActiveArcana();
        if (cardArcana == ArcanaEnums.Arcana.PRIESTESS) {
            cardToTransform = priestessCard;
        }
        else if (cardArcana == ArcanaEnums.Arcana.EMPEROR) {
            cardToTransform = emperorCard;
        }
        else if (cardArcana == ArcanaEnums.Arcana.FOOL) {
            cardToTransform = foolCard;
        }
        else if (cardArcana == ArcanaEnums.Arcana.JUDGEMENT) {
            cardToTransform = judgementCard;
        }
        else {
            cardToTransform = deathCard;
        }
        if (this.upgraded) {
            cardToTransform.upgrade();
        }

        this.name = cardToTransform.name;
        this.target = cardToTransform.target;
        this.cost = cardToTransform.cost;
        this.energyOnUse = cardToTransform.cost;
        this.costForTurn = cardToTransform.costForTurn;
        this.exhaust = cardToTransform.exhaust;
        this.purgeOnUse = cardToTransform.purgeOnUse;
        this.baseDamage = cardToTransform.baseDamage;
        this.baseBlock = cardToTransform.baseBlock;
        this.baseDraw = cardToTransform.baseDraw;
        this.baseMagicNumber = cardToTransform.baseMagicNumber;
        this.baseHeal = cardToTransform.baseHeal;
        this.baseDiscard = cardToTransform.baseDiscard;
        this.rawDescription = languagePack.getCardStrings(cardToTransform.cardID).DESCRIPTION;

        initializeDescription();
    }

    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (cardToTransform != null) {
            return cardToTransform.canUse(p, m);
        }
        return false;
    }

    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (cardToTransform != null) {
            cardToTransform.calculateCardDamage(mo);
        }
    }

    public void applyPowers() {
        super.applyPowers();
        if (cardToTransform != null) {
            cardToTransform.applyPowers();
        }
    }

    public void onMoveToDiscard() {
        super.onMoveToDiscard();
        resetCard();
    }

    public void atTurnStart() {
        if (ArcanaEnums.getActiveArcana() != null) {
            transform();
        }
    }

    public void resetCard() {
        this.name = languagePack.getCardStrings(cardID).NAME;
        this.cost = -1;
        this.costForTurn = this.cost;
        this.isCostModifiedForTurn = false;
        this.rawDescription = languagePack.getCardStrings(cardID).DESCRIPTION;
        this.exhaust = false;
        this.purgeOnUse = false;
        if (this.upgraded) {
            upgradeName();
        }
        //loadCardImage("chrono_images/cards/ResonantCall.png");
        resetAttributes();
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        cardToTransform.use(p, m);
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
        }
    }
}