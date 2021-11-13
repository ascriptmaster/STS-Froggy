package com.ascript.froggy.actions;

import com.ascript.froggy.FroggyMod;
import com.ascript.froggy.powers.SuspendPower;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.ApplyPowerAction;
import com.megacrit.cardcrawl.cards.AbstractCard;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.core.CardCrawlGame;
import com.megacrit.cardcrawl.core.Settings;
import com.megacrit.cardcrawl.dungeons.AbstractDungeon;
import com.megacrit.cardcrawl.localization.UIStrings;
import kobting.friendlyminions.monsters.AbstractFriendlyMonster;

public class SuspendFromHandAction extends AbstractGameAction {
    private static final String ID = FroggyMod.makeID(SuspendFromHandAction.class.getSimpleName());
    private static UIStrings uiStrings = CardCrawlGame.languagePack.getUIString(ID);
    public SuspendFromHandAction(AbstractFriendlyMonster owner) {
        setValues(owner, owner);
        this.duration = this.startDuration = Settings.ACTION_DUR_FAST;
        this.actionType = ActionType.CARD_MANIPULATION;
    }

    @Override
    public void update() {
        AbstractPlayer p = AbstractDungeon.player;
        if (this.duration == this.startDuration) {
            if (p.hand.size() == 0) {
                this.isDone = true;
                return;
            }
            if (p.hand.size() == 1) {
                AbstractCard card = p.hand.getTopCard();
                p.hand.removeCard(card);
                addToTop(new ApplyPowerAction(target, target, new SuspendPower(target, card, card.costForTurn)));
                return;
            }

            AbstractDungeon.handCardSelectScreen.open(uiStrings.TEXT[0], 1, false, false);
        } else if (!AbstractDungeon.handCardSelectScreen.wereCardsRetrieved) {
            AbstractCard card = AbstractDungeon.handCardSelectScreen.selectedCards.getTopCard();
            p.hand.removeCard(card);
            addToTop(new ApplyPowerAction(target, target, new SuspendPower(target, card, card.costForTurn)));
            AbstractDungeon.handCardSelectScreen.wereCardsRetrieved = true;
        }
        this.tickDuration();
    }
}
