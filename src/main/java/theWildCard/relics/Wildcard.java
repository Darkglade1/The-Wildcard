package theWildCard.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.actions.AnyCardFromDeckToHandAction;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makeRelicOutlinePath;
import static theWildCard.WildcardMod.makeRelicPath;

public class Wildcard extends CustomRelic {

    public static final String ID = WildcardMod.makeID("Wildcard");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Wildcard.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Wildcard.png"));

    private boolean activated = false;

    public Wildcard() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void atBattleStartPreDraw() {
        this.activated = false;
    }

    @Override
    public void atTurnStartPostDraw() {
        if (!this.activated) {
            this.activated = true;
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new AnyCardFromDeckToHandAction(1));
        }

    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
