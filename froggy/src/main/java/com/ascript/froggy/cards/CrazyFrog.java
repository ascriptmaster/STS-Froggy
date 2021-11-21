package com.ascript.froggy.cards;

import com.ascript.froggy.FroggyMod;
import com.ascript.froggy.actions.SuspendTopNewFrogAction;
import com.ascript.froggy.characters.Froggy;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class CrazyFrog extends AbstractDynamicCard {
    public static final String ID = FroggyMod.makeID(CrazyFrog.class.getSimpleName());
    public static final String IMG = FroggyMod.makeCardPath("CrazyFrog.png");

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Froggy.Enums.FROGGY_GREEN;

    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;

    public CrazyFrog() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new SuspendTopNewFrogAction());
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
            initializeDescription();
        }
    }
}
