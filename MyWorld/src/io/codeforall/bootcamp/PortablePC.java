package io.codeforall.bootcamp;
public class PortablePC {

    private String brand;
    private String colour;
    private String keyboard;

    public PortablePC(String brand, String colour, String keyboard){
        this.brand = brand;
        this.colour = colour;
        this.keyboard = keyboard;
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

    public Boolean TurnOn() {
        Boolean on = ;
        if (on == false){
            on = true;
            System.out.println("Computer starting.");
            return on;
        } else {
            on = false;
            System.out.println("Computer shutting down.");
            return on;
        }
    }




}