package theWildCard.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.cards.Persona.AbstractPersonaCard;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makeRelicOutlinePath;
import static theWildCard.WildcardMod.makeRelicPath;

public class Evoker extends CustomRelic {

    public static final String ID = WildcardMod.makeID("Evoker");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("Evoker.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("Evoker.png"));

    private static final int BLOCK = 3;

    public Evoker() {
        super(ID, IMG, OUTLINE, RelicTier.RARE, LandingSound.MAGICAL);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card instanceof AbstractPersonaCard) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new GainBlockAction(AbstractDungeon.player, AbstractDungeon.player, BLOCK));
        }
    }


    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + BLOCK + DESCRIPTIONS[1];
    }

}
