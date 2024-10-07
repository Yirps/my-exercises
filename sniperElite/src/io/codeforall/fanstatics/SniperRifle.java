package io.codeforall.fanstatics;

public class SniperRifle {
    public int bulletDamage;

    public SniperRifle(int damage){
        this.bulletDamage = damage;
    }

    public void shoot(Destroyable object){
        if(Math.random() > 0.8){
            System.out.println("Shot missed.");
            return;
        }
        if(Math.random() < 0.2 && object instanceof Enemy){
            System.out.println("Headshot.");
            object.hit(this.bulletDamage * 2);
            return;
        }
        System.out.println("Shot on target.");
        object.hit(this.bulletDamage);
    }
}
