package theWildCard.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWildCard.WildcardMod;
import theWildCard.cards.Persona.Loki;
import theWildCard.tags.Tags;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makePowerPath;


public class PersonaLokiPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = WildcardMod.makeID("PersonaLokiPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final int DRAW = Loki.DRAW_POWER;
    private static final int DRAW_COUNTER = Loki.DRAW_COUNTER;

    private int counter = 0;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("LokiPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("LokiPower32.png"));

    public PersonaLokiPower(final AbstractCreature owner, final AbstractCreature source) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void atStartOfTurn() {
        counter = 0;
        updateDescription();
    }

    @Override
    public void onAfterCardPlayed(AbstractCard card) {
        if (!card.hasTag(Tags.PERSONA)) {
            counter++;
            if (counter % DRAW_COUNTER == 0) {
                counter = 0;
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new DrawCardAction(owner, DRAW));
            }
            updateDescription();
        }
    }

    @Override
    public void onRemove() {
        counter = 0;
        updateDescription();
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + DRAW_COUNTER + DESCRIPTIONS[1] + DRAW + DESCRIPTIONS[2] + (DRAW_COUNTER - counter);
        if (DRAW_COUNTER - counter == 1) {
            description += DESCRIPTIONS[3];
        } else {
            description += DESCRIPTIONS[4];
        }
    }
}
