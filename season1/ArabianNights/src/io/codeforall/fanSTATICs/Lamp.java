package io.codeforall.fanSTATICs;

public class Lamp {

    // MAKE COMPARE ----------------
    private int maxGenies;
    private int rubNum;
    private static boolean odd = true;


    public Lamp(int maxGenies) {
        this.maxGenies = maxGenies;
    }

    public boolean getOdd(){
        return odd;
    }

    public boolean setOdd() {
        return odd = !odd;
    }

    public  Genie rubLamp(int maxWish ) {
        rubNum++;
        System.out.print("Rub: " + rubNum + " - ");
        Genie.summoned(maxGenies, rubNum);
        return new Genie(maxWish);
    }

    public void recycleDemon() {
        System.out.println("         Demon recycled");
        rubNum = 0;
    }
}
