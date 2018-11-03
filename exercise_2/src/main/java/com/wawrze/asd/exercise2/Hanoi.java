package com.wawrze.asd.exercise2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Hanoi implements Algorithm {

    private PrintWriter writer;
    private Scanner reader;

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        String o;
        do {
            System.out.println("Choose option:");
            System.out.println("(f) Run algorithm from file \"In0202.txt\"");
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
                int n = rand.nextInt(10) + 1;
                File file = new File("In0202.txt");
                if(!file.exists()) {
                    try {
                        file.createNewFile();
                    }
                    catch(IOException e) {}
                }
                try {
                    writer = new PrintWriter(file.getName());
                }
                catch(IOException e) {}
                writer.print(n + "\t// N");
                System.out.println("Generated number " + n + " saved to file \"In0202.txt\"");
                writer.close();
                break;
            case "f":
                n = fileReader();
                runAlgorithm(n);
                break;
            case "m":
                System.out.print("Enter number: ");
                n = sc.nextInt();
                runAlgorithm(n);
                break;
            default:
                break;
        }
    }

    public void runAlgorithm(int n) {
        if(n <= 0 || n > 10) {
            System.out.println("Incorrect input: " + n + "!");
        }
        else {
            String result = hanoi(n, 1, 2, 3);
            System.out.println("Result of the algorithm:\n n=" + n);
            System.out.println(result);
            fileWriter(n, result);
            System.out.println("Result saved to file \"Out0202.txt\"");
        }
    }

    private String hanoi(int n, int first, int second, int third) {
        if(n == 1) {
            return first + "->" + second + ", ";
        }
        else {
            String result = hanoi(n - 1, first, third, second);
            result += first + "->" + second + ", ";
            result += hanoi(n - 1, third, second, first);
            return result;
        }
    }

    private void fileWriter(int n, String hanoi) {
        File file = new File("Out0202.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            }
            catch(IOException e) {}
        }
        try {
            writer = new PrintWriter(file.getName());
        }
        catch(IOException e) {}
        writer.println("N=" + n);
        writer.print(hanoi);
        writer.print("\t// A1->B1, A2->B2, ... Ak->Bk,");
        writer.close();
    }

    private int fileReader() {
        File file = null;
        try {
            file = new File("In0202.txt");
            reader = new Scanner(file);
        }
        catch(IOException e) {
            System.out.println("File " + file.getName() + " couldn't be opened!");
            return 0;
        }
        int n = reader.nextInt();
        reader.close();
        return n;
    }

}