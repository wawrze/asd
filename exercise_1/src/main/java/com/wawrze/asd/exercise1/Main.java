package com.wawrze.asd.exercise1;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Main {

    private PrintWriter writer;
    private Scanner reader;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String o;
        do {
            for (int i = 0; i < 100; i++)
                System.out.println();
            System.out.println("Choose option:");
            System.out.println("(f) Run algorithms from file (\"in.txt\")");
            System.out.println("(g) Generate random file");
            System.out.println("(r) Run algorithms with manual input");
            System.out.println("(x) Exit");
            o = sc.nextLine();
            (new Main()).option(o);
        } while (!o.equals("x"));
    }

    private void option(String o) {
        Scanner sc = new Scanner(System.in);
        int[] array;
        switch (o) {
            case "f":
                array = fileReader(new File("in.txt"));
                runAlgorithms(array);
                break;
            case "g":
                System.out.print("Enter size of the array: ");
                array = arrayGenerator(sc.nextInt());
                File file = new File("in.txt");
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
                writer.println(array.length);
                IntStream.iterate(0, i -> ++i)
                        .limit(array.length)
                        .forEach(i -> writer.println(array[i]));
                System.out.println("Array saved to file \"in.txt\"");
                writer.close();
                break;
            case "r":
                System.out.print("Enter size of the array: ");
                int size = sc.nextInt();
                array = new int[size];
                for(int i = 0;i < size;i++) {
                    System.out.print("Enter " + (i + 1) + " number: ");
                    int tmp = sc.nextInt();
                    array[i] = tmp;
                }
                runAlgorithms(array);
                break;
            default:
                break;
        }
    }

    private int algorithm1(int[] array) {

        return 1;
    }

    private int algorithm2(int[] array) {

        return 2;
    }

    public int[] runAlgorithms(int[] array) {
        if(array.length == 0)
            throw new RuntimeException("Incorrect input!!!");
        int[] result = new int[2];
        result[0] = algorithm1(array);
        result[1] = algorithm2(array);
        fileWriter(result);
        System.out.println("Result of running algorithm 1: " + result[0]);
        System.out.println("Result of running algorithm 2: " + result[1]);
        System.out.println("\nResults saved to file \"out.txt\"");
        return result;
    }

    private void fileWriter(int[] array) {
        File file = new File("out.txt");
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
        writer.println(array[0]);
        writer.println(array[1]);
        writer.close();
    }

    public int[] fileReader(File file) {
        try {
            reader = new Scanner(file);
        }
        catch(IOException e) {
            System.out.println("File " + file.getName() + " couldn't be opened!");
            return new int[0];
        }
        int tmp = Integer.valueOf(reader.nextLine());
        int[] array = new int[tmp];
        IntStream.iterate(0, i -> ++i)
                .limit(tmp)
                .forEach(i -> {
                    try {
                        array[i] = Integer.valueOf(reader.nextLine());
                    }
                    catch(NoSuchElementException e) {
                        System.out.println("Incorrect input file!");
                    }
                });
        reader.close();
        return array;
    }

    private int[] arrayGenerator(int size) {
        Random rand = new Random();
        int[] array = new int[size];
        IntStream.iterate(0, i -> ++i)
                .limit(size)
                .forEach(i -> array[i] = rand.nextInt(10));
        System.out.println("Generated numbers:");
        for(Integer i : array)
            System.out.println(i);
        return array;
    }

}