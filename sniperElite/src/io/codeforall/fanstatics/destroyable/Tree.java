package io.codeforall.fanstatics;

public class Tree extends GameObject implements Destroyable{

    private boolean destroyed;
    private int maxDamage;
    private int currentDamage;

    public Tree(){
        this.maxDamage = 100;
    }

    @Override
    public void hit(int damage){
        this.currentDamage += damage;
        if(this.currentDamage >= this.maxDamage){
            setDestroyed();
        }
    }

    @Override
    public boolean isDestroyed(){
        return this.destroyed;
    }
    public void setDestroyed(){
        if(!this.destroyed) {
            System.out.println("Tree destroyed.");
            this.destroyed = true;
        }
    }
    @Override
    public String getMessage(){
        return "Tree found";
    }
}
