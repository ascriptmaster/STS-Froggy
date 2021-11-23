package com.ascript.froggy.actions;

import com.ascript.froggy.minions.SuspendFroggy;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import kobting.friendlyminions.helpers.BasePlayerMinionHelper;

public class SummonFroggiesAction extends AbstractGameAction {
    public SummonFroggiesAction(int amt) {
        amount = amt;
        duration = startDuration = Settings.ACTION_DUR_FASTER;
    }

    @Override
    public void update() {
        if (duration == startDuration) {
            AbstractPlayer p = AbstractDungeon.player;
            for (int i = 0; i < amount; i++) {
                SuspendFroggy froggy = new SuspendFroggy();
                if (p instanceof AbstractPlayerWithMinions) {
                    AbstractPlayerWithMinions player = (AbstractPlayerWithMinions) p;
                    player.addMinion(froggy);
                } else {
                    BasePlayerMinionHelper.addMinion(p, froggy);
                }
                froggy.addMoves();
            }
            isDone = true;
        }
    }
}
