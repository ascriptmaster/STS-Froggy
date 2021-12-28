package com.ascript.froggy.cards;

import basemod.abstracts.CustomCard;
import com.ascript.froggy.FroggyMod;
import com.ascript.froggy.characters.Froggy;
import com.ascript.froggy.powers.SuspendPower;
import com.ascript.froggy.util.MinionUtils;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.monsters.MonsterGroup;

public class FroggyChair extends CustomCard {
    public static final String ID = FroggyMod.makeID(FroggyChair.class.getSimpleName());
    public static final String IMG = FroggyMod.makeCardPath("FroggyChair.png");
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Froggy.Enums.FROGGY_GREEN;

    private static final int COST = 2;
    private static final int UPGRADE_COST = 1;

    public FroggyChair() {
        super(ID, cardStrings.NAME, IMG, COST, cardStrings.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseMagicNumber = magicNumber = 1;
    }

    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeBaseCost(UPGRADE_COST);
        }
    }

    @Override
    public void use(AbstractPlayer abstractPlayer, AbstractMonster abstractMonster) {
        if (magicNumber <= 0) return;
        for (AbstractMonster m : MinionUtils.playerMinions().monsters) {
            if (m.hasPower(SuspendPower.POWER_ID) && m.getPower(SuspendPower.POWER_ID).amount > 0) {
                m.getPower(SuspendPower.POWER_ID).amount -= magicNumber;
            }
        }
    }
}
