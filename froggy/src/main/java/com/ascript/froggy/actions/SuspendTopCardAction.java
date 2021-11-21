package com.ascript.froggy.actions;

import com.ascript.froggy.minions.SuspendFroggy;
import com.ascript.froggy.powers.SuspendPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;

public class SuspendTopCardAction extends AbstractGameAction {
    private AbstractCard card;

    public SuspendTopCardAction(AbstractCreature target) {
        duration = startDuration = Settings.ACTION_DUR_LONG;
        actionType = ActionType.WAIT;
        this.target = target;
        this.source = AbstractDungeon.player;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;

        if (duration == startDuration) {
            if (p.drawPile.isEmpty()) {
                if (p.discardPile.isEmpty()) {
                    if (target instanceof SuspendFroggy) {
                        ((SuspendFroggy) target).addMoves();
                    }
                } else {
                    addToTop(new SuspendTopCardAction(target));
                    addToTop(new EmptyDeckShuffleAction());
                }

                isDone = true;
                return;
            }

            card = p.drawPile.getTopCard();
            p.drawPile.removeCard(card);
            AbstractDungeon.getCurrRoom().souls.remove(card);
            p.limbo.addToBottom(card);
            card.current_y = -200.0F * Settings.scale;
            card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
            card.target_y = (float)Settings.HEIGHT / 2.0F;
            card.targetAngle = 0.0F;
            card.lighten(false);
            card.drawScale = 0.12F;
            card.targetDrawScale = 0.75F;
            card.unfadeOut();
            card.unhover();
            card.untip();
            card.stopGlowing();
        }

        tickDuration();

        if (isDone && card != null) {
            addToTop(new ApplyPowerAction(target, source, new SuspendPower(target, card, card.costForTurn)));
            addToTop(new ShowCardAction(card));
        }
    }
}
