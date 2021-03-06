package theWildCard.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ScreenShake;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.vfx.combat.BlockImpactLineEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockedNumberEffect;
import com.megacrit.cardcrawl.vfx.combat.BlockedWordEffect;
import com.megacrit.cardcrawl.vfx.combat.DeckPoofEffect;
import com.megacrit.cardcrawl.vfx.combat.HbBlockBrokenEffect;
import com.megacrit.cardcrawl.vfx.combat.StrikeEffect;

public class KillAction extends AbstractGameAction {

    public KillAction(AbstractMonster m) {
        this.m = m;
    }

    private AbstractMonster m;

    public void update() {
        if (m.id.equals("Darkling") || m.id.equals("AwakenedOne")) {
            //band-aid fix for Darklings and the Awakened One due to their unique death effects
            m.damage(new DamageInfo(m, 9999, DamageInfo.DamageType.HP_LOSS));
        } else {
            m.currentHealth = 0;
            loseHP(9999);
            m.die();
        }

        if (AbstractDungeon.getMonsters().areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.cleanCardQueue();
            AbstractDungeon.effectList.add(new DeckPoofEffect(64.0F * Settings.scale, 64.0F * Settings.scale, true));
            AbstractDungeon.effectList.add(new DeckPoofEffect(Settings.WIDTH - 64.0F * Settings.scale, 64.0F * Settings.scale, false));
            AbstractDungeon.overlayMenu.hideCombatPanels();
        }

        if ((AbstractDungeon.getCurrRoom()).monsters.areMonstersBasicallyDead()) {
            AbstractDungeon.actionManager.clearPostCombatActions();
        }
        this.isDone = true;
    }


    private void loseHP(int damageAmount) {
        boolean hadBlock = (this.m.currentBlock != 0);
        boolean weakenedToZero = (damageAmount == 0);
        damageAmount = decrementBlock(damageAmount);

        if (damageAmount > 0) {
            this.m.useStaggerAnimation();
            if (damageAmount >= 99 && !CardCrawlGame.overkill) {
                CardCrawlGame.overkill = true;
            }

            this.m.currentHealth -= damageAmount;
            AbstractDungeon.effectList.add(new StrikeEffect(this.m, this.m.hb.cX, this.m.hb.cY, damageAmount));

            if (this.m.currentHealth < 0) {
                this.m.currentHealth = 0;
            }

            this.m.healthBarUpdatedEvent();

        } else if (weakenedToZero && this.m.currentBlock == 0) {
            if (hadBlock) {
                AbstractDungeon.effectList.add(new BlockedWordEffect(this.m, this.m.hb.cX, this.m.hb.cY, AbstractMonster.TEXT[30]));
            } else {
                AbstractDungeon.effectList.add(new StrikeEffect(this.m, this.m.hb.cX, this.m.hb.cY, 0));
            }
        } else if (Settings.SHOW_DMG_BLOCK) {
            AbstractDungeon.effectList.add(new BlockedWordEffect(this.m, this.m.hb.cX, this.m.hb.cY, AbstractMonster.TEXT[30]));
        }
    }


    private int decrementBlock(int damageAmount) {
        if (this.m.currentBlock > 0) {
            CardCrawlGame.screenShake.shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.SHORT, false);
            if (damageAmount > this.m.currentBlock) {
                damageAmount -= this.m.currentBlock;
                if (Settings.SHOW_DMG_BLOCK) {
                    AbstractDungeon.effectList.add(new BlockedNumberEffect(this.m.hb.cX, this.m.hb.cY + this.m.hb.height / 2.0F, Integer.toString(this.m.currentBlock)));
                }

                this.m.loseBlock();
                AbstractDungeon.effectList.add(new HbBlockBrokenEffect(this.m.hb.cX - this.m.hb.width / 2.0F + BLOCK_ICON_X, this.m.hb.cY - this.m.hb.height / 2.0F + BLOCK_ICON_Y));
                CardCrawlGame.sound.play("BLOCK_BREAK");

            } else if (damageAmount == this.m.currentBlock) {

                damageAmount = 0;
                this.m.loseBlock();
                AbstractDungeon.effectList.add(new BlockedWordEffect(this.m, this.m.hb.cX, this.m.hb.cY, AbstractMonster.TEXT[1]));
            } else {

                CardCrawlGame.sound.play("BLOCK_ATTACK");
                this.m.loseBlock(damageAmount);

                for (int i = 0; i < 18; i++) {
                    AbstractDungeon.effectList.add(new BlockImpactLineEffect(this.m.hb.cX, this.m.hb.cY));
                }

                if (Settings.SHOW_DMG_BLOCK) {
                    AbstractDungeon.effectList.add(new BlockedNumberEffect(this.m.hb.cX, this.m.hb.cY + this.m.hb.height / 2.0F, Integer.toString(damageAmount)));
                }
                damageAmount = 0;
            }
        }
        return damageAmount;
    }


    private static final float BLOCK_ICON_X = -14.0F * Settings.scale;
    private static final float BLOCK_ICON_Y = -14.0F * Settings.scale;

}
