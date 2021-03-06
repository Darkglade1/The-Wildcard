package theWildCard.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWildCard.WildcardMod;
import theWildCard.cards.Persona.AbstractPersonaCard;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makePowerPath;
import static theWildCard.cards.Persona.AbstractPersonaCard.returnTrulyRandomPersona;


public class TheUniversePower extends AbstractPower {

    public static final String POWER_ID = WildcardMod.makeID("TheUniversePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("TheUniverse84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("TheUniverse32.png"));

    public TheUniversePower(AbstractCreature owner, int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        this.flash();
        for (int i = 0 ; i < amount; i++) {
            AbstractPersonaCard c = (AbstractPersonaCard)returnTrulyRandomPersona().makeCopy();
            AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, true));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
