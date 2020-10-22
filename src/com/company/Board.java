package com.company;

import java.util.*;

public class Board {

    protected Random rand = new Random();
    protected String[][] board;
    protected String[][] boardMemory;


    public Board() {
        new Chip(0);
        board = new String[9][9];
        boardMemory = new String[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                int randNumber = rand.nextInt(5) + 9312;
                board[i][j] = String.valueOf((char) (randNumber));
                boardMemory[i][j] = String.valueOf((char) (randNumber));
            }
        }
    }

    public void drawBoard(){
        placePlayersAndChips();

        String r = "\u001B[31m | \u001B[0m";
        String p = "\u001B[35m | \u001B[0m";
        String y = "\u001B[33m | \u001B[0m";
        String c = "\u001B[36m | \u001B[0m";
        String b = "\u001B[34m | \u001B[0m";

        System.out.println(" " + "-".repeat(18) + "\u001B[31m-\u001B[0m".repeat(5) + "-".repeat(17));
        for(int j = 0; j < 4; j++) System.out.print(" | " + board[0][j]);
        System.out.print(r + board[0][4] + r);
        for(int j = 5; j < 9; j++) System.out.print(board[0][j] + " | " );
        System.out.println("\n " + "-".repeat(18) + "\u001B[31m-\u001B[0m".repeat(5) + "-".repeat(17));
        for(int j = 0; j < 9; j++) System.out.print(" | " + board[1][j]);
        System.out.print(" | ");
        System.out.println("\n " + "-".repeat(40));
        for(int j = 0; j < 9; j++) System.out.print(" | " + board[2][j]);
        System.out.print(" | ");
        String wpw = "\n " + "-".repeat(13) + "\u001B[35m-\u001B[0m".repeat(14) + "-".repeat(13);
        System.out.println(wpw);
        for(int j = 0; j < 3; j++) System.out.print(" | " + board[3][j]);
        System.out.print(p + board[3][3] + p + board[3][4] + p + board[3][5] + p);
        for(int j = 6; j < 9; j++) System.out.print(board[3][j] + " | ");
        String ypg = "\n " + "\u001B[33m-\u001B[0m".repeat(5) + "-".repeat(8) +
                "\u001B[35m-\u001B[0m".repeat(14) + "-".repeat(9) + "\u001B[36m-\u001B[0m".repeat(4);
        System.out.println(ypg);
        System.out.print(y + board[4][0] + y + board[4][1] + " | " + board[4][2] + p + board[4][3] + p + board[4][4] +
                p + board[4][5] + p + board[4][6] + " | " + board[4][7] + c + board[4][8] + c);
        System.out.println(ypg);
        for(int j = 0; j < 3; j++) System.out.print(" | " + board[5][j]);
        System.out.print(p + board[5][3] + p + board[5][4] + p + board[5][5] + p);
        for(int j = 6; j < 9; j++) System.out.print(board[5][j] + " | ");
        System.out.println(wpw);
        for(int j = 0; j < 9; j++) System.out.print(" | " + board[6][j]);
        System.out.print(" | ");
        System.out.println("\n " + "-".repeat(40));
        for(int j = 0; j < 9; j++) System.out.print(" | " + board[7][j]);
        System.out.print(" | ");
        System.out.println("\n " + "-".repeat(18) + "\u001B[34m-\u001B[0m".repeat(5) + "-".repeat(17));
        for(int j = 0; j < 4; j++) System.out.print(" | " + board[8][j]);
        System.out.print(b + board[8][4] + b);
        for(int j = 5; j < 9; j++) System.out.print(board[8][j] + " | " );
        System.out.println("\n " + "-".repeat(18) + "\u001B[34m-\u001B[0m".repeat(5) + "-".repeat(17) + "");
    }

    public void placePlayersAndChips(){
        for(Player player : Player.players){
            if(player.onBoard) {
                board[translateCoordinates(player.coordinates)[0]]
                        [translateCoordinates(player.coordinates)[1]] = player.getRoundsString();
            }
        }
        for(Chip chip : Chip.chips){
            board[translateCoordinates(chip.coordinates)[0]]
                    [translateCoordinates(chip.coordinates)[1]] = chip.getSymbol();
        }
    }

    public int translateNumber(Player player, int coordinates){
        int[] xy = translateCoordinates(coordinates);
        try {
            String number = board[xy[0]][xy[1]];
            char actualNumber = number.charAt(0);
            return (int) actualNumber - 9311;
        }
        catch (Exception e){
            System.out.println("Outside of board");
            player.onBoard = false;

        }
        return 0;
    }

    public int[] translateCoordinates(int coordinates){
        int[] xy = new int[2];
        xy[0] = (coordinates / 10) - 1;
        xy[1] = (coordinates % 10) - 1;
        return xy;
    }

    public int[] actionToCoordinates(String action, int currentCoordinates, int ... number){
        int multiplier = number.length == 0 ? 1 : number[0];
        int numberCoordinate = 200;
        int[] numberCoordinates = new int[multiplier];
        for(int i = 1; i <= multiplier; i++) {
            switch (action) {
                case "w" -> numberCoordinate = currentCoordinates - 10 * i;
                case "wd", "dw" -> numberCoordinate = currentCoordinates - 9 * i;
                case "d" -> numberCoordinate = currentCoordinates + i;
                case "sd", "ds" -> numberCoordinate = currentCoordinates + 11 * i;
                case "s" -> numberCoordinate = currentCoordinates + 10 * i;
                case "sa", "as" -> numberCoordinate = currentCoordinates + 9 * i;
                case "a" -> numberCoordinate = currentCoordinates - i;
                case "aw", "wa" -> numberCoordinate = currentCoordinates - 11 * i;
                default -> numberCoordinate = 200;
            }
            numberCoordinates[i - 1] = numberCoordinate;
        }
        return numberCoordinates;

    }

    public void playerOfBoard(Player player){
        player.onBoard = false;
        player.rounds--;
        int[] xy = translateCoordinates(player.coordinates);
        board[xy[0]][xy[1]] = boardMemory [xy[0]][xy[1]];
        player.coordinates = 100;
    }

    public void bringPlayerBack(Player player) {
        while (player.coordinates == 100) {
            try {
                int middleNumber = Integer.parseInt(Helper.action(player.name + ": Which purple box would you like to return on? (1-9)"));
                switch (middleNumber) {
                    case 1 -> player.coordinates = board[3][3].matches("[①②③④⑤]") ? 44 : 100;
                    case 2 -> player.coordinates = board[3][4].matches("[①②③④⑤]") ? 45 : 100;
                    case 3 -> player.coordinates = board[3][5].matches("[①②③④⑤]") ? 46 : 100;
                    case 4 -> player.coordinates = board[4][3].matches("[①②③④⑤]") ? 54 : 100;
                    case 5 -> player.coordinates = board[4][4].matches("[①②③④⑤]") ? 55 : 100;
                    case 6 -> player.coordinates = board[4][5].matches("[①②③④⑤]") ? 56 : 100;
                    case 7 -> player.coordinates = board[5][3].matches("[①②③④⑤]") ? 64 : 100;
                    case 8 -> player.coordinates = board[5][4].matches("[①②③④⑤]") ? 65 : 100;
                    case 9 -> player.coordinates = board[5][5].matches("[①②③④⑤]") ? 66 : 100;
                }
            } catch (Exception ignore) {
            }
        }
        player.onBoard = true;
        drawBoard();
    }
}
