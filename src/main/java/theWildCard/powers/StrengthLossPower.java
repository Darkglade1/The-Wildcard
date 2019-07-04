package theWildCard.powers;

        import com.badlogic.gdx.graphics.Texture;
        import com.badlogic.gdx.graphics.g2d.TextureAtlas;
        import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
        import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
        import com.megacrit.cardcrawl.cards.AbstractCard;
        import com.megacrit.cardcrawl.core.AbstractCreature;
        import com.megacrit.cardcrawl.core.CardCrawlGame;
        import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
        import com.megacrit.cardcrawl.localization.PowerStrings;
        import com.megacrit.cardcrawl.powers.AbstractPower;
        import com.megacrit.cardcrawl.powers.StrengthPower;
        import theWildCard.WildcardMod;
        import theWildCard.util.TextureLoader;
        import static theWildCard.WildcardMod.makePowerPath;


public class StrengthLossPower extends AbstractPower {

    public static final String POWER_ID = WildcardMod.makeID("StrengthLossPower");
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    private static final Texture tex84 = TextureLoader.getTexture(makePowerPath("placeholder_power84.png"));
    private static final Texture tex32 = TextureLoader.getTexture(makePowerPath("placeholder_power32.png"));

    public StrengthLossPower(AbstractCreature owner, int newAmount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = newAmount;

        type = PowerType.DEBUFF;
        isTurnBased = false;

        //loads textures
        this.region128 = new TextureAtlas.AtlasRegion(tex84, 0, 0, 84, 84);
        this.region48 = new TextureAtlas.AtlasRegion(tex32, 0, 0, 32, 32);

        updateDescription();
    }

    @Override
    public void onAfterCardPlayed(AbstractCard usedCard) {
        if (usedCard.type == AbstractCard.CardType.ATTACK) {
            this.flash();
            AbstractDungeon.actionManager.addToBottom(new ApplyPowerAction(this.owner, this.owner, new StrengthPower(this.owner, -this.amount), -this.amount));
            AbstractDungeon.actionManager.addToBottom(new RemoveSpecificPowerAction(this.owner, this.owner, this.ID));
        }
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
