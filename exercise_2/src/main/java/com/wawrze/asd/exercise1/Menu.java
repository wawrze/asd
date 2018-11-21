package com.wawrze.asd.exercise1;

import java.util.Scanner;

public class Menu {

    public void start() {
        Scanner sc = new Scanner(System.in);
        String o;
        do {
            System.out.println("Choose algorithm:");
            System.out.println("(f) Fibonacci sequence");
            System.out.println("(h) Towers of Hanoi");
            System.out.println("(e) Euclidean algorithm");
            System.out.println("(x) Exit");
            o = sc.nextLine();
            option(o);
        } while (!o.equals("x"));
    }

    private void option(String o) {
        Algorithm algorithm;
        switch (o) {
            case "f":
                algorithm = new Fibonacci();
                algorithm.menu();
                break;
            case "h":
                algorithm = new Hanoi();
                algorithm.menu();
                break;
            case "e":
                algorithm = new Euclid();
                algorithm.menu();
                break;
            default:
                break;
        }
    }

}