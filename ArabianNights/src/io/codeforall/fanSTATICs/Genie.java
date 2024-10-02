package io.codeforall.fanSTATICs;

public class Genie {

    public int maxWish;
    public int wishGranted;


    public Genie(int maxWish) {
        this.maxWish = maxWish;
    }

    public static void summoned(int maxGenies, int rubNum, boolean odd, boolean) {

        if (maxGenies < rubNum) {
            Demon demon = new Demon();
            System.out.println("Demon summoned");
            //demon.makeWish();
            return;
        }
        if (!odd) {
            setOdd();
            FriendlyGenie friendlyGenie = new FriendlyGenie();
            System.out.println("FriendlyGenie summoned");
            // friendlyGenie.makeWish();
            return;
        }
        GrumpyGenie grumpyGenie = new GrumpyGenie();
        System.out.println("GrumpyGenie summoned");
        //grumpyGenie.makeWish();
        setOdd();
    }

    public void makeWish() {
        for (int i = 0; i < maxWish; i++) {

            if (this.maxWish > wishGranted) {
                System.out.println("         Wish granted");
                wishGranted++;
                return;
            }
            System.out.println("No more wishes");
        }
    }
}
