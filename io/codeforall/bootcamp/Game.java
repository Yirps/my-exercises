package io.codeforall.bootcamp;

public class Game {

    Player players = new Player();

    public String whoWon(String p1Move, String p2Move) {
        String winner = "";

        if (p1Move.equals(p2Move)) {
            System.out.println("Tie");
        }

        if (p1Move.equals("Rock") && p2Move.equals("Scissors")) {
            winner = "Player One";
        }

        if (p1Move.equals("Paper") && p2Move.equals("Rock")) {
            winner = "Player One";
        }

        if (p1Move.equals("Scissors") && p2Move.equals("Paper")) {
            winner = "Player One";
        }

        if (p2Move.equals("Rock") && p1Move.equals("Scissors")) {
            winner = "Player Two";
        }

        if (p2Move.equals("Paper") && p1Move.equals("Rock")) {
            winner = "Player Two";
        }

        if (p2Move.equals("Scissors") && p1Move.equals("Paper")) {
            winner = "Player Two";
        }



        // Finish

        return winner;

    }

    public String playGame(){
        int p1Wins = 0;
        int p2Wins = 0 ;
        String winner = "";

        while(p1Wins < 2 && p2Wins < 2){
            String result = whoWon(players.player1Move(), players.player2Move());
            if (result.equals("Player One")){
                p1Wins ++;
                if(p1Wins == 2){
                    winner = result;
                }
            }
            else if (result.equals("Player Two")){
                p2Wins ++;
                if(p2Wins == 2){
                    winner = result;
                }
            }
        }
        return winner;

//        If playerOneWins, ++ the playerOneWins number
//        If playerTwoWins, ++ the playerTwoWins number
//        If playerOneWins = 2, return "Player One" and print that they won // the return statement will break the for loop
//        If playerOneWins = 2, return "Player One" print that they won // the return statement will break the for loop
    }
}


