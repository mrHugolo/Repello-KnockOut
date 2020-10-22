package com.company;

import java.util.*;

public class Game {
    protected Board b = new Board();

    public Game() {

        decideNames();
        while (true) {
            for (Player player : Player.players) {
                b.drawBoard();
                decideWhatToDo(player);
                reaction(player);
            }
        }

    }

    public void decideNames() {
        Player player1 = new Player(Helper.enterName("Red Player: Please enter your name."));
        Player player2 = new Player(Helper.enterName("Cyan Player: Please enter your name."));
        Player player3 = new Player(Helper.enterName("Blue Player: Please enter your name."));
        Player player4 = new Player(Helper.enterName("Yellow Player: Please enter your name."));
    }

    public void decideWhatToDo(Player player) {
        if (!player.onBoard) {
            b.bringPlayerBack(player);
            reaction(player);
        }
        String action = Helper.action(player.name + ": Enter an action!");
        int circledNumberCoordinate = b.actionToCoordinates(action, player.coordinates)[0];
        if(circledNumberCoordinate == 200) {
            decideWhatToDo(player);
            return;
        }
        new Chip(player.coordinates);
        player.rounds--;
        if (circledNumberCoordinate < 11 || circledNumberCoordinate > 99 || circledNumberCoordinate % 10 == 0) {
            b.playerOfBoard(player);
            return;
        }
        int[] temp = b.translateCoordinates(circledNumberCoordinate);
        String circledNumber = b.board[temp[0]][temp[1]];
        int[] coordinates = b.actionToCoordinates(action, player.coordinates, (int) circledNumber.charAt(0) - 9311);
        for (int coordinate : coordinates) {
            int check = b.translateNumber(player, coordinate);
            if (check < 1 || check > 5) {
                decideWhatToDo(player);
                return;
            }
        }
        player.coordinates = coordinates[coordinates.length - 1];
        b.drawBoard();
    }

