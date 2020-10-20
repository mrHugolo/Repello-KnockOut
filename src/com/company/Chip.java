package com.company;

import java.util.*;

public class Chip {

    protected static ArrayList<Chip> chips = new ArrayList<>();
    protected String symbol; // = "●"; //9679 = blank // 10122 = 1
    protected int coordinates;
    protected ArrayList<String> actions;


    public Chip(int coordinates){
        this.coordinates = coordinates;
        chips.add(this);
        symbol = (char) (10121 + chips.indexOf(this)) +"";
        actions = new ArrayList<>();
        if(chips.size() == 1){
            this.coordinates = 55;
            symbol = "\u001B[32m●\u001B[0m";
        }
    }
}
