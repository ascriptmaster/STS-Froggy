package com.ascript.froggy.interfaces;

import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.core.AbstractCreature;

public interface SuspendSubscriber {
    void onPlaySuspended(AbstractCreature owner, AbstractCard card);
}
