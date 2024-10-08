package io.codeforall.fanstatics;

public enum BarrelType {
    PLASTIC(30, 60),
    WOOD(50, 100),
    METAL(100, 200);

    private BarrelType(int maxDamage, int damage){
        this.maxDamage = maxDamage;
        this.damage = damage;
    }
    public int maxDamage;
    private int damage;

    public int getMaxDamage(){
        return maxDamage;
    }



}



