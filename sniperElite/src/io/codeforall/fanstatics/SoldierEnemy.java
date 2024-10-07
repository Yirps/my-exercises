package io.codeforall.fanstatics;

import javax.swing.*;

public class SoldierEnemy extends Enemy {

    public SoldierEnemy(int health) {
        super.health = health;
        super.isDead = false;
    }

    @Override
    public void hit(int damage) {
        System.out.println("Soldier Enemy shot.");
        this.health -= damage;

        if (this.health <= 0) {
            this.isDead = true;
            System.out.println("Soldier Enemy died.");
        }
    }
}
