package com.ascript.froggy.cards;

import basemod.abstracts.CustomCard;
import com.ascript.froggy.FroggyMod;
import com.ascript.froggy.actions.SummonFroggyWithCardAction;
import com.ascript.froggy.characters.Froggy;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.cards.tempCards.Smite;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.localization.CardStrings;
import com.megacrit.cardcrawl.monsters.AbstractMonster;
import com.megacrit.cardcrawl.powers.watcher.MasterRealityPower;

public class AmphibianAngel extends CustomCard {
    public static final String ID = FroggyMod.makeID(AmphibianAngel.class.getSimpleName());
    public static final String IMG = FroggyMod.makeCardPath("AmphibianAngel.png");
    public static final CardStrings cardStrings = CardCrawlGame.languagePack.getCardStrings(ID);

    private static final CardRarity RARITY = CardRarity.COMMON;
    private static final CardTarget TARGET = CardTarget.SELF;
    private static final CardType TYPE = CardType.SKILL;
    public static final CardColor COLOR = Froggy.Enums.FROGGY_GREEN;

    private static final int COST = 2;
    private static final int BLOCK = 12;
    private static final int UPGRADE_PLUS_BLOCK = 4;

    public AmphibianAngel() {
        super(ID, cardStrings.NAME, IMG, COST, cardStrings.DESCRIPTION, TYPE, COLOR, RARITY, TARGET);
        baseBlock = BLOCK;
        cardsToPreview = new Smite();
    }

    @Override
    public void upgrade() {
        upgradeBaseCost(UPGRADE_PLUS_BLOCK);
        initializeDescription();
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new GainBlockAction(p, block));

        AbstractCard card = new Smite();
        if (p.hasPower(MasterRealityPower.POWER_ID)) {
            card.upgrade();
        }
        addToBot(new SummonFroggyWithCardAction(card));
    }
}
