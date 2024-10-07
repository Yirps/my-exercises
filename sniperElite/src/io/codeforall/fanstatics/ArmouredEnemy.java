package io.codeforall.fanstatics;

public class ArmouredEnemy extends Enemy{
    public int armour;

    public ArmouredEnemy(int health, int armour){
        this.health = health;
        this.isDead = false;
        this.armour = armour;
    }
    @Override
    public void hit(int damage){
        if(this.armour > damage){
            this.armour -= damage;
            return;
        } else if (this.armour > 0){
            this.health -= damage - this.armour;
            this.armour = 0;
            return;
        } else {
            this.health -= damage;
        }

        if(this.health <= 0){
            System.out.println("Armoured Enemy died.");
            System.out.println(" ");
            this.isDead = true;
        }

    }

    @Override
    public void setDestroyed(){
        if(!this.isDead) {
            this.isDead = true;
            System.out.println("Armoured soldier dead.");
        }
    }

    @Override
    public String getMessage(){
        return "U found an armoured enemy.";
    }
}
