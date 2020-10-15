package com.company;

public class Player {

    protected static Player[] players = new Player[4];
    private static int tracker = 0;
    protected String name;
    protected int rounds;

    //red cyan blue yellow

    public Player(String name){

        if(tracker == 0) this.name = "\u001B[31m" + name + "\u001B[0m";
        else if(tracker == 1) this.name = "\u001B[36m" + name + "\u001B[0m";
        else if(tracker == 2) this.name = "\u001B[34m" + name + "\u001B[0m";
        else this.name = "\u001B[33m" + name + "\u001B[0m";
        rounds = 6;
        players[tracker] = this;
        tracker++;
    }
}
