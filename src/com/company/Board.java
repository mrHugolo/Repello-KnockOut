package com.company;

import java.util.*;

public class Board {

    protected static Random rand = new Random();
    protected static char[][] board;


    public Board() {
        board = new char[9][9];
        for(int i = 0; i < 9; i++){
            for(int j = 0; j < 9; j++){
                board[i][j] = (char) (rand.nextInt(5) + 9312);
            }
        }
        drawBoard();
    }

    public static void drawBoard(){
        String p = "\u001B[35m | \u001B[0m";
        System.out.println(" " + "-".repeat(18) + "\u001B[31m-\u001B[0m".repeat(5) + "-".repeat(17));
        for(int j = 0; j < 4; j++) System.out.print(" | " + board[0][j]);
        System.out.print("\u001B[31m | \u001B[0m" + board[0][4] + "\u001B[31m | \u001B[0m");
        for(int j = 0; j < 4; j++) System.out.print(board[0][j] + " | " );
        System.out.println("\n " + "-".repeat(18) + "\u001B[31m-\u001B[0m".repeat(5) + "-".repeat(17));
        for(int j = 0; j < 9; j++) System.out.print(" | " + board[1][j]);
        System.out.print(" | ");
        System.out.println("\n " + "-".repeat(40));
        for(int j = 0; j < 9; j++) System.out.print(" | " + board[2][j]);
        System.out.print(" | ");
        System.out.println("\n " + "-".repeat(13) + "\u001B[35m-\u001B[0m".repeat(14) + "-".repeat(13));
        for(int j = 0; j < 3; j++) System.out.print(" | " + board[3][j]);
        System.out.print(p + board[3][3] + p + board[3][4] + p + board[3][5] + p);
        for(int j = 6; j < 9; j++) System.out.print(board[3][j] + " | ");


    }


}
