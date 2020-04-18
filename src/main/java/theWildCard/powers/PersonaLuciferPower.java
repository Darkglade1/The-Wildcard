package theWildCard.powers;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.powers.DexterityPower;
import com.megacrit.cardcrawl.powers.StrengthPower;
import theWildCard.WildcardMod;
import theWildCard.cards.Persona.Lucifer;
import theWildCard.util.TextureLoader;

import static theWildCard.WildcardMod.makePowerPath;


public class PersonaLuciferPower extends AbstractPower {
    public AbstractCreature source;

    public static final String POWER_ID = WildcardMod.makeID("PersonaLuciferPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;
    private static final int DEX_LOSS = Lucifer.DEX_LOSS;
    private boolean activated = false;
    private boolean active = true;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("LuciferPower84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("LuciferPower32.png"));

    public PersonaLuciferPower(final AbstractCreature owner, final AbstractCreature source) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.source = source;

        type = PowerType.BUFF;
        isTurnBased = false;

        //loads textures
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        description = DESCRIPTIONS[0] + DEX_LOSS + DESCRIPTIONS[1];
    }

    @Override
    public void atStartOfTurn() {
        activated = false;
    }

    @Override
    public void onRemove() {
        activated = false;
    }

    @Override
    public void onApplyPower(AbstractPower power, AbstractCreature target, AbstractCreature source) {
        if (power instanceof StrengthPower && power.amount > 0) {
            if(active) {
                active = false;
                //Prevents infinite loop
                this.addToTop(new AbstractGameAction() {
                    @Override
                    public void update() {
                        this.isDone = true;
                        active = true;
                    }
                });
                this.flash();
                AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(owner, owner, new StrengthPower(owner, power.amount), power.amount));
                //Player loses Dex the first time they use this effect each turn
                if (!activated) {
                    AbstractDungeon.actionManager.addToTop(new ApplyPowerAction(owner, owner, new DexterityPower(owner, -Lucifer.DEX_LOSS), -Lucifer.DEX_LOSS));
                    activated = true;
                }
            }
        }
    }
}
