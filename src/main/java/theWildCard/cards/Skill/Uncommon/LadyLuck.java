package theWildCard.cards.Skill.Uncommon;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.WildcardMod;
import theWildCard.actions.DiscardArcanaAction;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.cards.OnDiscardArcanaCard;
import theWildCard.characters.WildcardCharacter;

import static theWildCard.WildcardMod.makeCardPath;

public class LadyLuck extends AbstractDefaultCard implements OnDiscardArcanaCard {

    public static final String ID = WildcardMod.makeID(LadyLuck.class.getSimpleName());
    public static final String IMG = makeCardPath("LadyLuck.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 1;

    private static final int DRAW = 2;

    private static final int DAMAGE = 8;
    private static final int UPGRADE_PLUS_DMG = 3;

    private static final int DRAW_EFFECT = 2;

    public LadyLuck() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = DRAW;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DRAW_EFFECT;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, magicNumber));
        AbstractDungeon.actionManager.addToBottom(new DiscardArcanaAction(p, p, -1, false, false, this));
    }

    @Override
    public void onDiscardArcana() {
        AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, defaultSecondMagicNumber));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeDamage(UPGRADE_PLUS_DMG);
            initializeDescription();
        }
    }
}
