package com.company;

import java.util.*;

public class Game {
    protected Board b = new Board();

    public Game() {

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
        String action = Helper.action(player.name + ": Enter an action!");
        int circledNumberCoordinate = b.actionToCoordinates(action, player.coordinates)[0];
        int[] temp = b.translateCoordinates(circledNumberCoordinate);
        String circledNumber = b.board[temp[0]][temp[1]];
        int[] coordinates = b.actionToCoordinates(action, player.coordinates, (int) circledNumber.charAt(0) - 9311);
        for(int coordinate : coordinates){
            int check = b.translateNumber(coordinate);
            if(check < 1 || check > 5) {
                decideWhatToDo(player);
                return;
            }
        }
        new Chip(player.coordinates);
        player.coordinates = coordinates[coordinates.length - 1];
        player.rounds--;
    }


}
