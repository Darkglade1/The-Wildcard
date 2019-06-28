package theWildCard.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.DefaultMod;
import theWildCard.cards.AbstractPersonaCard;
import theWildCard.util.TextureLoader;

import static theWildCard.DefaultMod.makeRelicOutlinePath;
import static theWildCard.DefaultMod.makeRelicPath;

public class VelvetContractRelic extends CustomRelic {

    public static final String ID = DefaultMod.makeID("VelvetContractRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("placeholder_relic.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("placeholder_relic.png"));

    public VelvetContractRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard instanceof AbstractPersonaCard) {
            this.flash();
            AbstractDungeon.player.draw(1);
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
