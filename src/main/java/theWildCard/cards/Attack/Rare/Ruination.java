package theWildCard.cards.Attack.Rare;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.VulnerablePower;
import theWildCard.WildcardMod;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.cards.Persona.AbstractPersonaCard;
import theWildCard.characters.WildcardCharacter;

import java.util.ArrayList;
import java.util.Iterator;

import static theWildCard.WildcardMod.makeCardPath;

public class Ruination extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(Ruination.class.getSimpleName());
    public static final String IMG = makeCardPath("Ruination.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 2;

    private static final int DAMAGE = 15;
    private static final int UPGRADE_PLUS_DMG = 5;

    private static final int VULNERABLE = 5;
    private static final int UPGRADE_PLUS_VULNERABLE = 1;

    public Ruination() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = VULNERABLE;
        exhaust = true;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(
                new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.SLASH_HEAVY));
        Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
        while(iterator.hasNext()) {
            AbstractMonster mo = (AbstractMonster)iterator.next();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new VulnerablePower(mo, magicNumber, false), magicNumber, true, AbstractGameAction.AttackEffect.NONE));
        }
        ArrayList<AbstractPersonaCard> cardsToRemove = new ArrayList<>();
        for (AbstractCard card : p.hand.group) {
            if (card instanceof AbstractPersonaCard) {
                cardsToRemove.add((AbstractPersonaCard)card);
            }
        }
        for (AbstractPersonaCard persona : cardsToRemove) {
            p.hand.moveToExhaustPile(persona);
        }
        cardsToRemove.clear();

        for (AbstractCard card : p.drawPile.group) {
            if (card instanceof AbstractPersonaCard) {
                cardsToRemove.add((AbstractPersonaCard)card);
            }
        }
        for (AbstractPersonaCard persona : cardsToRemove) {
            p.drawPile.moveToExhaustPile(persona);
        }
        cardsToRemove.clear();

        for (AbstractCard card : p.discardPile.group) {
            if (card instanceof AbstractPersonaCard) {
                cardsToRemove.add((AbstractPersonaCard)card);
            }
        }
        for (AbstractPersonaCard persona : cardsToRemove) {
            p.discardPile.moveToExhaustPile(persona);
        }
        cardsToRemove.clear();
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            upgradeMagicNumber(UPGRADE_PLUS_VULNERABLE);
            initializeDescription();
        }
    }
}
