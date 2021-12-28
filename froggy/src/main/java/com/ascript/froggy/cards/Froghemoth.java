package com.ascript.froggy.cards;

import com.ascript.froggy.FroggyMod;
import com.ascript.froggy.characters.Froggy;
import com.megacrit.cardcrawl.actions.AbstractGameAction;
import com.megacrit.cardcrawl.actions.common.DamageAction;
import com.megacrit.cardcrawl.actions.common.ModifyDamageAction;
import com.megacrit.cardcrawl.cards.DamageInfo;
import com.megacrit.cardcrawl.characters.AbstractPlayer;
import com.megacrit.cardcrawl.monsters.AbstractMonster;

public class Froghemoth extends AbstractDynamicCard {
    public static final String ID = FroggyMod.makeID(Froghemoth.class.getSimpleName());
    public static final String IMG = FroggyMod.makeCardPath("Froghemoth.png");

    private static final CardRarity RARITY = CardRarity.UNCOMMON;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final CardType TYPE = CardType.ATTACK;
    public static final CardColor COLOR = Froggy.Enums.FROGGY_GREEN;

    private static final int COST = 2;
    private static final int DAMAGE = 15;
    private static final int MAGIC = 6;
    private static final int UPGRADE_PLUS_MAGIC = 4;

    public Froghemoth() {
        super(ID, IMG, COST, TYPE, COLOR, RARITY, TARGET);
        baseDamage = DAMAGE;
        magicNumber = baseMagicNumber = MAGIC;
    }

    @Override
    public void use(AbstractPlayer p, AbstractMonster m) {
        addToBot(new DamageAction(m, new DamageInfo(p, damage, damageTypeForTurn), AbstractGameAction.AttackEffect.BLUNT_HEAVY));
        addToBot(new ModifyDamageAction(uuid, magicNumber));
    }

    //Upgraded stats.
    @Override
    public void upgrade() {
        if (!upgraded) {
            upgradeName();
            upgradeMagicNumber(UPGRADE_PLUS_MAGIC);
            initializeDescription();
        }
    }
}