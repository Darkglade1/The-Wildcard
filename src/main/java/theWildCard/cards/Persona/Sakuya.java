package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.WeakPower;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaSakuyaPower;
import theWildCard.variables.ArcanaEnums;

import java.util.Iterator;

import static theWildCard.WildcardMod.makeCardPath;

public class Sakuya extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Sakuya.class.getSimpleName());
    public static final String IMG = makeCardPath("Sakuya.png");

    public static final int WEAK = 1;
    public static final int WEAK_POWER = 2;

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;
    private static final ArcanaEnums.Arcana ARCANA = ArcanaEnums.Arcana.PRIESTESS;

    private static final int COST = 0;

    public Sakuya() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ARCANA, new PersonaSakuyaPower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = WEAK;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = WEAK_POWER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        if (AbstractPersonaCard.canChangePersona) {
            Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while(iterator.hasNext()) {
                AbstractMonster mo = (AbstractMonster)iterator.next();
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(mo, p, new WeakPower(mo, this.magicNumber, false), this.magicNumber, true, AbstractGameAction.AttackEffect.NONE));
            }
        }
    }
}
