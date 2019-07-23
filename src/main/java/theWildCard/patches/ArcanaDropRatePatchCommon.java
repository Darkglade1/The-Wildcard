package theWildCard.patches;

import com.evacipated.cardcrawl.modthespire.lib.ByRef;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.CardGroup;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import javassist.CtBehavior;
import theWildCard.cards.Arcana.AbstractArcanaCard;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "initializeCardPools"
)

// A patch that increases the drop rate of Arcana cards
public class ArcanaDropRatePatchCommon {
    @SpireInsertPatch(locator = Locator.class, localvars = {"commonCardPool", "c"})
    public static void increaseDropRate(AbstractDungeon instance, @ByRef CardGroup[] commonCardPool, AbstractCard c) {
        if (c instanceof AbstractArcanaCard) {
            commonCardPool[0].addToTop(c);
        }
    }
    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "addToTop");
            return new int[]{LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)[0]};
        }
    }
}
