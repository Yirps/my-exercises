package io.codeforall.bootcamp;

public class Player {

    InputEnum randomMove = new InputEnum();

    public String player1Move(){
        return InputEnum.gameInput.randomInput();
    }

    public String player2Move(){
        return InputEnum.gameInput.randomInput();
    }
}
