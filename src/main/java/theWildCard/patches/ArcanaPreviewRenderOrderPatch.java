package theWildCard.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "render"
)

// A patch to force the Arcana preview to render last on the card reward screen so the reward banner doesn't cover up the preview
public class ArcanaPreviewRenderOrderPatch {
    @SpirePostfixPatch()
    public static void changeRenderOrder(AbstractDungeon instance, SpriteBatch sb) {
        if (instance.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
            instance.cardRewardScreen.render(sb);
        }
    }
}
