package theWildCard.cards.Persona;

import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import theWildCard.WildcardMod;
import theWildCard.characters.WildcardCharacter;
import theWildCard.powers.PersonaLokiPower;
import theWildCard.variables.ArcanaEnums;

import static theWildCard.WildcardMod.makeCardPath;

public class Loki extends AbstractPersonaCard {

    public static final String ID = WildcardMod.makeID(Loki.class.getSimpleName());
    public static final String IMG = makeCardPath("Attack.png");

    public static final int DRAW = 1;
    public static final int DRAW_POWER = 2;

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 0;

    public Loki() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET, ArcanaEnums.Arcana.FOOL, new PersonaLokiPower(AbstractDungeon.player, AbstractDungeon.player));
        magicNumber = baseMagicNumber = DRAW;
        defaultSecondMagicNumber = defaultBaseSecondMagicNumber = DRAW_POWER;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        super.use(p, m);
        if (AbstractPersonaCard.canChangePersona) {
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(p, DRAW));
        }
    }
}
