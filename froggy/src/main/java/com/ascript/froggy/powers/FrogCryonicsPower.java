package com.ascript.froggy.powers;

import com.ascript.froggy.FroggyMod;
import com.ascript.froggy.interfaces.SuspendSubscriber;
import com.megacrit.cardcrawl.actions.common.GainBlockAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;

public class FrogCryonicsPower extends AbstractPower implements SuspendSubscriber {
    public static final String POWER_ID = FroggyMod.makeID(FrogCryonicsPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public FrogCryonicsPower(final AbstractCreature owner, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.amount = amount;

        type = PowerType.BUFF;

        this.loadRegion("like_water");

        updateDescription();
    }

    @Override
    public void onPlaySuspended(AbstractCreature owner, AbstractCard card) {
        addToBot(new GainBlockAction(AbstractDungeon.player, amount));
    }

    @Override
    public void updateDescription() {
        description = DESCRIPTIONS[0] + amount + DESCRIPTIONS[1];
    }
}
