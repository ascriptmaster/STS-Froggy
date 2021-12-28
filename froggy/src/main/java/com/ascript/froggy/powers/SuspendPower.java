package com.ascript.froggy.powers;

import basemod.helpers.CardModifierManager;
import basemod.interfaces.CloneablePowerInterface;
import com.ascript.froggy.FroggyMod;
import com.ascript.froggy.cards.mods.SetXMod;
import com.ascript.froggy.cards.mods.SuspendMod;
import com.ascript.froggy.interfaces.SuspendSubscriber;
import com.ascript.froggy.minions.SuspendFroggy;
import com.megacrit.cardcrawl.actions.common.InstantKillAction;
import com.megacrit.cardcrawl.actions.common.ReducePowerAction;
import com.megacrit.cardcrawl.actions.common.RemoveSpecificPowerAction;
import com.megacrit.cardcrawl.actions.utility.NewQueueCardAction;
import com.megacrit.cardcrawl.actions.utility.UnlimboAction;
import com.megacrit.cardcrawl.actions.utility.WaitAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.helpers.FontHelper;
import com.megacrit.cardcrawl.localization.PowerStrings;
import com.megacrit.cardcrawl.powers.AbstractPower;
import com.megacrit.cardcrawl.ui.panels.EnergyPanel;

public class SuspendPower extends AbstractPower implements CloneablePowerInterface {
    public AbstractCard card;
    public SetXMod mod;

    public static final String POWER_ID = FroggyMod.makeID(SuspendPower.class.getSimpleName());
    private static final PowerStrings powerStrings = CardCrawlGame.languagePack.getPowerStrings(POWER_ID);
    public static final String NAME = powerStrings.NAME;
    public static final String[] DESCRIPTIONS = powerStrings.DESCRIPTIONS;

    public SuspendPower(final AbstractCreature owner, final AbstractCard card, final int amount) {
        name = NAME;
        ID = POWER_ID;

        this.owner = owner;
        this.card = card;
        this.amount = amount >= 0 ? amount : EnergyPanel.getCurrentEnergy();

        type = PowerType.BUFF;
        isTurnBased = true;

        this.loadRegion("stasis");

        updateDescription();
    }

    @Override
    public void onInitialApplication() {
        if (card.cost == -1) {
            mod = new SetXMod(amount);
            CardModifierManager.addModifier(card, mod);
        }
    }

    @Override
    public void onRemove() {
        if (card != null) {
            AbstractDungeon.player.discardPile.addToTop(card);
            if (mod != null) {
                CardModifierManager.removeSpecificModifier(card, mod, false);
            }
        }
        if (owner instanceof SuspendFroggy) {
            addToBot(new InstantKillAction(owner));
        }
    }

    @Override
    public void atStartOfTurnPostDraw() {
        if (amount > 1) {
            addToBot(new ReducePowerAction(owner, owner, this, 1));
        } else {
            playCard();
            card = null;
        }
    }

    @Override
    public void atEndOfTurn(boolean isPlayer) {
        if (amount <= 0) {
            playCard();
            card = null;
        }
    }

    private void playCard() {
        flash();
        AbstractDungeon.player.limbo.group.add(card);
        card.current_y = -200.0F * Settings.scale;
        card.target_x = (float)Settings.WIDTH / 2.0F + 200.0F * Settings.xScale;
        card.target_y = (float)Settings.HEIGHT / 2.0F;
        card.targetAngle = 0.0F;
        card.lighten(false);
        card.drawScale = 0.12F;
        card.targetDrawScale = 0.75F;
        CardModifierManager.addModifier(card, new SuspendMod());
        card.applyPowers();
        addToBot(new WaitAction(Settings.FAST_MODE ? Settings.ACTION_DUR_FASTER : Settings.ACTION_DUR_MED));
        addToBot(new UnlimboAction(card));
        addToBot(new NewQueueCardAction(card, true, false, true));
        addToBot(new RemoveSpecificPowerAction(owner, owner, this));
        for (AbstractPower power : AbstractDungeon.player.powers) {
            if (power instanceof SuspendSubscriber) {
                ((SuspendSubscriber)power).onPlaySuspended(owner, card);
            }
        }
    }

    @Override
    public void updateDescription() {
        if (amount <= 0) {
            description = DESCRIPTIONS[0];
        } else if (amount == 1) {
            description = DESCRIPTIONS[1];
        } else {
            description = DESCRIPTIONS[2] + amount + DESCRIPTIONS[3];
        }
        description += FontHelper.colorString(card.name, "y") + DESCRIPTIONS[4];
    }

    @Override
    public AbstractPower makeCopy() {
        return new SuspendPower(owner, card, amount);
    }
}
