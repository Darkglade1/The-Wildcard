package theWildCard.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.MakeTempCardInHandAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.cards.Persona.AbstractPersonaCard;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makeRelicOutlinePath;
import static theWildCard.WildcardMod.makeRelicPath;
import static theWildCard.cards.Persona.AbstractPersonaCard.returnTrulyRandomPersona;

public class Evoker extends CustomRelic {

    public static final String ID = WildcardMod.makeID("Evoker");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Evoker.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Evoker.png"));

    private static final int NUM_CARDS = 3;

    public Evoker() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
        counter = 0;
    }

    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractPersonaCard) {
            ++this.counter;
            if (this.counter % NUM_CARDS == 0) {
                this.counter = 0;
                this.flash();
                AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
                AbstractPersonaCard c = (AbstractPersonaCard)returnTrulyRandomPersona().makeCopy();
                AbstractDungeon.actionManager.addToBottom(new MakeTempCardInHandAction(c, true));
            }
        }
    }




    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + NUM_CARDS + DESCRIPTIONS[1];
    }

}
