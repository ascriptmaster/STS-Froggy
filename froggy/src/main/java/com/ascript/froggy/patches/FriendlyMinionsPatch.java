package com.ascript.froggy.patches;

import com.ascript.froggy.FroggyMod;
import com.ascript.froggy.minions.AbstractFroggy;
import com.ascript.froggy.util.MinionUtils;
import com.evacipated.cardcrawl.modthespire.lib.SpirePatch;
import com.evacipated.cardcrawl.modthespire.lib.SpirePrefixPatch;
import com.evacipated.cardcrawl.modthespire.lib.SpireReturn;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;
import kobting.friendlyminions.patches.MonsterSetMovePatch;

@SpirePatch(
        clz = MonsterSetMovePatch.class,
        method = "maybeChangeIntent",
        paramtypez = {AbstractMonster.class, AbstractMonster.Intent.class, byte.class, int.class, int.class, boolean.class}
)
public class FriendlyMinionsPatch {
    @SpirePrefixPatch
    public static SpireReturn Prefix(AbstractMonster monster, AbstractMonster.Intent possibleNewIntent, byte nextMove,
                                     int intentBaseDmg, int multiplier, boolean isMultiDamage) throws IllegalAccessException {
        FroggyMod.logger.info("Attempting to prefix FriendlyMinions.maybeChangeIntent");
        MonsterGroup minions = MinionUtils.playerMinions();
        if (minions.monsters.stream().allMatch(m -> m instanceof AbstractFroggy)) {
            FroggyMod.logger.info("Only froggies found. Exiting.");
            return SpireReturn.Return();
        }

        return SpireReturn.Continue();
    }
}
