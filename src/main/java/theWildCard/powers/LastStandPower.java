package theWildCard.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.screens.DeathScreen;
import theWildCard.WildcardMod;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makePowerPath;


public class LastStandPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = WildcardMod.makeID("LastStandPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("LastStand84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("LastStand32.png"));

    public LastStandPower(AbstractCreature owner, AbstractCreature source) {
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
    public void atStartOfTurnPostDraw () {
        AbstractPlayer p = AbstractDungeon.player;
        p.isDead = true;
        AbstractDungeon.deathScreen = new DeathScreen(AbstractDungeon.getMonsters());
        p.currentHealth = 0;
        if (p.currentBlock > 0) {
            p.loseBlock();
        }
    }

    @Override
    public void onRemove() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner,
                new LastStandPower(owner, owner), 0));
    }
}
