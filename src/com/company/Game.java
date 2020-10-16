package com.company;

import java.util.*;

public class Game {

    public Game() {
        Board b = new Board();
        decideNames();
        while(true) {
            for(Player player : Player.players) {
                b.drawBoard();
                decideWhatToDo(player);
            }
        }

    }

    public void decideNames(){
        Player player1 = new Player(Helper.enterName("Red Player: Please enter your name."));
        Player player2 = new Player(Helper.enterName("Cyan Player: Please enter your name."));
        Player player3 = new Player(Helper.enterName("Blue Player: Please enter your name."));
        Player player4 = new Player(Helper.enterName("Yellow Player: Please enter your name."));
    }

    public void decideWhatToDo(Player player){

    }


}
