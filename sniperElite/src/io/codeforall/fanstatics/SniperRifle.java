package io.codeforall.fanstatics;

public class SniperRifle {
    public int bulletDamage;

    public SniperRifle(int damage){
        this.bulletDamage = damage;
    }

    public void shoot(Enemy enemy){
        if(Math.random() > 0.8){
            System.out.println("Shot missed. -----------------------");
            return;
        }
        enemy.hit(this.bulletDamage);
        System.out.println("Shot on the target.");
    }
}
