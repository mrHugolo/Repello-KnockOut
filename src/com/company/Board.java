package com.company;

import java.util.*;

public class Board {

    protected  Random rand = new Random();
    protected  String[][] board;


    public Board() {
        board = new String[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                board[i][j] = String.valueOf((char) (rand.nextInt(5) + 9312));
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
            char temp = (char) (player.rounds + 65296);
            String rounds = player.color + temp + "\u001B[0m";
            board[Helper.translateCoordinates(player.coordinates)[0]]
                    [Helper.translateCoordinates(player.coordinates)[1]] = rounds;
        }
        for(Chip chip : Chip.chips){
            board[Helper.translateCoordinates(chip.coordinates)[0]]
                    [Helper.translateCoordinates(chip.coordinates)[1]] = chip.symbol + "";
        }
    }

    public int translateNumber(int coordinates){
        int[] xy = Helper.translateCoordinates(coordinates);
        String number = board[xy[0]][xy[1]];
        char actualNumber = number.charAt(0);
        return (int) actualNumber - 9311;
    }
}
