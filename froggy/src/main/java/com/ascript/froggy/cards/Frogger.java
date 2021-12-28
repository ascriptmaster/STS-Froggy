package com.ascript.froggy.cards;

import basemod.abstracts.CustomCard;
import com.ascript.froggy.FroggyMod;
import com.ascript.froggy.characters.Froggy;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Frogger extends CustomCard {
    public static final String ID = FroggyMod.makeID(Frogger.class.getSimpleName());
    public static final String IMG = FroggyMod.makeCardPath("Frogger.png");
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Froggy.Enums.FROGGY_GREEN;

    private static final int COST = 1;
    private static final int BLOCK = 9;
    private static final int UPGRADE_PLUS_BLOCK = 3;

    public Frogger() {
        super(ID, cardStrings.NAME, IMG, COST, cardStrings.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, p, block));
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBlock(UPGRADE_PLUS_BLOCK);
            initializeDescription();
        }
    }
}
