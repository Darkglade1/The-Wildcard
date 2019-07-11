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
import com.megacrit.cardcrawl.screens.compendium.CardLibraryScreen;
import com.megacrit.cardcrawl.screens.mainMenu.ScrollBar;
import com.megacrit.cardcrawl.shop.ShopScreen;
import javassist.CtBehavior;
import theWildCard.cards.Arcana.AbstractArcanaCard;

import java.util.ArrayList;
import java.util.Iterator;

// A patch to force the Arcana preview to render last on various screens so the previews aren't covered up by as many things
public class ArcanaPreviewRenderOrderPatch {
    //prevents the banner from covering up Arcana previews in the card reward screen
    //Also prevents the preview from being covered up by the scroll bar and the top status bar containing HP, floor #, gold, potions, etc.
    @SpirePatch(
            clz = AbstractDungeon.class,
            method = "render"
    )
    public static class GeneralRenderOrderPatch {
        @SpirePostfixPatch()
        public static void changeRenderOrder(AbstractDungeon instance, SpriteBatch sb) {
            if (instance.screen == AbstractDungeon.CurrentScreen.CARD_REWARD) {
                instance.cardRewardScreen.render(sb);
            }
            if (instance.screen == AbstractDungeon.CurrentScreen.GAME_DECK_VIEW) {
                AbstractDungeon.player.drawPile.renderTip(sb);
            }
            if (instance.screen == AbstractDungeon.CurrentScreen.MASTER_DECK_VIEW) {
                AbstractDungeon.player.masterDeck.renderTip(sb);
            }
            if (instance.screen == AbstractDungeon.CurrentScreen.DISCARD_VIEW) {
                AbstractDungeon.player.discardPile.renderTip(sb);
            }
            if (instance.screen == AbstractDungeon.CurrentScreen.SHOP) {
                if (AbstractArcanaCard.shopCards != null) {
                    Iterator iterator = AbstractArcanaCard.shopCards.iterator();
                    while(iterator.hasNext()) {
                        AbstractCard c = (AbstractCard)iterator.next();
                        c.renderCardTip(sb);
                    }
                }
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

    //Extracts the shop cards so their card tips can be rendered last so Arcana preview isn't covered up by the top bar
    @SpirePatch(
            clz = ShopScreen.class,
            method = "render"
    )
    public static class ShopScreenRenderOrderPatch {
        @SpireInsertPatch(locator = ShopScreenRenderOrderPatch.Locator.class, localvars = {"coloredCards"})
        public static void changeRenderOrder(ShopScreen instance, SpriteBatch sb, ArrayList<AbstractCard> coloredCards) {
            AbstractArcanaCard.shopCards = coloredCards;
        }
        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(ShopScreen.class, "renderCardsAndPrices");
                return LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
            }
        }
    }
}
