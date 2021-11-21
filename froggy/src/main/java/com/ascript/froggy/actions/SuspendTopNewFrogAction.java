package com.ascript.froggy.actions;

import com.ascript.froggy.minions.SuspendFroggy;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import kobting.friendlyminions.helpers.BasePlayerMinionHelper;

public class SuspendTopNewFrogAction extends AbstractGameAction {
    public SuspendTopNewFrogAction() {
        duration = startDuration = Settings.ACTION_DUR_FAST;
        actionType = ActionType.WAIT;
        source = AbstractDungeon.player;
    }

    @Override
    public void update() {
        if (duration == startDuration) {
            AbstractPlayer p = AbstractDungeon.player;
            SuspendFroggy froggy = new SuspendFroggy();
            if (p instanceof AbstractPlayerWithMinions) {
                AbstractPlayerWithMinions player = (AbstractPlayerWithMinions) p;
                player.addMinion(froggy);
            } else {
                BasePlayerMinionHelper.addMinion(p, froggy);
            }
            addToTop(new SuspendTopCardAction(froggy));
            isDone = true;
        }
    }
}
