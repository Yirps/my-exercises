package io.codeforall.fanstatics;

public class Game {
    public GameObject[] gameObjects;
    public SniperRifle sniperRifle;
    public int shotsFired;

    public Game(int numberObjects, int rifleDamage) {
        this.gameObjects = createGameObjects(numberObjects);

        this.sniperRifle = new SniperRifle(rifleDamage);

        this.shotsFired = 0;
    }

    public GameObject[] createGameObjects(int numberObjects){
        GameObject[] gameObjects = new GameObject[numberObjects];
        for (int i = 0; i < numberObjects; i++) {
            double rand = Math.random();
            if (rand < 0.3) {
                gameObjects[i] = new ArmouredEnemy(100, 150);
            } else if (rand > 0.9) {
                gameObjects[i] = new Tree();
            } else if (rand < 0.7){
                gameObjects[i] = new SoldierEnemy(100);
            } else {
                gameObjects[i] = new Barrel(BarrelType.values()[(int)(Math.random() * BarrelType.values().length)]);
            }
        }
        return gameObjects;
    }

    public void start() {
        for (GameObject obj1 : this.gameObjects) {

            if (obj1 instanceof Tree) {
                System.out.println("Found tree. Ignoring...");
                System.out.println(" ");
                continue;
            } else if ((obj1 instanceof Enemy || obj1 instanceof Barrel) && !((Destroyable) obj1).isDestroyed()) {
                System.out.println(obj1.getMessage());
                while (!((Destroyable) obj1).isDestroyed()) {
                    this.sniperRifle.shoot((Destroyable) obj1);
                    this.shotsFired++;
                }
            }
        }
        System.out.println("Total shots fired: " + this.shotsFired);
    }


}
