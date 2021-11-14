package com.ascript.froggy.util;

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
}