    public boolean reactionActions() {
        int booleanChecker = 0;
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                if (!b.board[i][j].matches("[①②③④⑤]")) {
                    try {
                        if (!b.board[i - 1][j - 1].matches("[①②③④⑤]")) {
                            for (Player p : Player.players)
                                if (b.board[i][j].equals(p.getRoundsString()))
                                    if (!p.actions.contains("sd")) p.actions.add("sd");
                            for (Chip c : Chip.chips)
                                if (b.board[i][j].equals(c.getSymbol())) if (!c.actions.contains("sd")) c.actions.add("sd");
                            for (Player p : Player.players)
                                if (b.board[i - 1][j - 1].equals(p.getRoundsString()))
                                    if (!p.actions.contains("aw")) p.actions.add("aw");
                            for (Chip c : Chip.chips)
                                if (b.board[i][j + 1].equals(c.getSymbol()))
                                    if (!c.actions.contains("aw")) c.actions.add("aw");
                            booleanChecker = 1;
                        }
                        if (!b.board[i - 1][j].matches("[①②③④⑤]")) {
                            for (Player p : Player.players)
                                if (b.board[i][j].equals(p.getRoundsString()))
                                    if (!p.actions.contains("s")) p.actions.add("s");
                            for (Chip c : Chip.chips)
                                if (b.board[i][j].equals(c.getSymbol())) if (!c.actions.contains("s")) c.actions.add("s");
                            for (Player p : Player.players)
                                if (b.board[i - 1][j].equals(p.getRoundsString()))
                                    if (!p.actions.contains("w")) p.actions.add("w");
                            for (Chip c : Chip.chips)
                                if (b.board[i - 1][j].equals(c.getSymbol()))
                                    if (!c.actions.contains("w")) c.actions.add("w");
                            booleanChecker = 1;
                        }
                        if (!b.board[i - 1][j + 1].matches("[①②③④⑤]")) {
                            for (Player p : Player.players)
                                if (b.board[i][j].equals(p.getRoundsString()))
                                    if (!p.actions.contains("as")) p.actions.add("as");
                            for (Chip c : Chip.chips)
                                if (b.board[i][j].equals(c.getSymbol())) if (!c.actions.contains("as")) c.actions.add("as");
                            for (Player p : Player.players)
                                if (b.board[i - 1][j + 1].equals(p.getRoundsString()))
                                    if (!p.actions.contains("dw")) p.actions.add("dw");
                            for (Chip c : Chip.chips)
                                if (b.board[i - 1][j + 1].equals(c.getSymbol()))
                                    if (!c.actions.contains("dw")) c.actions.add("dw");
                            booleanChecker = 1;
                        }
                        if (!b.board[i][j - 1].matches("[①②③④⑤]")) {
                            for (Player p : Player.players)
                                if (b.board[i][j].equals(p.getRoundsString()))
                                    if (!p.actions.contains("d")) p.actions.add("d");
                            for (Chip c : Chip.chips)
                                if (b.board[i][j].equals(c.getSymbol())) if (!c.actions.contains("d")) c.actions.add("d");
                            for (Player p : Player.players)
                                if (b.board[i][j - 1].equals(p.getRoundsString()))
                                    if (!p.actions.contains("a")) p.actions.add("a");
                            for (Chip c : Chip.chips)
                                if (b.board[i][j - 1].equals(c.getSymbol()))
                                    if (!c.actions.contains("a")) c.actions.add("a");
                            booleanChecker = 1;
                        }
                        if (!b.board[i][j + 1].matches("[①②③④⑤]")) {
                            for (Player p : Player.players)
                                if (b.board[i][j].equals(p.getRoundsString()))
                                    if (!p.actions.contains("a")) p.actions.add("a");
                            for (Chip c : Chip.chips)
                                if (b.board[i][j].equals(c.getSymbol())) if (!c.actions.contains("a")) c.actions.add("a");
                            for (Player p : Player.players)
                                if (b.board[i][j + 1].equals(p.getRoundsString()))
                                    if (!p.actions.contains("d")) p.actions.add("d");
                            for (Chip c : Chip.chips)
                                if (b.board[i][j + 1].equals(c.getSymbol()))
                                    if (!c.actions.contains("d")) c.actions.add("d");
                            booleanChecker = 1;
                        }
                        if (!b.board[i + 1][j - 1].matches("[①②③④⑤]")) {
                            for (Player p : Player.players)
                                if (b.board[i][j].equals(p.getRoundsString()))
                                    if (!p.actions.contains("dw")) p.actions.add("dw");
                            for (Chip c : Chip.chips)
                                if (b.board[i][j].equals(c.getSymbol())) if (!c.actions.contains("dw")) c.actions.add("dw");
                            for (Player p : Player.players)
                                if (b.board[i + 1][j - 1].equals(p.getRoundsString()))
                                    if (!p.actions.contains("as")) p.actions.add("as");
                            for (Chip c : Chip.chips)
                                if (b.board[i + 1][j - 1].equals(c.getSymbol()))
                                    if (!c.actions.contains("as")) c.actions.add("as");
                            booleanChecker = 1;
                        }
                        if (!b.board[i + 1][j].matches("[①②③④⑤]")) {
                            for (Player p : Player.players)
                                if (b.board[i][j].equals(p.getRoundsString()))
                                    if (!p.actions.contains("w")) p.actions.add("w");
                            for (Chip c : Chip.chips)
                                if (b.board[i][j].equals(c.getSymbol())) if (!c.actions.contains("w")) c.actions.add("w");
                            for (Player p : Player.players)
                                if (b.board[i + 1][j].equals(p.getRoundsString()))
                                    if (!p.actions.contains("s")) p.actions.add("s");
                            for (Chip c : Chip.chips)
                                if (b.board[i + 1][j].equals(c.getSymbol()))
                                    if (!c.actions.contains("s")) c.actions.add("s");
                            booleanChecker = 1;
                        }
                        if (!b.board[i + 1][j + 1].matches("[①②③④⑤]")) {
                            for (Player p : Player.players)
                                if (b.board[i][j].equals(p.getRoundsString()))
                                    if (!p.actions.contains("aw")) p.actions.add("aw");
                            for (Chip c : Chip.chips)
                                if (b.board[i][j].equals(c.getSymbol())) if (!c.actions.contains("aw")) c.actions.add("aw");
                            for (Player p : Player.players)
                                if (b.board[i + 1][j + 1].equals(p.getRoundsString()))
                                    if (!p.actions.contains("sd")) p.actions.add("sd");
                            for (Chip c : Chip.chips)
                                if (b.board[i + 1][j + 1].equals(c.getSymbol()))
                                    if (!c.actions.contains("sd")) c.actions.add("sd");
                            booleanChecker = 1;
                        }
                    } catch (Exception ignore) {
                    }
                }
            }
        }
        return booleanChecker == 1;
    }

    public void reaction(Player player) {
        System.out.println(reactionActions());
        if (!reactionActions()) return;
        b.drawBoard();
        String actionString = Helper.action(player.name + ": There are pieces touching, enter an action!");
        int actionNumber = actionString.compareTo("0");
        //Color order: r66 c51 b50 y73
        System.out.println(actionNumber);
        switch (actionNumber) {
            case 0, 1, 2, 3, 4, 5, 6, 7, 8, 9 -> {
                if (actionNumber >= Chip.chips.size() || Chip.chips.get(actionNumber).actions.size() == 0) {
                    clearActions(player);
                    return;
                }
                String action = Helper.removeFirstChar(actionString);
                action = Chip.chips.get(actionNumber).actions.size() == 1 ?
                        Chip.chips.get(actionNumber).actions.get(0) : action;
                System.out.println(action);
                System.out.println(Chip.chips.get(actionNumber).actions.size() + " : är det 1?");
                int newCoordinates = b.actionToCoordinates(action, Chip.chips.get(actionNumber).coordinates)[0];
                int[] oldXY = b.translateCoordinates(Chip.chips.get(actionNumber).coordinates);
                b.board[oldXY[0]][oldXY[1]] = b.boardMemory[oldXY[0]][oldXY[1]];
                System.out.println("newCoordinates: " + newCoordinates);
                if (newCoordinates != 200 && (newCoordinates < 11 || newCoordinates > 99 || newCoordinates % 10 == 0)) {
                    Chip.chips.remove(actionNumber);
                    player.rounds++;
                    clearActions(player);
                    return;
                }
                int[] newXY = b.translateCoordinates(newCoordinates);
                b.board[newXY[0]][newXY[1]] = b.board[newXY[0]][newXY[1]].matches("[①②③④⑤]") ?
                        Chip.chips.get(actionNumber).getSymbol() : b.board[newXY[0]][newXY[1]];

                Chip.chips.get(actionNumber).coordinates =
                        b.board[newXY[0]][newXY[1]].equals(Chip.chips.get(actionNumber).getSymbol())?
                        newCoordinates : Chip.chips.get(actionNumber).coordinates;
            }
            case 66, 51, 50, 73 -> {
                actionNumber = (actionNumber == 66 ? 0 : (actionNumber == 51 ? 1 : (actionNumber == 50 ? 2 : 3)));
                System.out.println(Player.players[actionNumber].actions.size());
                for (String action : Player.players[actionNumber].actions) System.out.println(action);
                if (Player.players[actionNumber].actions.size() == 0) {
                    clearActions(player);
                    return;
                }
                String action = Helper.removeFirstChar(actionString);
                action = Player.players[actionNumber].actions.size() == 1 ?
                        Player.players[actionNumber].actions.get(0) :
                        Player.players[actionNumber].actions.contains(action) ||
                                Player.players[actionNumber].actions.contains(Helper.revAction(action)) ? action : "";
                System.out.println(action);
                System.out.println(Player.players[actionNumber].actions.size());
                int newCoordinates = b.actionToCoordinates(action, Player.players[actionNumber].coordinates)[0];
                if (newCoordinates == 200) {
                    clearActions(player);
                    return;
                }
                int[] oldXY = b.translateCoordinates(Player.players[actionNumber].coordinates);
                int[] newXY = b.translateCoordinates(newCoordinates);
                System.out.println(newCoordinates);
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


}
