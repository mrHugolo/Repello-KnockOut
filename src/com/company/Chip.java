package com.company;

import java.util.*;

public class Chip {

    protected static ArrayList<Chip> chips = new ArrayList<>();
    protected String symbol = "●"; //9679 = blank 10122 = 1
    protected int symbolValue = 0;
    protected int coordinates;


    public Chip(int coordinates){
        if(chips.size() == 0){
            coordinates = 55;
            symbol = "\u001B[32m●\u001B[0m";
        }
        this.coordinates = coordinates;
        chips.add(this);
    }
}
