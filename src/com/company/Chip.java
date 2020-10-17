package com.company;

import java.util.*;

public class Chip {

    protected static ArrayList<Chip> chips = new ArrayList<>();
    protected char symbol = 9679; //9679 = blank 10122 = 1
    protected int symbolValue = 0;
    protected int coordinates;

    public Chip(int coordinates){
        this.coordinates = coordinates;
        chips.add(this);
    }
}
