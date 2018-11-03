package com.wawrze.asd.exercise2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Fibonacci implements Algorithm {

    private PrintWriter writer;
    private Scanner reader;

    public void menu() {
        Scanner sc = new Scanner(System.in);
        String o;
        do {
            System.out.println("Choose option:");
            System.out.println("(f) Run algorithm from file \"In0201.txt\"");
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
                int n = rand.nextInt(9999) + 1;
                File file = new File("In0201.txt.txt");
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
                writer.println(n + "\t// n");
                System.out.println("Generated number " + n + " saved to file \"In0201.txt\"");
                writer.close();
                break;
            case "r":
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

    private void runAlgorithm(int n) {
        int[] result = new int[n + 1];

        //PLACE FOR CALLING THE ALGORITHM

        writer.println("Result of the algorithm:\n n=" + n);
        IntStream.iterate(0, i -> ++i)
                .limit(result.length - 1)
                .forEach(i -> System.out.print(result[i] + ", "));
        System.out.print(result[result.length - 1] + "\n");
        fileWriter(n, result);
        System.out.println("Result saved to file \"Out02.01.txt\"");
    }

    //PLACE FOR RECURSIVE VERSION OF FIBONACCI ALGORITHM

    private void fileWriter(int n, int[] fibonacci) {
        File file = new File("Out02.01.txt");
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
        writer.print("n=" + n);
        IntStream.iterate(0, i -> ++i)
                .limit(fibonacci.length - 1)
                .forEach(i -> writer.print(fibonacci[i] + ", "));
        writer.print(fibonacci[fibonacci.length - 1]);
        writer.close();
    }

    private int fileReader() {
        File file = null;
        try {
            file = new File("In0201.txt");
            reader = new Scanner(file);
        }
        catch(IOException e) {
            System.out.println("File " + file.getName() + " couldn't be opened!");
            return 0;
        }
        reader.close();
        return reader.nextInt();
    }

}