package theWildCard.cards.Skill.Rare;

import com.megacrit.cardcrawl.actions.animations.VFXAction;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.utility.SFXAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.CollectorCurseEffect;
import theWildCard.WildcardMod;
import theWildCard.actions.KillAction;
import theWildCard.cards.AbstractDefaultCard;
import theWildCard.cards.Persona.AbstractPersonaCard;
import theWildCard.characters.WildcardCharacter;

import java.util.ArrayList;
import java.util.Iterator;

import static theWildCard.WildcardMod.makeCardPath;

public class Deicide extends AbstractDefaultCard {

    public static final String ID = WildcardMod.makeID(Deicide.class.getSimpleName());
    public static final String IMG = makeCardPath("Deicide.png");

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.ALL_ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = WildcardCharacter.Enums.COLOR_BLUE;

    private static final int COST = 2;

    private static final int BLOCK = 10;
    private static final int UPGRADE_PLUS_BLOCK = 5;

    private static final int THRESHOLD = 10;

    public Deicide() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        magicNumber = baseMagicNumber = THRESHOLD;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        AbstractDungeon.actionManager.addToBottom(new GainBlockAction(p, p, block));
        int personaCount = 0;
        for (AbstractCard card : p.exhaustPile.group) {
            if (card instanceof AbstractPersonaCard) {
                personaCount++;
            }
        }

        ArrayList<AbstractMonster> notDeadMonsters = new ArrayList<>();
        if (personaCount >= magicNumber) {
            Iterator iterator = AbstractDungeon.getCurrRoom().monsters.monsters.iterator();
            while(iterator.hasNext()) {
                AbstractMonster mo = (AbstractMonster)iterator.next();
                if (!mo.isDeadOrEscaped()) {
                    notDeadMonsters.add(mo);
                }
            }

            for (int i = 0; i < notDeadMonsters.size(); i++) {
                AbstractMonster mo = notDeadMonsters.get(i);
                //makes the special effects appear all at once for multiple monsters instead of one-by-one
                AbstractDungeon.actionManager.addToBottom(new SFXAction("MONSTER_COLLECTOR_DEBUFF"));
                if (i == notDeadMonsters.size() - 1) {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new CollectorCurseEffect(mo.hb.cX, mo.hb.cY), 2.0F));
                } else {
                    AbstractDungeon.actionManager.addToBottom(new VFXAction(new CollectorCurseEffect(mo.hb.cX, mo.hb.cY)));
                }
            }

            for (int i = 0; i < notDeadMonsters.size(); i++) {
                AbstractMonster mo = notDeadMonsters.get(i);
                AbstractDungeon.actionManager.addToBottom(new KillAction(mo));
            }
        }
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
