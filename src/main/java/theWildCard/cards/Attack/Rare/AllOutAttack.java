package theWildCard.cards.Attack.Rare;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardQueueItem;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.WildcardMod;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.cards.Arcana.AbstractArcanaCard;
import theWildCard.cards.Persona.AbstractPersonaCard;
import theWildCard.characters.WildcardCharacter;

import java.util.ArrayList;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;
import static theWildCard.WildcardMod.makeCardPath;

public class AllOutAttack extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(AllOutAttack.class.getSimpleName());
    public static final String IMG = makeCardPath("AllOutAttack.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 3;
    private static final int UPGRADED_COST = 2;

    public AllOutAttack() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        int personaCount = 0;
        ArrayList<AbstractCard> attacks = new ArrayList<>();
        for (AbstractCard card : p.hand.group) {
            if (card instanceof AbstractPersonaCard) {
                personaCount++;
            } else if (card.type == CardType.ATTACK && !(card.cardID.equals(this.cardID))) {
                //prevents untransformed Arcana from being added
                if (!(card instanceof AbstractArcanaCard && ((AbstractArcanaCard)card).cardToTransform == null)) {
                    attacks.add(card);
                }
            }
        }
        for (AbstractCard card : p.drawPile.group) {
            if (card instanceof AbstractPersonaCard) {
                personaCount++;
            } else if (card.type == CardType.ATTACK && !(card.cardID.equals(this.cardID))) {
                //prevents untransformed Arcana from being added
                if (!(card instanceof AbstractArcanaCard && ((AbstractArcanaCard)card).cardToTransform == null)) {
                    attacks.add(card);
                }
            }
        }
        for (AbstractCard card : p.discardPile.group) {
            if (card instanceof AbstractPersonaCard) {
                personaCount++;
            } else if (card.type == CardType.ATTACK && !(card.cardID.equals(this.cardID))) {
                //prevents untransformed Arcana from being added
                if (!(card instanceof AbstractArcanaCard && ((AbstractArcanaCard)card).cardToTransform == null)) {
                    attacks.add(card);
                }
            }
        }
        for (int i = 0; i < personaCount; i++) {
            AbstractCard card = attacks.get(AbstractDungeon.cardRandomRng.random(attacks.size() - 1));
            AbstractMonster mo = null;
            if (card.target == CardTarget.ENEMY) {
                mo = AbstractDungeon.getMonsters().getRandomMonster(null, true, AbstractDungeon.cardRandomRng);
            }
            AbstractCard tmp = card.makeSameInstanceOf();
            AbstractDungeon.player.limbo.addToBottom(tmp);
            tmp.current_x = card.current_x;
            tmp.current_y = card.current_y;
            tmp.target_x = (float) Settings.WIDTH / 2.0F - 300.0F * Settings.scale;
            tmp.target_y = (float) Settings.HEIGHT / 2.0F;
            tmp.freeToPlayOnce = true;

            if (mo != null) {
                tmp.calculateCardDamage(mo);
            }

            tmp.purgeOnUse = true;
            AbstractDungeon.actionManager.cardQueue.add(new CardQueueItem(tmp, mo, card.energyOnUse, true));
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
