package com.ascript.froggy.minions;

import com.ascript.froggy.FroggyMod;
import com.ascript.froggy.actions.SuspendFromHandAction;
import com.ascript.froggy.powers.SuspendPower;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.ImageMaster;
import com.megacrit.cardcrawl.localization.MonsterStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import kobting.friendlyminions.characters.AbstractPlayerWithMinions;
import kobting.friendlyminions.helpers.BasePlayerMinionHelper;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;
import kobting.friendlyminions.monsters.MinionMove;

import java.util.HashSet;
import java.util.Set;

import static com.megacrit.cardcrawl.core.CardCrawlGame.languagePack;

public class SuspendFroggy extends AbstractFroggy {
    public static final String ID = FroggyMod.makeID(SuspendFroggy.class.getSimpleName());
    private static final MonsterStrings mStrings = languagePack.getMonsterStrings(ID);
    private static final String NAME = mStrings.NAME;
    private static final String[] moves = mStrings.MOVES;
    private static final String IMG = "froggyResources/images/monsters/froghelper.png";

    public SuspendFroggy() {
        super(NAME, ID, 5, 0.0f, 0, 100, 76, IMG);
    }

    @Override
    public void applyEndOfTurnTriggers() {
        super.applyEndOfTurnTriggers();
        if (hasPower(SuspendPower.POWER_ID)) {
            removeMove("Suspend");
        }
    }

    public void addMoves() {
        addMove(new MinionMove("Suspend", this, ImageMaster.INTENT_BUFF_L, moves[0], () -> {
            addToBot(new SuspendFromHandAction(this));
        }));
    }


}
