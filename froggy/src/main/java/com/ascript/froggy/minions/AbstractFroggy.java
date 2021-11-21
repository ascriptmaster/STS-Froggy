package com.ascript.froggy.minions;

import com.ascript.froggy.util.MinionUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import kobting.friendlyminions.helpers.BasePlayerMinionHelper;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;

import java.util.HashSet;
import java.util.Set;

public abstract class AbstractFroggy extends AbstractFriendlyMonster {
    private final int index;

    public AbstractFroggy(String name, String id, int maxHealth, float hb_x, float hb_y, float hb_w, float hb_h, String imgUrl) {
        super(name, id, maxHealth, hb_x, hb_y, hb_w, hb_h, imgUrl, getOffsetX(), getOffsetY());
        index = nextIndex();
    }

    private static int nextIndex() {
        MonsterGroup minions = MinionUtils.playerMinions();

        Set<Integer> usedIndices = new HashSet<>();
        for (AbstractMonster m : minions.monsters) {
            if (m instanceof AbstractFroggy) {
                usedIndices.add(((AbstractFroggy)m).index);
            }
        }

        int index = 0;
        while (usedIndices.contains(index)) {
            index++;
        }
        return index;
    }

    private static float getOffsetX() {
        return -750f + 100f * nextIndex();
    }

    private static float getOffsetY() {
        return 200f * (nextIndex() % 2);
    }
}
