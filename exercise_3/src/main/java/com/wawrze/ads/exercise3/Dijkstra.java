package com.wawrze.ads.exercise3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;

public class Dijkstra implements Algorithm {

    private PrintWriter writer;
    private Scanner reader;
    private final static int MAX_WEIGHT = 100;

    private int[][] dijkstra(int[][] input) {
        int pointsQuantity = input[0][0];
        int sourcePoint = input[0][1];
        int[][] distancesMatrix = new int[pointsQuantity][pointsQuantity];
        int[] unknownDistanceFromSource = new int[pointsQuantity];
        boolean[] isDistanceKnown = new boolean[pointsQuantity];
        int[] previousPoint = new int[pointsQuantity];
        Set<Integer> unvisitedPoints = new HashSet<>();
        for(int i = 1;i <= pointsQuantity;i++) {
            for(int j = 0;j < pointsQuantity;j++) {
                distancesMatrix[i - 1][j] = input[i][j];
            }
            unknownDistanceFromSource[i - 1] = MAX_WEIGHT * 10 - 1;
            previousPoint[i - 1] = MAX_WEIGHT * 10 - 1;
            isDistanceKnown[i - 1] = false;
            unvisitedPoints.add(i - 1);
        }
        unknownDistanceFromSource[sourcePoint - 1] = 0;
        while(!unvisitedPoints.isEmpty()) {
            int min = MAX_WEIGHT * 10;
            int point = MAX_WEIGHT;
            for(int i = 0;i < unknownDistanceFromSource.length;i++) {
                if(unknownDistanceFromSource[i] < min && !isDistanceKnown[i]) {
                    min = unknownDistanceFromSource[i];
                    point = i;
                }
            }
            unvisitedPoints.remove(point);
            isDistanceKnown[point] = true;
            for(int i = 0;i < pointsQuantity;i++) {
                if(unvisitedPoints.contains(i)) {
                    if(distancesMatrix[point][i] <= MAX_WEIGHT) {
                        int distance = unknownDistanceFromSource[point];

                            distance += distancesMatrix[point][i];

                        if(distance < unknownDistanceFromSource[i]) {
                            unknownDistanceFromSource[i] = distance;
                            previousPoint[i] = point;
                        }
                    }
                }
            }
        }
        int[][] result = new int[2][pointsQuantity];
        result[0] = unknownDistanceFromSource;
        for(int i = 0;i < pointsQuantity;i++) {
            if(previousPoint[i] <= MAX_WEIGHT) {
                result[1][i] = previousPoint[i] + 1;
            }
            else {
                result[1][i] = -1;
            }
        }
        return result;
    }

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        String o;
        do {
            System.out.println("Choose option:");
            System.out.println("(f) Run algorithm from file \"In0304.txt\"");
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
                int n = rand.nextInt(100) + 1;
                int m = rand.nextInt(n) + 1;
                int[][] randomInput = new int[n][n];
                for(int i = 0;i < n;i++) {
                    for(int j = 0;j < n;j++) {
                        int r = rand.nextInt(2);
                        if(r == 1 && i != j) {
                            randomInput[i][j] = rand.nextInt(MAX_WEIGHT);
                        }
                        else {
                            randomInput[i][j] = MAX_WEIGHT * 10 - 1;
                        }
                    }
                }
                File file = new File("In0304.txt");
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
                StringBuilder input = new StringBuilder();
                input.append(n + " " + m + "\n");
                for(int i = 0;i < n;i++) {
                    for(int j = 0;j < n;j++) {
                        input.append(randomInput[i][j] + "\t");
                    }
                    input.append("\n");
                }
                writer.print(input.toString());
                System.out.println("Generated input");
                System.out.println(input.toString());
                System.out.println("saved to file \"In0304.txt\"");
                writer.close();
                break;
            case "f":
                int[][] inputArray = fileReader();
                runAlgorithm(inputArray);
                break;
            case "m":
                System.out.print("Enter nodes quantity: ");
                n = sc.nextInt();
                System.out.print("Enter source nod: ");
                m = sc.nextInt();
                inputArray = new int[n+1][n];
                inputArray[0][0] = n;
                inputArray[0][1] = m;
                for(int i = 1;i <= n;i++) {
                    for(int j = 0;j < n;j++) {
                        System.out.println("Enter distance from nod " + (i) + " to nod " + (j + 1) + ": ");
                        inputArray[i][j] = sc.nextInt();
                    }
                }
                runAlgorithm(inputArray);
                break;
            default:
                break;
        }
    }

    public void runAlgorithm(int[][] input) {
        int[][] result = dijkstra(input);
        StringBuilder resultStringified = new StringBuilder();
        resultStringified.append("dist[ ");
        for(int i = 0;i < result[0].length;i++) {
            resultStringified.append(result[0][i] + " ");
        }
        resultStringified.append("]\npred[ ");
        for(int i = 0;i < result[1].length;i++) {
            resultStringified.append(result[1][i] + " ");
        }
        resultStringified.append("]");
        fileWriter(resultStringified.toString());
        System.out.println("Result:");
        System.out.println(resultStringified.toString());
        System.out.println("saved to file Out0303.txt");
    }

    private void fileWriter(String result) {
        File file = new File("Out0304.txt");
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
        writer.print(result);
        writer.close();
    }

    private int[][] fileReader() {
        File file = null;
        try {
            file = new File("In0304.txt");
            reader = new Scanner(file);
        }
        catch(IOException e) {
            System.out.println("File " + file.getName() + " couldn't be opened!");
            return new int[0][0];
        }
        int n;
        int m;
        int [][] input;
        try {
            n = reader.nextInt();
            m = reader.nextInt();
            input = new int[n+1][n];
            input[0][0] = n;
            input[0][1] = m;
            for(int i = 1;i <= n;i++) {
                for(int j = 0;j < n;j++) {
                    int tmp = reader.nextInt();
                    if(tmp > MAX_WEIGHT) {
                        input[i][j] = MAX_WEIGHT * 10 - 1;
                    }
                    else {
                        input[i][j] = tmp;
                    }
                }
            }
        }
        catch(Exception e) {
            System.out.println("Incorrect input in file \"In304.txt\"!");
            return new int[0][0];
        }
        finally {
            reader.close();
        }
        return input;
    }

}