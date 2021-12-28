package com.ascript.froggy.cards;

import basemod.abstracts.CustomCard;
import com.ascript.froggy.FroggyMod;
import com.ascript.froggy.characters.Froggy;
import com.ascript.froggy.powers.FrogCryonicsPower;
import com.ascript.froggy.powers.ToxicityPower;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class FrogCryonics extends CustomCard {
    public static final String ID = FroggyMod.makeID(FrogCryonics.class.getSimpleName());
    public static final String IMG = FroggyMod.makeCardPath("FrogCryonics.png");
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final CardRarity RARITY = CardRarity.RARE;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.POWER;
    public static final CardColor COLOR = Froggy.Enums.FROGGY_GREEN;

    private static final int COST = 1;

    private static final int MAGIC = 4;
    private static final int UPGRADE_MAGIC = 6;

    public FrogCryonics() {
        super(ID, cardStrings.NAME, IMG, COST, cardStrings.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        magicNumber = baseMagicNumber = MAGIC;
    }

    // Actions the card should do.
    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new ApplyPowerAction(p, p, new FrogCryonicsPower(p, magicNumber), magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_MAGIC);
            initializeDescription();
        }
    }
}
