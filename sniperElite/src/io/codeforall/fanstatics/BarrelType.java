package io.codeforall.fanstatics;

public enum BarrelType {
    PLASTIC(30),
    WOOD(50),
    METAL(100);

    private BarrelType(int maxDamage){
        this.maxDamage = maxDamage;
    }
    public int maxDamage;

    public int getMaxDamage(){
        return maxDamage;
    }



}



