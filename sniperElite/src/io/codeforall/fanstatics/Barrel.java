package io.codeforall.fanstatics;

public class Barrel extends GameObject implements Destroyable{
    private BarrelType barrelType;

    private int currentDamage;
    private boolean destroyed;

    public Barrel(BarrelType type){
        this.barrelType = type;
        this.currentDamage = 0;
        this.destroyed = false;
    }

    @Override
    public void hit(int damage){
        this.currentDamage += damage;
        if(this.currentDamage >= this.barrelType.maxDamage){
            System.out.println("Barrel destroyed.");
            System.out.println(" ");
            this.destroyed = true;
        }
    }

    @Override
    public boolean isDestroyed(){
        return this.destroyed;
    }

    @Override
    public String getMessage(){
        return "U found a " + barrelType.toString().toLowerCase() + " barrel.";
    }
}
