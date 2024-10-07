package io.codeforall.fanstatics;

public abstract class Enemy extends GameObject {
    public int health;
    public boolean isDead;

    public void hit(int damage){
        this.health -= damage;
    }

    @Override
    public String getMessage(){
       return "I am an enemy.";
    }
}
