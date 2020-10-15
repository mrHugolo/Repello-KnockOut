package com.company;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Player player1 = new Player("Hugo");
        Player player2 = new Player("Hanna");
        Player player3 = new Player("Elias");
        Player player4 = new Player("Per");
        for(Player player : Player.players)
        System.out.println(player.name);
        new Board();
        scan.next();
    }
}
