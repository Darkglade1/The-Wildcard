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

public class VelvetContractRelic extends CustomRelic {

    public static final String ID = WildcardMod.makeID("VelvetContractRelic");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("VelvetContract.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("VelvetContract.png"));

    public VelvetContractRelic() {
        super(ID, IMG, OUTLINE, RelicTier.STARTER, LandingSound.MAGICAL);
    }

    @Override
    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard instanceof AbstractPersonaCard) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new DrawCardAction(AbstractDungeon.player, 1));
        }
    }

//    @Override
//    public void obtain() {
//        if (AbstractDungeon.player.hasRelic(BlankContractRelic.ID)) {
//            for (int i = 0; i < AbstractDungeon.player.relics.size(); i++) {
//                if ((AbstractDungeon.player.relics.get(i)).relicId.equals(BlankContractRelic.ID)) {
//                    instantObtain(AbstractDungeon.player, i, true);
//                    break;
//                }
//            }
//        } else {
//            super.obtain();
//        }
//    }

//    @Override
//    public boolean canSpawn() {
//        return AbstractDungeon.player.hasRelic(BlankContractRelic.ID);
//    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
