package com.ascript.froggy.cards.mods;

import basemod.abstracts.AbstractCardModifier;
import basemod.interfaces.XCostModifier;
import com.megacrit.cardcrawl.cards.AbstractCard;

// Thanks to Jobby for the code
public class SetXMod extends AbstractCardModifier implements XCostModifier {
    private int amt;

    public SetXMod(int amt) {
        this.amt = amt;
    }

    //method is expecting a modification to the X, return 2 to add 2 to X like chemical X, so we have to do something a little different.
    public int modifyX(AbstractCard c) {
        return -(c.energyOnUse - amt);
        //example: energyOnUse is 5, amt is 3, 5 - 3 = 2, 5 - 2 = 3
    }

    public boolean removeOnCardPlayed(AbstractCard c) {
        return true;
    }

    public AbstractCardModifier makeCopy() {
        return new SetXMod(amt);
    }
}
