package com.ascript.froggy.cards.mods;

import basemod.abstracts.AbstractCardModifier;
import com.ascript.froggy.interfaces.PlayFromSuspendSubscriber;
import com.megacrit.cardcrawl.actions.utility.UseCardAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public class SuspendMod extends AbstractCardModifier {
    @Override
    public boolean removeOnCardPlayed(AbstractCard card) {
        return true;
    }

    @Override
    public boolean removeAtEndOfTurn(AbstractCard card) {
        return true;
    }

    @Override
    public void onUse(AbstractCard card, AbstractCreature target, UseCardAction action) {
        if (card instanceof PlayFromSuspendSubscriber) {
            ((PlayFromSuspendSubscriber)card).useFromSuspend();
        }
    }

    @Override
    public AbstractCardModifier makeCopy() {
        return new SuspendMod();
    }
}
