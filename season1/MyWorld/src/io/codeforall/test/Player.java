package io.codeforall.test;

public class Player {

    RandomGenerator numGenerator = new RandomGenerator();//created a variable that can access the class

    public int playerGuess() {
        int guess = numGenerator.numGenerat();//method that is gonna call the number generator and store it
        return guess;                         // on the guess variable
    }
}
