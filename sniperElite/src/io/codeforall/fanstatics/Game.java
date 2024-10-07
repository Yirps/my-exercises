package io.codeforall.fanstatics;

public class Game {
    public GameObject[] gameObjects;
    public SniperRifle sniperRifle;
    public int shotsFired;

    public Game(int rifleDamage) {
        this.gameObjects = new GameObject[10];
        for (int i = 0; i < this.gameObjects.length; i++) {
            double rand = Math.random();
            if (rand < 0.3) {
                gameObjects[i] = new ArmouredEnemy(100, 20);
            } else if (rand > 0.8) {
                gameObjects[i] = new Tree();
            } else {
                gameObjects[i] = new SoldierEnemy(100);
            }
        }

        this.sniperRifle = new SniperRifle(rifleDamage);

        this.shotsFired = 0;
    }

    public void start(){
        for(GameObject obj1 : this.gameObjects){
            if(obj1 instanceof Tree){
                System.out.println("Found tree. Ignoring...");
                continue;
            } else if (obj1 instanceof Enemy && !((Enemy) obj1).isDead){
                for(GameObject obj2 : this.gameObjects){
                    if(obj1 == obj2){
                        continue;
                    }
                    if(obj2 instanceof Enemy){
                        while(!((Enemy) obj2).isDead){
                            ((Enemy) obj2).hit(this.sniperRifle.bulletDamage);
                            this.shotsFired++;
                            System.out.println("Shot fired. Total shots fired: " + this.shotsFired);
                        }
                    }
                }
            }
        }
    }
}
