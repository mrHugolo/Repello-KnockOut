package com.company;

import java.util.*;

public class Game {
    protected Board b = new Board();

    public Game() {

        decideNames();
        while (!seeIfSomeoneWon()) {
            for (Player player : Player.players) {
                decideWhatToDo(player);
                reaction(player);
            }
        }
        System.exit(0);
    }

    public void decideNames() {
        Player player1 = new Player(Helper.enterName("Red Player: Please enter your name."));
        Player player2 = new Player(Helper.enterName("Cyan Player: Please enter your name."));
        Player player3 = new Player(Helper.enterName("Blue Player: Please enter your name."));
        Player player4 = new Player(Helper.enterName("Yellow Player: Please enter your name."));
    }

    public void decideWhatToDo(Player player) {
        if(seeIfSomeoneWon()) System.exit(0);
        if (!player.onBoard) {
            b.bringPlayerBack(player);
            reaction(player);
        }
        int chipCoordinates = player.coordinates;
        Helper.clear();
        b.drawBoard();
        String action = Helper.action(player.name + ": Enter an action!");
        int circledNumberCoordinate = b.actionToCoordinates(action, player.coordinates)[0];
        System.out.println(circledNumberCoordinate);
        if (circledNumberCoordinate == 200) {
            decideWhatToDo(player);
            return;
        }

        if (circledNumberCoordinate < 11 || circledNumberCoordinate > 99 || circledNumberCoordinate % 10 == 0) {
            new Chip(chipCoordinates);
            player.rounds--;
            b.playerOfBoard(player);
            return;
        }
        int[] temp = b.translateCoordinates(circledNumberCoordinate);
        String circledNumber = b.board[temp[0]][temp[1]];
        int[] coordinates = b.actionToCoordinates(action, player.coordinates, (int) circledNumber.charAt(0) - 9311);
        for (int coordinate : coordinates) {
            int check = b.translateNumber(player, coordinate);
            System.out.println("Check: " + check);
            if (check != 0 && (check < 1 || check > 5)) {
                decideWhatToDo(player);
                return;
            }
        }
        new Chip(chipCoordinates);
        player.rounds--;
        player.coordinates = coordinates[coordinates.length - 1];
        if(player.coordinates < 11 || player.coordinates > 99 || player.coordinates % 10 == 0) b.playerOfBoard(player);
    }

    public int reactionActionsHelper(int i, int j) {
        int booleanChecker = 0;
        for (int m = -1; m <= 1; m++) {
            for (int n = -1; n <= 1; n++) {
                int x = i + m < 0 ? 0 : Math.min(i + m, 8);
                int y = j + n < 0 ? 0 : Math.min(j + n, 8);
                if(x == i && y == j) continue;
                if (!b.board[x][y].matches("[①②③④⑤]")) {
                    for (Player p : Player.players) {
                         if(b.board[i][j].equals(p.getRoundsString())) {
                             Helper.addIfNotInList(p.actions, Helper.directionToAction(m, n));
                             booleanChecker = 1;
                         }
                    }
                    for (Chip c : Chip.chips) {
                        if(b.board[i][j].equals(c.getSymbol())) {
                            Helper.addIfNotInList(c.actions, Helper.directionToAction(m, n));
                            booleanChecker = 1;
                        }
                    }
                }
            }
        }
        return booleanChecker;
    }

