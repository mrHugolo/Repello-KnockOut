package com.company;

import java.util.*;

public abstract class Helper {
    private static final Scanner scan = new Scanner(System.in);

    public static final String reset = "\u001B[0m";
    public static final String red = "\u001B[31m";
    public static final String yellow = "\u001B[33m";
    public static final String blue = "\u001B[34m";
    public static final String purple = "\u001B[35m";
    public static final String cyan = "\u001B[36m";
    private static final String[] temp ={reset, red, yellow, blue, purple, cyan};
    public static ArrayList<String> colors = new ArrayList<>(Arrays.asList(temp));
    //action and tracker needs to be saved in getNumberCoordinates()
    private static String action = "";
    private static boolean tracker = true;


    //public static int one = '①';
    //public static char two = 9313;

    //char temp = (char) (Player.players[0].rounds + 65296);
    //String six = player1.color + temp + "\u001B[0m";

    //char temp2 = (char) (Player.players[0].rounds + 8543);
    //String VI = player1.color + temp2 + "\u001B[0m";

    public static String enterName(String message){
        System.out.println(message);
        return scan.nextLine();
    }

    public static String action(String message){
        System.out.println(message);
        return scan.nextLine();
    }

    public static String directionToAction(int x, int y){
        switch (x){
            case -1 -> {
                if(y == -1) return "sd";
                else if(y == 0) return "s";
                else if(y == 1) return "sa";
            }
            case 0 -> {
                if(y == -1) return "d";
                else if(y == 1) return "a";
            }
            case 1 -> {
                if(y == -1) return "wd";
                else if(y == 0) return "w";
                else if(y == 1) return "wa";
            }
        }
        return "";
    }

    public static void addIfNotInList(ArrayList<String> list, String direction){
        System.out.println(direction);
        if(!list.contains(direction)) list.add(direction);
    }

    public static void clear(){
        System.out.println("\n".repeat(30));
    }

    public static String removeFirstChar(String word){
        String wordWithoutFirstChar = "";
        for(int i = 1; i < word.length(); i++) wordWithoutFirstChar += word.charAt(i);
        return wordWithoutFirstChar;
    }

    public static String revAction(String action) {
        return action.length() <= 1 ? action : "" + action.charAt(1) + action.charAt(0);
    }
}
