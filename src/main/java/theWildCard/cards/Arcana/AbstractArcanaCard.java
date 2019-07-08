package theWildCard.cards.Arcana;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.tags.Tags;
import theWildCard.variables.ArcanaEnums;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public abstract class AbstractArcanaCard extends AbstractDefaultCard {

    public ArcanaEnums.Arcana cardArcana;
    public AbstractDefaultCard priestessCard;
    public AbstractDefaultCard emperorCard;
    public AbstractDefaultCard foolCard;
    public AbstractDefaultCard judgementCard;
    public AbstractDefaultCard deathCard;
    public AbstractDefaultCard cardToTransform;
    public AbstractDefaultCard cardToPreviewPriestess;
    public AbstractDefaultCard cardToPreviewEmperor;
    public AbstractDefaultCard cardToPreviewFool;
    public AbstractDefaultCard cardToPreviewJudgement;
    public AbstractDefaultCard cardToPreviewDeath;
    public boolean isLocked = false;


    public AbstractArcanaCard(final String id,
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
        tags.add(Tags.ARCANA);
    }

    public void changeArcana() {
        if (!isLocked) {
            cardArcana = ArcanaEnums.getActiveArcana();
            transform();
        }
    }

    public void transform() {
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
        else if (cardArcana == ArcanaEnums.Arcana.DEATH){
            cardToTransform = deathCard;
        }

        if (cardToTransform != null) {
            this.rawDescription = languagePack.getCardStrings(cardToTransform.cardID).DESCRIPTION;
            if (this.upgraded) {
                cardToTransform.upgrade();
                //Sets the card description to the upgraded version, if it exists.
                if (languagePack.getCardStrings(cardToTransform.cardID).UPGRADE_DESCRIPTION != null) {
                    this.rawDescription = languagePack.getCardStrings(cardToTransform.cardID).UPGRADE_DESCRIPTION;
                }
            }

            this.name = cardToTransform.name;
            this.target = cardToTransform.target;
            this.cost = cardToTransform.cost;
            this.costForTurn = cardToTransform.costForTurn;
            this.isCostModified = false;
            this.isCostModifiedForTurn = false;
            this.energyOnUse = cardToTransform.energyOnUse;
            this.freeToPlayOnce = cardToTransform.freeToPlayOnce;
            this.exhaust = cardToTransform.exhaust;
            this.retain = cardToTransform.retain;
            this.purgeOnUse = cardToTransform.purgeOnUse;
            this.baseDamage = cardToTransform.baseDamage;
            this.baseBlock = cardToTransform.baseBlock;
            this.baseDraw = cardToTransform.baseDraw;
            this.baseMagicNumber = cardToTransform.baseMagicNumber;
            this.defaultBaseSecondMagicNumber = cardToTransform.defaultBaseSecondMagicNumber;
            this.baseHeal = cardToTransform.baseHeal;
            this.baseDiscard = cardToTransform.baseDiscard;

            initializeDescription();
        }
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

    //Piggybacks off the keyword tooltip popup to also show the Arcana previews
    @Override
    public void renderCardTip(SpriteBatch sb) {
        super.renderCardTip(sb);
        //Removes the preview when the player is manipulating the card or if the card is locked
        if (isLocked || (AbstractDungeon.player != null && (AbstractDungeon.player.isDraggingCard || AbstractDungeon.player.inSingleTargetMode))) {
            return;
        }
        float drawScale = 0.5f;
        float yPosition1 = this.current_y + this.hb.height * 0.75f;
        float yPosition2 = this.current_y + this.hb.height * 0.25f;
        float yPosition3 = this.current_y - this.hb.height * 0.25f;
        float xPosition1;
        float xPosition2;
        float xPosition3;
        float xOffset1 = -this.hb.width * 0.75f;
        float xOffset2 = -this.hb.width * 0.25f;
        float xOffset3 = this.hb.width * 0.25f;

        if (this.current_x > Settings.WIDTH * 0.75F) {
            xOffset1 = -xOffset1;
            xOffset2 = -xOffset2;
            xOffset3 = -xOffset3;
        }

        xPosition1 = this.current_x + xOffset1;
        xPosition2 = this.current_x + xOffset2;
        xPosition3 = this.current_x + xOffset3;

        if (cardToPreviewPriestess != null) {
            AbstractCard card = cardToPreviewPriestess.makeStatEquivalentCopy();
            if (card != null) {
                card.drawScale = drawScale;
                card.current_x = xPosition1;
                card.current_y = yPosition3;
                card.render(sb);
            }
        }
        if (cardToPreviewEmperor != null) {
            AbstractCard card = cardToPreviewEmperor.makeStatEquivalentCopy();
            if (card != null) {
                card.drawScale = drawScale;
                card.current_x = xPosition1;
                card.current_y = yPosition2;
                card.render(sb);
            }
        }
        if (cardToPreviewFool != null) {
            AbstractCard card = cardToPreviewFool.makeStatEquivalentCopy();
            if (card != null) {
                card.drawScale = drawScale;
                card.current_x = xPosition1;
                card.current_y = yPosition1;
                card.render(sb);
            }
        }
        if (cardToPreviewJudgement != null) {
            AbstractCard card = cardToPreviewJudgement.makeStatEquivalentCopy();
            if (card != null) {
                card.drawScale = drawScale;
                card.current_x = xPosition2;
                card.current_y = yPosition1;
                card.render(sb);
            }
        }
        if (cardToPreviewDeath != null) {
            AbstractCard card = cardToPreviewDeath.makeStatEquivalentCopy();
            if (card != null) {
                card.drawScale = drawScale;
                card.current_x = xPosition3;
                card.current_y = yPosition1;
                card.render(sb);
            }
        }
    }

    @Override
    public void atTurnStart() {
        super.atTurnStart();
        if (cardToTransform != null) {
            cardToTransform.atTurnStart();
        }
    }

    @Override
    public AbstractCard makeCopy() {
        AbstractArcanaCard card = (AbstractArcanaCard)super.makeCopy();
        card.cardArcana = this.cardArcana;
        card.transform();
        card.isLocked = this.isLocked;
        return card;
    }
}