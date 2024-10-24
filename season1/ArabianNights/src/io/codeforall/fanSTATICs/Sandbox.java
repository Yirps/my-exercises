package io.codeforall.fanSTATICs;

public class Sandbox {
    public static void main(String[] args) {
        Lamp lamp = new Lamp(3);
        Lamp lamp1 = new Lamp(7);

       Genie leandro = lamp1.rubLamp(2);
        leandro.makeWish();

        lamp1.rubLamp(2);
        lamp1.rubLamp(2);
        lamp1.rubLamp(2);
        lamp1.rubLamp(2);
        lamp1.rubLamp(2);
        lamp1.rubLamp(2);
        lamp1.rubLamp(2);
        lamp1.rubLamp(2);

        System.out.println("--------------------------------------");

        lamp.rubLamp(5);
        lamp.rubLamp(3);
        lamp.rubLamp(7);
        lamp.rubLamp(10);
        lamp.rubLamp(11);
        lamp.rubLamp(11);
        lamp.rubLamp(11);

        lamp.recycleDemon();
        lamp.rubLamp(4);
        lamp.rubLamp(4);
    }
}
