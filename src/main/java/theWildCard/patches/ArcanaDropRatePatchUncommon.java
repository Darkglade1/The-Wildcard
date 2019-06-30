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
import theWildCard.cards.AbstractArcanaCard;

@SpirePatch(
        clz = AbstractDungeon.class,
        method = "initializeCardPools"
)

// A patch that triples the drop rate of Arcana cards
public class ArcanaDropRatePatchUncommon {
    @SpireInsertPatch(locator = Locator.class, localvars = {"uncommonCardPool", "c"})
    public static void tripleDropRate(AbstractDungeon instance, @ByRef CardGroup[] uncommonCardPool, AbstractCard c) {
        if (c instanceof AbstractArcanaCard) {
            uncommonCardPool[0].addToTop(c);
            uncommonCardPool[0].addToTop(c);
        }
    }
    private static class Locator extends SpireInsertLocator {
        @Override
        public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
            Matcher finalMatcher = new Matcher.MethodCallMatcher(CardGroup.class, "addToTop");
            return new int[]{LineFinder.findAllInOrder(ctMethodToPatch, finalMatcher)[1]};
        }
    }
}
