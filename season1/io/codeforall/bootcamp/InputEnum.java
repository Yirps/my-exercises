package io.codeforall.bootcamp;

public class InputEnum {

    public enum gameInput{
        ROCK("Rock"),
        PAPER("Paper"),
        SCISSORS("Scissors");

        private String input;

        gameInput(String input){
            this.input = input;
        }

        public String getInput(){
            return this.input;
        }

        public static String randomInput(){
            int randomizer = (int)(Math.random() * 3 + 1);
            String move = null;

            if (randomizer == 1){
                move = gameInput.ROCK.input;
            } else if (randomizer == 2) {
                move = gameInput.PAPER.input;
            } else if (randomizer == 3) {
                move = gameInput.SCISSORS.input;
            }
            return move;
        }
    }




}

/*create a rock paper scissors game without inputs and with  3 rounds

### Enum Class ###

Create a String randomMove method that gives a random enum possibility
    Math.random 1-3
    If 1 = return "Rock"
    If 2 = return "Paper"
    If 3 = return "Scissors"

### Player Class ###

Create a String playerOneMove method and a String playerTwoMove that each call the enum method and returns the name of the move along with a print statement of
what move the player did

### Game Class ###

Create a whoWon String method that takes 2 string inputs - p1Move and p2move

String whoWon(p1move, p2move){
if p1move === p2move = tie

}

    If statement or switch statement

    if playerOneMove === "Rock" and playerTwoMove === "Paper" then result = Player Two wins
    if playerOneMove === "Rock" and playerTwoMove === "Scissors" then result = Player One wins
    If playerOneMove === "Paper" and playerTwoMove === "Rock" then result = Player One wins
    If playerOneMove === "Paper" and playerTwoMove === "Scissors then result= Player Two wins
    If playerOneMove === "Scissors" and playerTwoMove === "Rock" then result = Player Two wins
    If playerOneMove === "Scissors" and playerTwoMove === "Paper" then result= Player One wins

    return result

Create a String playGameMethod

    Create empty int playerOneWins and playerTwoWins variables

    for three rounds
    call the whoWon method, passing in the gameMove() function for each of the parameters
    If playerOneWins, ++ the playerOneWins number
    If playerTwoWins, ++ the playerTwoWins number
    If playerOneWins = 2, return "Player One" and print that they won // the return statement will break the for loop
    If playerOneWins = 2, return "Player One" print that they won // the return statement will break the for loop
 */
