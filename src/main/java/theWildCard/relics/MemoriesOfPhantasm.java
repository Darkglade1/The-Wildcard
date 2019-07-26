package theWildCard.relics;

import basemod.abstracts.CustomRelic;
import com.badlogic.gdx.graphics.Texture;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAllEnemiesAction;
import com.megacrit.cardcrawl.actions.common.RelicAboveCreatureAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import theWildCard.WildcardMod;
import theWildCard.tags.Tags;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makeRelicOutlinePath;
import static theWildCard.WildcardMod.makeRelicPath;

public class MemoriesOfPhantasm extends CustomRelic {

    public static final String ID = WildcardMod.makeID("MemoriesOfPhantasm");

    private static final Texture IMG = TextureLoader.getTexture(makeRelicPath("MemoriesOfPhantasm.png"));
    private static final Texture OUTLINE = TextureLoader.getTexture(makeRelicOutlinePath("MemoriesOfPhantasm.png"));

    private static final int DAMAGE = 2;

    public MemoriesOfPhantasm() {
        super(ID, IMG, OUTLINE, RelicTier.UNCOMMON, LandingSound.MAGICAL);
    }

    public void onCardDraw(AbstractCard drawnCard) {
        if (drawnCard.hasTag(Tags.ARCANA)) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new RelicAboveCreatureAction(AbstractDungeon.player, this));
            AbstractDungeon.actionManager.addToBottom(new DamageAllEnemiesAction(null, DamageInfo.createDamageMatrix(DAMAGE, true), DamageInfo.DamageType.THORNS, AbstractGameAction.AttackEffect.BLUNT_LIGHT));
        }
    }

    @Override
    public String getUpdatedDescription() {
        return DESCRIPTIONS[0] + DAMAGE + DESCRIPTIONS[1];
    }

}
