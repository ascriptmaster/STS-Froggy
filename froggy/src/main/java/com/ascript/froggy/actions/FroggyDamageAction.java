package com.ascript.froggy.actions;

import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.vfx.combat.FlashAtkImgEffect;

public class FroggyDamageAction extends AbstractGameAction {
    private static final int FROGGY_TRESHOLD = 4;
    private DamageInfo info;
    private AttackEffect eff;

    public FroggyDamageAction(AbstractCreature target, DamageInfo info, AttackEffect atkEffect) {
        this.info = info;
        this.eff = atkEffect;
        setValues(target, info);
        this.actionType = ActionType.DAMAGE;
        this.startDuration = Settings.ACTION_DUR_FAST;
        this.duration = this.startDuration;
    }

    @Override
    public void update() {
        if (this.shouldCancelAction()) {
            this.isDone = true;
        } else {
            this.tickDuration();
            if (this.isDone) {
                AbstractDungeon.effectList.add(new FlashAtkImgEffect(this.target.hb.cX, this.target.hb.cY, eff, false));
                this.target.damage(this.info);
                if (this.target.lastDamageTaken > 0) {
                    addToTop(new SummonFroggiesAction(target.lastDamageTaken/FROGGY_TRESHOLD));
                }

                if (AbstractDungeon.getCurrRoom().monsters.areMonstersBasicallyDead()) {
                    AbstractDungeon.actionManager.clearPostCombatActions();
                } else {
                    this.addToTop(new WaitAction(0.1F));
                }
            }

        }
    }
}
