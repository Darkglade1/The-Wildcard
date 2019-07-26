package theWildCard.relics;

import basemod.BaseMod;
import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import theWildCard.WildcardMod;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makeRelicOutlinePath;
import static theWildCard.WildcardMod.makeRelicPath;

public class VelvetContract extends CustomRelic {

    public static final String ID = WildcardMod.makeID("VelvetContract");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("VelvetContract.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("VelvetContract.png"));

    private static final int HAND_SIZE_INCREASE = 2;

    public VelvetContract() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void onEquip() { BaseMod.MAX_HAND_SIZE += HAND_SIZE_INCREASE; }

    @Override
    public void onUnequip() { BaseMod.MAX_HAND_SIZE -= HAND_SIZE_INCREASE; }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + HAND_SIZE_INCREASE + DESCRIPTIONS[1];
    }

}
