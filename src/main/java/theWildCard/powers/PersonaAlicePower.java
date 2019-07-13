package theWildCard.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import theWildCard.WildcardMod;
import theWildCard.cards.OnKillPowerCard;
import theWildCard.cards.Persona.Alice;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makePowerPath;


public class PersonaAlicePower extends AbstractPower implements OnKillPowerCard {
    public AbstractCreature source;

    public static final String POWER_ID = WildcardMod.makeID("PersonaAlicePower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static int ENERGY_GAIN = Alice.ENERGY_GAIN;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("AlicePower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("AlicePower32.png"));


    public PersonaAlicePower(final AbstractCreature owner, final AbstractCreature source) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        description = DESCRIPTIONS[0];
    }

    @Override
    public void onKill(boolean isMinion) {
        AbstractPlayer p = AbstractDungeon.player;
        p.gainEnergy(ENERGY_GAIN);
    }
}
