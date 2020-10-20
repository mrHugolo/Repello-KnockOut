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
        new Chip(player.coordinates);
        player.rounds--;
        String action = Helper.action(player.name + ": Enter an action!");
        int circledNumberCoordinate = b.actionToCoordinates(action, player.coordinates)[0];
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
    }

    public void reactionActions(Player player) {
        for (int i = 1; i < 8; i++) {
            for (int j = 1; j < 8; j++) {
                if (!b.board[i][j].matches("[①②③④⑤]")) {
                    if (!b.board[i - 1][j - 1].matches("[①②③④⑤]")) {
                        for (Player p : Player.players) if (b.board[i][j].equals(p.roundsString)) p.actions.add("sd");
                        for (Chip c : Chip.chips) if (b.board[i][j].equals(c.symbol)) c.actions.add("sd");
                        for (Player p : Player.players)
                            if (b.board[i - 1][j - 1].equals(p.roundsString)) p.actions.add("aw");
                        for (Chip c : Chip.chips) if (b.board[i][j + 1].equals(c.symbol)) c.actions.add("aw");
                    }
                    if (!b.board[i - 1][j].matches("[①②③④⑤]")) {
                        for (Player p : Player.players) if (b.board[i][j].equals(p.roundsString)) p.actions.add("s");
                        for (Chip c : Chip.chips) if (b.board[i][j].equals(c.symbol)) c.actions.add("s");
                        for (Player p : Player.players)
                            if (b.board[i - 1][j].equals(p.roundsString)) p.actions.add("w");
                        for (Chip c : Chip.chips) if (b.board[i - 1][j].equals(c.symbol)) c.actions.add("w");
                    }
                    if (!b.board[i - 1][j + 1].matches("[①②③④⑤]")) {
                        for (Player p : Player.players)
                            if (b.board[i][j].equals(p.roundsString)) p.actions.add("as");
                        for (Chip c : Chip.chips) if (b.board[i][j].equals(c.symbol)) c.actions.add("as");
                        for (Player p : Player.players)
                            if (b.board[i - 1][j + 1].equals(p.roundsString)) p.actions.add("dw");
                        for (Chip c : Chip.chips) if (b.board[i - 1][j + 1].equals(c.symbol)) c.actions.add("dw");
                    }
                    if (!b.board[i][j - 1].matches("[①②③④⑤]")) {
                        for (Player p : Player.players)
                            if (b.board[i][j].equals(p.roundsString)) p.actions.add("d");
                        for (Chip c : Chip.chips) if (b.board[i][j].equals(c.symbol)) c.actions.add("d");
                        for (Player p : Player.players)
                            if (b.board[i][j - 1].equals(p.roundsString)) p.actions.add("a");
                        for (Chip c : Chip.chips) if (b.board[i][j - 1].equals(c.symbol)) c.actions.add("a");
                    }
                    if (!b.board[i][j + 1].matches("[①②③④⑤]")) {
                        for (Player p : Player.players)
                            if (b.board[i][j].equals(p.roundsString)) p.actions.add("a");
                        for (Chip c : Chip.chips) if (b.board[i][j].equals(c.symbol)) c.actions.add("a");
                        for (Player p : Player.players)
                            if (b.board[i][j + 1].equals(p.roundsString)) p.actions.add("d");
                        for (Chip c : Chip.chips) if (b.board[i][j + 1].equals(c.symbol)) c.actions.add("d");
                    }
                    if (!b.board[i + 1][j - 1].matches("[①②③④⑤]")) {
                        for (Player p : Player.players)
                            if (b.board[i][j].equals(p.roundsString)) p.actions.add("dw");
                        for (Chip c : Chip.chips) if (b.board[i][j].equals(c.symbol)) c.actions.add("dw");
                        for (Player p : Player.players)
                            if (b.board[i + 1][j - 1].equals(p.roundsString)) p.actions.add("as");
                        for (Chip c : Chip.chips) if (b.board[i + 1][j - 1].equals(c.symbol)) c.actions.add("as");
                    }
                    if (!b.board[i + 1][j].matches("[①②③④⑤]")) {
                        for (Player p : Player.players)
                            if (b.board[i][j].equals(p.roundsString)) p.actions.add("w");
                        for (Chip c : Chip.chips) if (b.board[i][j].equals(c.symbol)) c.actions.add("w");
                        for (Player p : Player.players)
                            if (b.board[i + 1][j].equals(p.roundsString)) p.actions.add("s");
                        for (Chip c : Chip.chips) if (b.board[i + 1][j].equals(c.symbol)) c.actions.add("s");
                    }
                    if (!b.board[i + 1][j + 1].matches("[①②③④⑤]")) {
                        for (Player p : Player.players)
                            if (b.board[i][j].equals(p.roundsString)) p.actions.add("aw");
                        for (Chip c : Chip.chips) if (b.board[i][j].equals(c.symbol)) c.actions.add("aw");
                        for (Player p : Player.players)
                            if (b.board[i + 1][j + 1].equals(p.roundsString)) p.actions.add("sd");
                        for (Chip c : Chip.chips) if (b.board[i + 1][j + 1].equals(c.symbol)) c.actions.add("sd");
                    }
                }
            }
        }
    }

    public void reaction(Player player) {

    }


}
