package io.codeforall.test;

public class Game {

    RandomGenerator numGenerator = new RandomGenerator();//creating a new random generator and assigning it to a variable
    Player player = new Player();                        //creating a new player and assigning it to a variable player

    public void compareNums() {//method that is gonna compare both numbers
        int botNum = numGenerator.numGenerat();//creating a variable that is gonna call the method numgenerator
        int playerGuess = player.playerGuess();//and store it

        while (playerGuess != botNum) {           //while that is gonna check if the numbers are the same
            System.out.println(playerGuess + " is not correct.");      //print the number and call the player till it is the same
            playerGuess = player.playerGuess();
        }

        System.out.println("Player won with the number " + playerGuess);
    }
}