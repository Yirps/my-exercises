package io.codeforall.bootcamp;

public class PortablePC {

    private String brand;
    private String colour;
    private String keyboard;
    private String os;
    private Boolean isOn;

    public PortablePC(String brand, String colour, String keyboard, String os) {
        this.brand = brand;
        this.colour = colour;
        this.keyboard = keyboard;
        this.os = os;
        this.isOn = false;
    }

    public PortablePC() {

    }

    public String getBrand() {
        return this.brand;
    }

    public void changeBrand(String newBrand) {
        if (newBrand.equals("hp") || newBrand.equals("Dell") || newBrand.equals("Apple")) {
            this.brand = newBrand;

        }
        System.out.println("That brand is out of stock.");
    }

    public String getColour() {
        return this.colour;
    }

    public void changeColour(String newColour) {
        if (newColour.equals("blue") || newColour.equals("white") || newColour.equals("red")) {
            this.colour = newColour;

        }
        System.out.println("That colour is not permitted");
    }

    public String getKeyboard() {
        return this.keyboard;
    }

    public void changeKeyboard(String newKeyboard) {
        if (newKeyboard.equals("EN")) {
            this.keyboard = newKeyboard;

        }
        System.out.println("We dont have that keyboard layout.");
    }

    public String getOs() {
        return this.os;
    }

    public void changeOs(String newOs) {
        if (newOs.equals("MacOs") || newOs.equals("Windows")) {
            this.os = newOs;

        }
        System.out.println("That brand is out of stock.");
    }

    public void turnOn() {
        if (!this.isOn) {
            this.isOn = true;
            System.out.println("Computer is on.");
        }
    }

    public void specifications() {
        if(this.isOn){
            System.out.println("Processor: Amd Ryzen 9.");
            System.out.println("RAM: 64GB 6400Mhz.");
            System.out.println("Graphics card: Geforce RTX 4090ti.");
        };
    }

    public void changeRGB() {
        if(this.isOn){
            int rgb = (int) (Math.random() * 7);

            switch(rgb){
                case 0:
                    System.out.println("yellow");
                    break;
                case 1:
                    System.out.println("orange");
                    break;
                case 2:
                    System.out.println("red");
                    break;
                case 3:
                    System.out.println("blue");
                    break;
                case 4:
                    System.out.println("purple");
                    break;
                case 5:
                    System.out.println("green");
                    break;
                case 6:
                    System.out.println("white");
                    break;
            }
        }
    }

    public void turnOff() {
        if (this.isOn) {
            this.isOn = false;
            System.out.println("Computer is shutting down.");
        }
    }
}