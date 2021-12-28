package com.ascript.froggy.cards;

import basemod.abstracts.CustomCard;
import com.ascript.froggy.FroggyMod;
import com.ascript.froggy.actions.SummonFroggiesAction;
import com.ascript.froggy.characters.Froggy;
import com.ascript.froggy.powers.SuspendPower;
import com.ascript.froggy.util.MinionUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class MILF extends CustomCard {
    public static final String ID = FroggyMod.makeID(MILF.class.getSimpleName());
    public static final String IMG = FroggyMod.makeCardPath("FroggyChair.png");
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Froggy.Enums.FROGGY_GREEN;

    private static final int COST = 1;
    private static final int MAGIC = 2;
    private static final int UPGRADE_MAGIC = 1;

    public MILF() {
        super(ID, cardStrings.NAME, IMG, COST, cardStrings.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = MAGIC;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        addToBot(new SummonFroggiesAction(magicNumber));
    }
}
