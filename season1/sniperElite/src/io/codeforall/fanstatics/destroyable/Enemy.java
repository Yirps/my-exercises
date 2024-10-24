package io.codeforall.fanstatics.destroyable;

import io.codeforall.fanstatics.GameObject;
import io.codeforall.fanstatics.destroyable.Destroyable;

public abstract class Enemy extends GameObject implements Destroyable {
    public int health;
    public boolean isDead;

    @Override
    public void hit(int damage){
        this.health -= damage;
    }

    public boolean isDead(){
        return this.isDead;
    }

    @Override
    public boolean isDestroyed(){
        return this.isDead;
    }

    @Override
    public void setDestroyed(){
    }


}
