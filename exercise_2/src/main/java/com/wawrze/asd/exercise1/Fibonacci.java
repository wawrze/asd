package com.wawrze.asd.exercise1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Fibonacci implements Algorithm {

    private PrintWriter writer;
    private Scanner reader;

    @Override
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
                File file = new File("In0201.txt");
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
        if(n < 1 || n > 10000) {
            System.out.println("Incorrect input: " + n + "!");
        }
        else {
            List<Integer> result = new ArrayList<>();
            int tmp;
            for(int i = 0;;i++) {
                tmp = fibonacci(i);
                if(tmp < n) {
                    result.add(tmp);
                    continue;
                }
                else {
                    break;
                }
            }
            System.out.println("Result of the algorithm:\n n=" + n);
            IntStream.iterate(0, i -> ++i)
                    .limit(result.size() - 1)
                    .forEach(i -> System.out.print(result.get(i) + ", "));
            System.out.print(result.get(result.size() - 1) + "\n");
            fileWriter(n, result);
            System.out.println("Result saved to file \"Out02.01.txt\"");
        }
    }

    private int fibonacci(int n) {
        if(n <= 1) {
            return n;
        }
        else {
            return fibonacci(n - 1) + fibonacci(n - 2);
        }
    }

    private void fileWriter(int n, List<Integer> fibonacci) {
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
                .limit(fibonacci.size()- 1)
                .forEach(i -> writer.print(fibonacci.get(i) + ", "));
        writer.print(fibonacci.get(fibonacci.size() - 1));
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
        int n = reader.nextInt();
        reader.close();
        return n;
    }

}