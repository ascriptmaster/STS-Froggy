package com.ascript.froggy.cards;

import basemod.abstracts.CustomCard;
import com.ascript.froggy.FroggyMod;
import com.ascript.froggy.actions.SummonFroggiesAction;
import com.ascript.froggy.characters.Froggy;
import com.ascript.froggy.util.MinionUtils;
import com.megacrit.cardcrawl.actions.common.DrawCardAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FrogCall extends CustomCard {
    public static final String ID = FroggyMod.makeID(FrogCall.class.getSimpleName());
    public static final String IMG = FroggyMod.makeCardPath("Skill.png");
    public static final CardStrings strings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final CardRarity RARITY = CardRarity.BASIC;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Froggy.Enums.FROGGY_GREEN;

    private static final int COST = 1;
    private static final int UPGRADE_COST = 0;

    public FrogCall() {
        super(ID, strings.NAME, IMG, COST, strings.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        if (MinionUtils.playerMinions().monsters.size() == 0) {
            addToBot(new SummonFroggiesAction(1));
        } else {
            addToBot(new DrawCardAction(1));
        }
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
