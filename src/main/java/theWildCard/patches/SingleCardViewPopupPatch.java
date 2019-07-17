package theWildCard.patches;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.evacipated.cardcrawl.modthespire.lib.LineFinder;
import com.evacipated.cardcrawl.modthespire.lib.Matcher;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertLocator;
import com.evacipated.cardcrawl.modthespire.lib.SpireInsertPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.screens.SingleCardViewPopup;
import javassist.CtBehavior;
import theWildCard.cards.Arcana.AbstractArcanaCard;

public class SingleCardViewPopupPatch {
    @SpirePatch(
            clz = SingleCardViewPopup.class,
            method = "render"
    )
    public static class RenderArcanaPreviewPatch {
        @SpireInsertPatch(locator = RenderArcanaPreviewPatch.Locator.class, localvars = {"card"})
        public static void AddArcanaPreview(SingleCardViewPopup instance, SpriteBatch sb, AbstractCard card) {
            AbstractArcanaCard arcanaCard = null;
            if (card instanceof AbstractArcanaCard) {
                arcanaCard = (AbstractArcanaCard)card;
            }
            AbstractCard priestessPreviewCard = null;
            AbstractCard emperorPreviewCard = null;
            AbstractCard foolPreviewCard = null;
            AbstractCard judgementPreviewCard = null;
            AbstractCard deathPreviewCard = null;
            float previewCardHeight = 0;
            
            if (arcanaCard != null) {
                priestessPreviewCard = arcanaCard.priestessCard.makeStatEquivalentCopy();
                emperorPreviewCard = arcanaCard.emperorCard.makeStatEquivalentCopy();
                foolPreviewCard = arcanaCard.foolCard.makeStatEquivalentCopy();
                judgementPreviewCard = arcanaCard.judgementCard.makeStatEquivalentCopy();
                deathPreviewCard = arcanaCard.deathCard.makeStatEquivalentCopy();
                previewCardHeight = priestessPreviewCard.hb.height;
                if (card.upgraded) {
                    priestessPreviewCard.upgrade();
                    emperorPreviewCard.upgrade();
                    foolPreviewCard.upgrade();
                    judgementPreviewCard.upgrade();
                    deathPreviewCard.upgrade();
                }
            }

            float drawScale = 0.75f;
            float xPosition1 = card.current_x + previewCardHeight * 0.7f;
            float xPosition2 = card.current_x + previewCardHeight * 1.7f;
            float xPosition3 = card.current_x + previewCardHeight * 1.2f;
            float yPosition1 = card.current_y + previewCardHeight * 0.65f;
            float yPosition2 = card.current_y + previewCardHeight * 1.8f;
            float yPosition3 = card.current_y + previewCardHeight * 2.95f;

            if (priestessPreviewCard != null) {
                priestessPreviewCard.drawScale = drawScale;
                priestessPreviewCard.current_x = xPosition1;
                priestessPreviewCard.current_y = yPosition1;
                priestessPreviewCard.render(sb);
            }

            if (emperorPreviewCard != null) {
                emperorPreviewCard.drawScale = drawScale;
                emperorPreviewCard.current_x = xPosition1;
                emperorPreviewCard.current_y = yPosition2;
                emperorPreviewCard.render(sb);
            }
            if (foolPreviewCard != null) {
                foolPreviewCard.drawScale = drawScale;
                foolPreviewCard.current_x = xPosition3;
                foolPreviewCard.current_y = yPosition3;
                foolPreviewCard.render(sb);
            }

            if (judgementPreviewCard != null) {
                judgementPreviewCard.drawScale = drawScale;
                judgementPreviewCard.current_x = xPosition2;
                judgementPreviewCard.current_y = yPosition2;
                judgementPreviewCard.render(sb);
            }
            if (deathPreviewCard != null) {
                deathPreviewCard.drawScale = drawScale;
                deathPreviewCard.current_x = xPosition2;
                deathPreviewCard.current_y = yPosition1;
                deathPreviewCard.render(sb);
            }
        }
        private static class Locator extends SpireInsertLocator {
            @Override
            public int[] Locate(CtBehavior ctMethodToPatch) throws Exception {
                Matcher finalMatcher = new Matcher.MethodCallMatcher(SingleCardViewPopup.class, "renderTips");
                int[] lines = LineFinder.findInOrder(ctMethodToPatch, finalMatcher);
                lines[0] ++;
                return lines;
            }
        }
    }
}
