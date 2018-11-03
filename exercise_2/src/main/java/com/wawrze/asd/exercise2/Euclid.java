package com.wawrze.asd.exercise2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Euclid implements Algorithm {

    private PrintWriter writer;
    private Scanner reader;

    public void menu() {
        Scanner sc = new Scanner(System.in);
        String o;
        do {
            System.out.println("Choose option:");
            System.out.println("(f) Run algorithm from file \"In0203.txt\"");
            System.out.println("(g) Generate random input file");
            System.out.println("(m) Run algorithm with manual input");
            System.out.println("(x) Exit to main menu");
            o = sc.nextLine();
            option(o);
        } while (!o.equals("x"));
    }

    private void option(String o) {
        Scanner sc = new Scanner(System.in);
        Random rand = new Random();
        switch (o) {
            case "g":
                int a = rand.nextInt();
                int b = rand.nextInt();
                File file = new File("In0203.txt");
                if (!file.exists()) {
                    try {
                        file.createNewFile();
                    } catch (IOException e) {
                    }
                }
                try {
                    writer = new PrintWriter(file.getName());
                } catch (IOException e) {
                }
                writer.print(a + " " + b);
                System.out.println("Generated numbers " + a + ", " + b + " saved to file \"In0203.txt\"");
                writer.close();
                break;
            case "r":
                a = fileReader()[0];
                b = fileReader()[1];
                runAlgorithm(a, b);
                break;
            case "m":
                System.out.print("Enter first number: ");
                a = sc.nextInt();
                System.out.print("Enter second number: ");
                b = sc.nextInt();
                runAlgorithm(a, b);
                break;
            default:
                break;
        }
    }

    private void runAlgorithm(int a, int b) {
        int result = 0;

        //PLACE FOR CALLING THE ALGORITHM

        System.out.println("Result of the algorithm:");
        System.out.println("NWD(" + a + "," + b + ")=" + result);
        fileWriter(a, b, result);
        System.out.println("Result saved to file \"Out0203.txt\"");
    }

    //PLACE FOR RECURSIVE VERSION OF EUCLIDS ALGORITHM

    private void fileWriter(int a, int b, int result) {
        File file = new File("Out0203.txt");
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
            }
        }
        try {
            writer = new PrintWriter(file.getName());
        } catch (IOException e) {
        }
        writer.print("NWD(" + a + "," + b + ")=" + result);
        writer.close();
    }

    private int[] fileReader() {
        File file = null;
        try {
            file = new File("In0203.txt");
            reader = new Scanner(file);
        } catch (IOException e) {
            System.out.println("File " + file.getName() + " couldn't be opened!");
            return new int[0];
        }
        reader.close();
        int[] input = new int[2];
        input[0] = reader.nextInt();
        input[1] = reader.nextInt();
        return input;
    }

}