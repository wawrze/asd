package com.wawrze.ads.exercise3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Kruskal implements Algorithm {

    private PrintWriter writer;
    private Scanner reader;

    private Set<int[]> kruskalsAlgorithm(Set<int[]> input) {

        return new HashSet<>();
    }

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        String o;
        do {
            System.out.println("Choose option:");
            System.out.println("(f) Run algorithm from file \"In0303.txt\"");
            System.out.println("(x) Exit to main menu");
            o = sc.nextLine();
            option(o);
        } while (!o.equals("x"));
    }

    private void option(String o) {
        switch (o) {
            case "f":
                Set<int[]> input = fileReader();
                runAlgorithm(input);
                break;
            default:
                break;
        }
    }

    public void runAlgorithm(Set<int[]> input) {
        Set<int[]> result = kruskalsAlgorithm(input);
        StringBuilder resultStringified = new StringBuilder();
        result.stream().forEach(r -> {
            resultStringified.append(r[0]);
            resultStringified.append(" ");
            resultStringified.append(r[1]);
            resultStringified.append("[");
            resultStringified.append(r[2]);
            resultStringified.append("], ");
        });
        fileWriter(resultStringified.toString());
        System.out.println("Result:\n" + resultStringified + "\nsaved to file Out0303.txt");
    }

    private void fileWriter(String result) {
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
        catch(IOException e) {
            System.out.println("There was an error during write to file Out303.txt: " + e.getMessage() + "!");
            return;
        }
        writer.println("");
        writer.print("");
        writer.close();
    }

    private Set<int[]> fileReader() {
        File file = null;
        try {
            file = new File("In0303.txt");
            reader = new Scanner(file);
        }
        catch(IOException e) {
            System.out.println("File " + file.getName() + " couldn't be opened!");
            return new HashSet<>();
        }
        int n = reader.nextInt();
        int m = reader.nextInt();
        reader.nextLine();
        int pointer = 1;
        Set<int[]> edges = new HashSet<>();
        while(pointer <= n) {
            Scanner scanner = new Scanner(reader.nextLine());
            boolean condition = true;
            while(condition) {
                try {
                    int point1 = scanner.nextInt();
                    int weight = scanner.nextInt();
                    int point2 = pointer;
                    if(point1 > point2) {
                        int temp = point1;
                        point1 = point2;
                        point2 = temp;
                    }
                    if(point1 < pointer) {
                        continue;
                    }
                    int[] edge = new int[3];
                    edge[0] = point1;
                    edge[1] = point2;
                    edge[2] = weight;
                    edges.add(edge);
                }
                catch(Exception e) {
                    condition = false;
                }
            }
            pointer++;
        }
        reader.close();
        if(edges.size() != m) {
            System.out.println("Incorrect input!");
            return new HashSet<>();
        }return edges;
    }

}