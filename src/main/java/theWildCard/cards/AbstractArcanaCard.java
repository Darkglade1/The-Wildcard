package theWildCard.cards;

import basemod.abstracts.CustomCard;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.Hitbox;
import com.megacrit.cardcrawl.helpers.input.InputHelper;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import theWildCard.tags.Tags;
import theWildCard.variables.ArcanaEnums;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theWildCard.DefaultMod.makeCardPath;

public abstract class AbstractArcanaCard extends CustomCard {

    public ArcanaEnums.Arcana cardArcana;
    public AbstractCard priestessCard;
    public AbstractCard emperorCard;
    public AbstractCard foolCard;
    public AbstractCard judgementCard;
    public AbstractCard deathCard;
    public AbstractCard cardToTransform;
    public AbstractCard cardToPreview;
    public boolean bullshit = false;
    protected Class previewClass;

    public AbstractArcanaCard(final String id,
                              final String img,
                              final int cost,
                              final CardType type,
                              final CardColor color,
                              final CardRarity rarity,
                              final CardTarget target,
                              Class previewCard) {

        super(id, languagePack.getCardStrings(id).NAME, img, cost, languagePack.getCardStrings(id).DESCRIPTION, type, color, rarity, target);

        // Set all the things to their default values.
        isCostModified = false;
        isCostModifiedForTurn = false;
        isDamageModified = false;
        isBlockModified = false;
        isMagicNumberModified = false;
        tags.add(Tags.ARCANA);
        cardToPreview = foolCard;
        this.previewClass = previewCard;
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

    @Override
    public boolean canUse(AbstractPlayer p, AbstractMonster m) {
        if (cardToTransform != null) {
            return cardToTransform.canUse(p, m);
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
            this.cardToPreview = (AbstractCard) this.previewClass.newInstance();
            if (this.upgraded) {
                this.cardToPreview.upgrade();
            }
        } catch (Throwable e) {
            System.out.println(e.toString());
        }
        super.hover();
        this.bullshit = true;
    }

    @Override
    public void unhover() {
        super.unhover();
        this.bullshit = false;
        this.cardToPreview = null;
    }

    @Override
    public void renderInLibrary(SpriteBatch sb) {
//        if (this.current_y < -200.0F * Settings.scale && this.current_y <= Settings.HEIGHT + 200.0F * Settings.scale) {
//            return;
//        }
//        if (SingleCardViewPopup.isViewingUpgrade && this.isSeen && !this.isLocked) {
////            AbstractCard copy = makeStatEquivalentCopy();
////            copy.current_x = this.current_x;
////            copy.current_y = this.current_y;
////            copy.drawScale = this.drawScale;
////            copy.upgrade();
////            copy.displayUpgrades();
////            copy.render(sb);
//        } else {
//
//            super.renderInLibrary(sb);
//        }
        super.renderInLibrary(sb);
        if (cardToPreview != null) {
            AbstractCard rCard = cardToPreview.makeStatEquivalentCopy();
            if (rCard != null) {
                rCard.drawScale = 0.75f;
                rCard.current_x = this.current_x - (rCard.hb.width / 2f);
                rCard.current_y = this.current_y;
                rCard.render(sb);
            }
        }
    }

    @Override
    public void renderCardTip(SpriteBatch sb) {
        super.renderCardTip(sb);
        if (cardToPreview != null) {
            AbstractCard rCard = cardToPreview.makeStatEquivalentCopy();
            if (rCard != null) {
                rCard.drawScale = 0.75f;
                rCard.current_x = this.current_x - (rCard.hb.width / 2f);
                rCard.current_y = this.current_y;
                rCard.render(sb);
            }
        }
//        if (this.cardToPreview != null && !Settings.hideCards && this.bullshit) {
//
//            float tmpScale = this.drawScale / 1.5F;
//
////            if (this.newTarget == null)
////                return;
////            Hitbox target = this.newTarget.hb;
//
//            this.cardToPreview.current_x = Gdx.input.getX() + AbstractCard.IMG_WIDTH / 2.0F / 1.5F;
//            this.cardToPreview.current_y = (Settings.HEIGHT - Gdx.input.getY()) + AbstractCard.IMG_HEIGHT / 2.0F / 1.5F;
//
//            this.cardToPreview.drawScale = tmpScale;
////            if (AbstractDungeon.player.hasRelic("Runic Dome")) {
////                this.cardToPreview.setLocked();
////            }
//            this.cardToPreview.render(sb);
//        }
//        AbstractCard rCard = makeStatEquivalentCopy();
//        if (rCard != null) {
//            rCard.drawScale = 0.75f;
//            rCard.current_x = InputHelper.mX - (rCard.hb.width / 2f);
//            rCard.current_y = InputHelper.mY;
//            rCard.render(sb);
//        }
    }
    
    private void resetCard() {
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
        resetAttributes();
        initializeDescription();
    }
}