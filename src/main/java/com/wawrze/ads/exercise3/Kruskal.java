package com.wawrze.ads.exercise3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Kruskal implements Algorithm {

    private PrintWriter writer;
    private Scanner reader;

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        String o;
        do {
            System.out.println("Choose option:");
            System.out.println("(f) Run algorithm from file \"In0303.txt\"");
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
                File file = new File("In0303.txt");
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
                System.out.println("Generated number " + n + " saved to file \"In0303.txt\"");
                writer.close();
                break;
            case "f":
                n = fileReader();
                runAlgorithm();
                break;
            case "m":
                System.out.print("Enter number: ");
                n = sc.nextInt();
                runAlgorithm();
                break;
            default:
                break;
        }
    }

    public void runAlgorithm() {
        kruskal();
    }

    private void kruskal() {

    }


    private void fileWriter() {
        File file = new File("Out0303.txt");
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
        writer.println("");
        writer.print("");
        writer.close();
    }

    private int fileReader() {
        File file = null;
        try {
            file = new File("In0303.txt");
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