    public boolean reactionActions() {
        Helper.clear();
        b.drawBoard();
        int booleanChecker = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!b.board[i][j].matches("[①②③④⑤]")) {
                    booleanChecker += reactionActionsHelper(i, j);

                }
            }
        }

        return booleanChecker > 0;
    }

    public void reaction(Player player) {
        if(seeIfSomeoneWon()) System.exit(0);
        if (!reactionActions()) return;
        Helper.clear();
        b.drawBoard();
        String actionString = Helper.action(player.name + ": There are pieces touching, enter an action!");
        int actionNumber = actionString.compareTo("0");
        //Color order: r66 c51 b50 y73

        switch (actionNumber) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 -> {
                if (actionNumber >= Chip.chips.size() || Chip.chips.get(actionNumber).actions.size() == 0) {
                    clearActions(player);
                    return;
                }
                String action = Helper.removeFirstChar(actionString);
                action = Chip.chips.get(actionNumber).actions.size() == 1 ?
                        Chip.chips.get(actionNumber).actions.get(0) :
                        Chip.chips.get(actionNumber).actions.contains(action) ||
                                Chip.chips.get(actionNumber).actions.contains(Helper.revAction(action)) ? action : "";
                int newCoordinates = b.actionToCoordinates(action, Chip.chips.get(actionNumber).coordinates)[0];
                if (newCoordinates == 200) {
                    clearActions(player);
                    return;
                }
                int[] oldXY = b.translateCoordinates(Chip.chips.get(actionNumber).coordinates);
                b.board[oldXY[0]][oldXY[1]] = b.boardMemory[oldXY[0]][oldXY[1]];
                if (newCoordinates < 11 || newCoordinates > 99 || newCoordinates % 10 == 0) {
                    if(actionNumber == 0) {
                        Chip.chips.get(actionNumber).coordinates = 100;
                        b.bringGoldChipBack(player);
                        clearActions(player);
                        return;
                    }
                    Chip.chips.remove(actionNumber);
                    player.rounds++;
                    clearActions(player);
                    return;
                }
                int[] newXY = b.translateCoordinates(newCoordinates);
                b.board[newXY[0]][newXY[1]] = b.board[newXY[0]][newXY[1]].matches("[①②③④⑤]") ?
                        Chip.chips.get(actionNumber).getSymbol() : b.board[newXY[0]][newXY[1]];

                Chip.chips.get(actionNumber).coordinates =
                        b.board[newXY[0]][newXY[1]].equals(Chip.chips.get(actionNumber).getSymbol()) ?
                                newCoordinates : Chip.chips.get(actionNumber).coordinates;
            }
            case 66, 51, 50, 73 -> {
                actionNumber = (actionNumber == 66 ? 0 : (actionNumber == 51 ? 1 : (actionNumber == 50 ? 2 : 3)));
                if (Player.players[actionNumber].actions.size() == 0) {
                    clearActions(player);
                    return;
                }
                String action = Helper.removeFirstChar(actionString);
                action = Player.players[actionNumber].actions.size() == 1 ?
                        Player.players[actionNumber].actions.get(0) :
                        Player.players[actionNumber].actions.contains(action) ||
                                Player.players[actionNumber].actions.contains(Helper.revAction(action)) ? action : "";
                int newCoordinates = b.actionToCoordinates(action, Player.players[actionNumber].coordinates)[0];
                if (newCoordinates == 200) {
                    clearActions(player);
                    return;
                }
                int[] oldXY = b.translateCoordinates(Player.players[actionNumber].coordinates);
                if (newCoordinates < 11 || newCoordinates > 99 || newCoordinates % 10 == 0) {
                    Player.players[actionNumber].onBoard = false;
                    Player.players[actionNumber].rounds--;
                    int[] xy = b.translateCoordinates(Player.players[actionNumber].coordinates);
                    b.board[xy[0]][xy[1]] = b.boardMemory[xy[0]][xy[1]];
                    Player.players[actionNumber].coordinates = 100;
                    player.rounds++;
                    clearActions(player);
                    return;
                }
                int[] newXY = b.translateCoordinates(newCoordinates);
                b.board[newXY[0]][newXY[1]] = Player.players[actionNumber].getRoundsString();
                b.board[oldXY[0]][oldXY[1]] = b.boardMemory[oldXY[0]][oldXY[1]];
                Player.players[actionNumber].coordinates = newCoordinates;
            }
            default -> {
            }
        }
        clearActions(player);
    }

    public void clearActions(Player player) {
        for (Player p : Player.players) p.actions.clear();
        for (Chip c : Chip.chips) c.actions.clear();
        reaction(player);
    }

    public boolean seeIfSomeoneWon(){
        int c = Chip.chips.get(0).coordinates;
        Integer[] temp = {15, 59, 95, 51};
        ArrayList<Integer> playerCoords = new ArrayList<>(Arrays.asList(temp));
        if(playerCoords.contains(c)){
            Helper.clear();
            System.out.println(Player.players[playerCoords.indexOf(c)].name + " won!");
            return true;
        }
        for(int i = 0; i < Player.players.length; i++){
            if(!playerCoords.get(i).equals(Player.players[i].coordinates) &&
                    playerCoords.contains(Player.players[i].coordinates)){
                Helper.clear();
                System.out.println(Player.players[i].name + " won!");
                return true;
            }
        }
        return false;
    }
}
