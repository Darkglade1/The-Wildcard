package theWildCard.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import theWildCard.WildcardMod;
import theWildCard.cards.Persona.Polydeuces;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makePowerPath;

public class PersonaPolydeucesPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = WildcardMod.makeID("PersonaPolydeucesPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("PolydeucesPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("PolydeucesPower32.png"));

    public PersonaPolydeucesPower(final AbstractCreature owner, final AbstractCreature source) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        description = DESCRIPTIONS[0] + Polydeuces.DEXTERITY + DESCRIPTIONS[1];
    }

    @Override
    public void onRemove() {
        AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(owner, owner, new DexterityPower(owner, -Polydeuces.DEXTERITY), -Polydeuces.DEXTERITY));
    }
}
