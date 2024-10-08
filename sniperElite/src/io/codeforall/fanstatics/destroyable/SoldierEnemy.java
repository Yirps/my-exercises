package io.codeforall.fanstatics.destroyable;

import io.codeforall.fanstatics.destroyable.Enemy;

public class SoldierEnemy extends Enemy {

    public SoldierEnemy(int health) {
        super.health = health;
        super.isDead = false;
    }

    @Override
    public void hit(int damage) {
        this.health -= damage;

        if (this.health <= 0) {
            this.isDead = true;
            System.out.println("Soldier Enemy died.");
            System.out.println(" ");
        }
    }

    @Override
    public void setDestroyed(){
        if(!this.isDead) {
            this.isDead = true;
            System.out.println("Soldier dead.");
        }
    }

    @Override
    public String getMessage(){
        return "U found an enemy.";
    }
}
