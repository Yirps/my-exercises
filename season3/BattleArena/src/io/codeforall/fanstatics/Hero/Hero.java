package io.codeforall.fanstatics.Hero;

import io.codeforall.fanstatics.Ability.Ability;

public abstract class Hero implements Ability {

    private int HP;
    private int Mana;
    private int Def;
    private Ability ability;

    protected Hero(int hp, int mana, int def, Ability ability) {
        HP = hp;
        Mana = mana;
        Def = def;
        this.ability = ability;
    }

    private void Attack() {

    }
}
