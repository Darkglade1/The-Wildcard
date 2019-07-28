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
import theWildCard.WildcardMod;
import theWildCard.cards.Persona.Satanael;
import theWildCard.tags.Tags;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makePowerPath;


public class PersonaSatanaelPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = WildcardMod.makeID("PersonaSatanaelPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    public static int RETAIN = Satanael.RETAIN;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("SatanaelPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("SatanaelPower32.png"));

    public PersonaSatanaelPower(final AbstractCreature owner, final AbstractCreature source) {
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
    public void onAfterCardPlayed(AbstractCard card) {
        if (!card.hasTag(Tags.PERSONA)) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new TempRetainCardPower(owner, RETAIN), RETAIN));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + RETAIN + DESCRIPTIONS[1];
    }
}
