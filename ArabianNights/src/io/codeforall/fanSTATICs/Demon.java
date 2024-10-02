package io.codeforall.fanSTATICs;

public class Demon extends Genie{

    private int wishGranted;

    public Demon() {
        super(0);
        wishGranted = 0;
    }
    public void makeWish() {
        System.out.println("         Wish granted");
    }
}
