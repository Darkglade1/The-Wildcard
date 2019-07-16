package theWildCard.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.cards.Persona.AbstractPersonaCard;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makeRelicOutlinePath;
import static theWildCard.WildcardMod.makeRelicPath;

public class BlankContractRelic extends CustomRelic {

    public static final String ID = WildcardMod.makeID("BlankContractRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("BlankContract.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("BlankContract.png"));

    private static final int triggerCap = 3;

    public BlankContractRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
        counter = 0;
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard instanceof AbstractPersonaCard && counter < triggerCap) {
            this.flash();
            counter++;
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }

    @Override
    public void onVictory() {
        counter = 0;
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
