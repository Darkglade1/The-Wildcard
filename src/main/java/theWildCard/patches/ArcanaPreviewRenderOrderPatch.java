package theWildCard.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePostfixPatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.screens.DiscardPileViewScreen;
import com.megacrit.cardcrawl.screens.DrawPileViewScreen;
import com.megacrit.cardcrawl.screens.MasterDeckViewScreen;
import com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import javassist.CtBehavior;

// A patch to force the Arcana preview to render last on various screens so the previews aren't covered up by as many things
public class ArcanaPreviewRenderOrderPatch {
    //prevents the banner from covering up Arcana previews in the card reward screen
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "render"
    )
    public static class CardRewardRenderOrderPatch {
        @SpirePostfixPatch()
        public static void changeRenderOrder(AbstractDungeon instance, SpriteBatch sb) {
            if (instance.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
                instance.cardRewardScreen.render(sb);
            }
        }
    }

    //prevents the scroll bar and sorter header in the master deck view from covering up the Arcana previews
    @SpirePatch(
            clz = MasterDeckViewScreen.class,
            method = "render"
    )
    public static class MasterDeckRenderOrderPatch {
        @SpirePostfixPatch()
        public static void changeRenderOrder(MasterDeckViewScreen instance, SpriteBatch sb) {
            AbstractDungeon.player.masterDeck.renderTip(sb);
        }
    }

    //prevents the scroll bar in the draw pile view from covering up the Arcana previews
    @SpirePatch(
            clz = DrawPileViewScreen.class,
            method = "render"
    )
    public static class DrawPileRenderOrderPatch {
        @SpireInsertPatch(locator = DrawPileRenderOrderPatch.Locator.class, localvars = {"hoveredCard", "drawPileCopy"})
        public static void changeRenderOrder(DrawPileViewScreen instance, SpriteBatch sb, AbstractCard hoveredCard, CardGroup drawPileCopy) {
            if (hoveredCard == null) {
                drawPileCopy.render(sb);
            } else {
                drawPileCopy.renderExceptOneCard(sb, hoveredCard);
                hoveredCard.renderHoverShadow(sb);
                hoveredCard.render(sb);
                hoveredCard.renderCardTip(sb);
            }
        }
        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(ScrollBar.class, "render");
                int[] lines = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
                lines[0]++;
                return lines;
            }
        }
    }

    //prevents the scroll bar in the discard pile view from covering up the Arcana previews
    @SpirePatch(
            clz = DiscardPileViewScreen.class,
            method = "render"
    )
    public static class DiscardPileRenderOrderPatch {
        @SpireInsertPatch(locator = DiscardPileRenderOrderPatch.Locator.class, localvars = {"hoveredCard"})
        public static void changeRenderOrder(DiscardPileViewScreen instance, SpriteBatch sb, AbstractCard hoveredCard) {
            if (hoveredCard == null) {
                AbstractDungeon.player.discardPile.render(sb);
            } else {
                AbstractDungeon.player.discardPile.renderExceptOneCard(sb, hoveredCard);
                hoveredCard.renderHoverShadow(sb);
                hoveredCard.render(sb);
                hoveredCard.renderCardTip(sb);
            }
        }
        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(ScrollBar.class, "render");
                int[] lines = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
                lines[0]++;
                return lines;
            }
        }
    }

    //prevents the scroll bar in the library from covering up Arcana previews
    @SpirePatch(
            clz = CardLibraryScreen.class,
            method = "render"
    )
    public static class CardLibraryRenderOrderPatch {
        @SpireInsertPatch(locator = CardLibraryRenderOrderPatch.Locator.class, localvars = {"visibleCards"})
        public static void changeRenderOrder(CardLibraryScreen instance, SpriteBatch sb, CardGroup visibleCards) {
            visibleCards.renderInLibrary(sb);
            visibleCards.renderTip(sb);
        }
        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(ScrollBar.class, "render");
                int[] lines = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
                lines[0]++;
                return lines;
            }
        }
    }
}
