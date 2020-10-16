package com.company;

public class Player {

    protected static Player[] players = new Player[4];
    private static int tracker = 0;
    protected String color;
    protected String name;
    protected int rounds;

    //red cyan blue yellow

    public Player(String name){

        if(tracker == 0) color = "\u001B[31m";
        else if(tracker == 1) color = "\u001B[36m";
        else if(tracker == 2) color = "\u001B[34m";
        else color = "\u001B[33m";
        this.name = color + name + "\u001B[0m";
        rounds = 6;
        players[tracker] = this;
        tracker++;
    }

}
