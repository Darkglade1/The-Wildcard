package theWildCard.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
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
    public AbstractCard cardToPreviewPriestess;
    public AbstractCard cardToPreviewEmperor;
    public AbstractCard cardToPreviewFool;
    public AbstractCard cardToPreviewJudgement;
    public AbstractCard cardToPreviewDeath;


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

    @Override
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
        this.costForTurn = cardToTransform.costForTurn;
        this.isCostModified = false;
        this.isCostModifiedForTurn = false;
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

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (cardToTransform != null) {
            return super.canUse(p, m);
        }
        return false;
    }

    @Override
    public void calculateCardDamage(AbstractMonster mo) {
        super.calculateCardDamage(mo);
        if (cardToTransform != null) {
            cardToTransform.calculateCardDamage(mo);
        }
    }

    @Override
    public void applyPowers() {
        super.applyPowers();
        if (cardToTransform != null) {
            cardToTransform.applyPowers();
        }
    }

    @Override
    public void onMoveToDiscard() {
        super.onMoveToDiscard();
        resetCard();
    }

    @Override
    public void triggerWhenDrawn() {
        if (ArcanaEnums.getActiveArcana() != null) {
            transform();
        }
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

    @Override
    public void hover() {
        try {
            //Sets up these variables to indicate that a preview should be shown
            cardToPreviewPriestess = priestessCard;
            cardToPreviewEmperor = emperorCard;
            cardToPreviewFool = foolCard;
            cardToPreviewJudgement = judgementCard;
            cardToPreviewDeath = deathCard;
            if (upgraded) {
                cardToPreviewPriestess.upgrade();
                cardToPreviewEmperor.upgrade();
                cardToPreviewFool.upgrade();
                cardToPreviewJudgement.upgrade();
                cardToPreviewDeath.upgrade();
            }
        } catch (Throwable e) {
            System.out.println(e.toString());
        }
        super.hover();
    }

    @Override
    public void unhover() {
        super.unhover();
        //remove the preview when the user stops hovering over the card
        cardToPreviewPriestess = null;
        cardToPreviewEmperor = null;
        cardToPreviewFool = null;
        cardToPreviewJudgement = null;
        cardToPreviewDeath = null;
    }

    @Override
    public void renderCardTip(SpriteBatch sb) {
        super.renderCardTip(sb);
        //Shows a preview of the different forms of the Arcana card above it when hovered over
        float drawScale = 0.5f;
        float yPosition = this.current_y + this.hb.height/1.35f;
        if (cardToPreviewPriestess != null) {
            AbstractCard card = cardToPreviewPriestess.makeStatEquivalentCopy();
            if (card != null) {
                card.drawScale = drawScale;
                card.current_x = this.current_x - this.hb.width;
                card.current_y = yPosition;
                card.render(sb);
            }
        }
        if (cardToPreviewEmperor != null) {
            AbstractCard card = cardToPreviewEmperor.makeStatEquivalentCopy();
            if (card != null) {
                card.drawScale = drawScale;
                card.current_x = this.current_x - this.hb.width/2;
                card.current_y = yPosition;
                card.render(sb);
            }
        }
        if (cardToPreviewFool != null) {
            AbstractCard card = cardToPreviewFool.makeStatEquivalentCopy();
            if (card != null) {
                card.drawScale = drawScale;
                card.current_x = this.current_x;
                card.current_y = yPosition;
                card.render(sb);
            }
        }
        if (cardToPreviewJudgement != null) {
            AbstractCard card = cardToPreviewJudgement.makeStatEquivalentCopy();
            if (card != null) {
                card.drawScale = drawScale;
                card.current_x = this.current_x + this.hb.width/2;
                card.current_y = yPosition;
                card.render(sb);
            }
        }
        if (cardToPreviewDeath != null) {
            AbstractCard card = cardToPreviewDeath.makeStatEquivalentCopy();
            if (card != null) {
                card.drawScale = drawScale;
                card.current_x = this.current_x + this.hb.width;
                card.current_y = yPosition;
                card.render(sb);
            }
        }
    }
    
    private void resetCard() {
        this.name = languagePack.getCardStrings(cardID).NAME;
        this.cost = -1;
        this.costForTurn = this.cost;
        this.isCostModified = false;
        this.isCostModifiedForTurn = false;
        this.rawDescription = languagePack.getCardStrings(cardID).DESCRIPTION;
        this.exhaust = false;
        this.purgeOnUse = false;
        if (this.upgraded) {
            upgradeName();
        }
        resetAttributes();
        initializeDescription();
    }
}