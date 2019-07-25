package theWildCard.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.actions.ExhaustPersonaAction;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makeRelicOutlinePath;
import static theWildCard.WildcardMod.makeRelicPath;

public class FigureOfDeath extends CustomRelic {

    public static final String ID = WildcardMod.makeID("FigureOfDeath");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("FigureOfDeath.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("FigureOfDeath.png"));

    public FigureOfDeath() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void onPlayerEndTurn() {
        AbstractPlayer p = AbstractDungeon.player;
        AbstractDungeon.actionManager.addToBottom(new ExhaustPersonaAction(p, p, p.hand.size()));
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
