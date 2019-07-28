package theWildCard.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.ArtifactPower;
import theWildCard.WildcardMod;
import theWildCard.cards.Persona.Caesar;
import theWildCard.tags.Tags;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makePowerPath;


public class PersonaCaesarPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = WildcardMod.makeID("PersonaCaesarPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int ARTIFACT = Caesar.ARTIFACT;
    private static int ARTIFACT_COUNTER = Caesar.ARTIFACT_COUNTER;
    private int counter = 0;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("CaesarPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("CaesarPower32.png"));

    public PersonaCaesarPower(final AbstractCreature owner, final AbstractCreature source) {
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
            if (counter % ARTIFACT_COUNTER == 0) {
                counter = 0;
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new ArtifactPower(owner, ARTIFACT), ARTIFACT));
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
        description = DESCRIPTIONS[0] + ARTIFACT_COUNTER + DESCRIPTIONS[1] + ARTIFACT + DESCRIPTIONS[2] + (ARTIFACT_COUNTER - counter);
        if (ARTIFACT_COUNTER - counter == 1) {
            description += DESCRIPTIONS[3];
        } else {
            description += DESCRIPTIONS[4];
        }
    }
}
