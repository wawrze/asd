package com.wawrze.ads.exercise3;

import java.util.Scanner;

public class Menu {

    public void start() {
        Scanner sc = new Scanner(System.in);
        String o;
        do {
            System.out.println("Choose algorithm:");
            System.out.println("(l) Longest Common Subsequence");
            System.out.println("(k) Kruskal");
            System.out.println("(d) Dijkstra");
            System.out.println("(x) Exit");
            o = sc.nextLine();
            option(o);
        } while (!o.equals("x"));
    }

    private void option(String o) {
        Algorithm algorithm;
        switch (o) {
            case "l":
                algorithm = new LongestCommonSubsequence();
                algorithm.menu();
                break;
            case "k":
                algorithm = new Kruskal();
                algorithm.menu();
                break;
            case "d":
                algorithm = new Dijkstra();
                algorithm.menu();
                break;
            default:
                break;
        }
    }

}