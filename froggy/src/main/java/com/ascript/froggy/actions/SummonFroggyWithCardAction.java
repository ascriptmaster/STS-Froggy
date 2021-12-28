package com.ascript.froggy.actions;

import com.ascript.froggy.minions.SuspendFroggy;
import com.ascript.froggy.powers.SuspendPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.actions.common.EmptyDeckShuffleAction;
import com.megacrit.cardcrawl.actions.utility.ShowCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import kobting.friendlyminions.helpers.BasePlayerMinionHelper;

public class SummonFroggyWithCardAction extends AbstractGameAction {
    private AbstractCard card;
    private SuspendFroggy froggy;

    public SummonFroggyWithCardAction(AbstractCard c) {
        startDuration = duration = Settings.ACTION_DUR_LONG;
        source = AbstractDungeon.player;
        card = c;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (duration == startDuration) {
            AbstractDungeon.player.limbo.addToBottom(card);
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

            froggy = new SuspendFroggy();
            if (p instanceof AbstractPlayerWithMinions) {
                AbstractPlayerWithMinions player = (AbstractPlayerWithMinions) p;
                player.addMinion(froggy);
            } else {
                BasePlayerMinionHelper.addMinion(p, froggy);
            }
        }

        tickDuration();

        if (isDone && card != null) {
            addToTop(new ApplyPowerAction(froggy, p, new SuspendPower(froggy, card, card.costForTurn)));
            addToTop(new ShowCardAction(card));
        }
    }
}
