package com.ascript.froggy.util;

import com.ascript.froggy.powers.SuspendPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import kobting.friendlyminions.helpers.BasePlayerMinionHelper;

public class MinionUtils {
    public static MonsterGroup playerMinions() {
        AbstractPlayer player = AbstractDungeon.player;
        MonsterGroup minions;
        if (player instanceof AbstractPlayerWithMinions) {
            minions = ((AbstractPlayerWithMinions)player).getMinions();
        } else {
            minions = BasePlayerMinionHelper.getMinions(player);
        }
        return minions;
    }

    public static long suspendCount() {
        return playerMinions().monsters.stream()
                .filter(m -> m.hasPower(SuspendPower.POWER_ID)
                        && ((SuspendPower)m.getPower(SuspendPower.POWER_ID)).card != null)
                .count();
    }
}
