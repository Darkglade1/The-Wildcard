package theWildCard.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.cards.Persona.AbstractPersonaCard;
import theWildCard.tags.Tags;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makeRelicOutlinePath;
import static theWildCard.WildcardMod.makeRelicPath;

public class FigureOfDeath extends CustomRelic {

    public static final String ID = WildcardMod.makeID("FigureOfDeath");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("FigureOfDeath.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("FigureOfDeath.png"));

    public FigureOfDeath() {
        super(ID, IMG, OUTLINE, RelicTier.BOSS, LandingSound.MAGICAL);
    }

    @Override
    public void onUseCard(AbstractCard card, UseCardAction action) {
        if (card.hasTag(Tags.PERSONA)) {
            this.flash();
            AbstractPersonaCard.canChangePersona = false;
        }
    }

    @Override
    public void atTurnStart() {
        if (!AbstractDungeon.player.hasPower("theWildCard:AttunementPower")) {
            AbstractPersonaCard.canChangePersona = true;
        }
    }

    @Override
    public void onEquip() {
        ++AbstractDungeon.player.energy.energyMaster;
    }

    @Override
    public void onUnequip() {
        --AbstractDungeon.player.energy.energyMaster;
        if (!AbstractDungeon.player.hasPower("theWildCard:AttunementPower")) {
            AbstractPersonaCard.canChangePersona = true; //In case some effect makes you drop this relic
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0];
    }

}
