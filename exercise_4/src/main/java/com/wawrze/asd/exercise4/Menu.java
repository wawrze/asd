package com.wawrze.asd.exercise4;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Menu {

    private PrintWriter writer;
    private Scanner reader;

    public void menu() {
        Scanner sc = new Scanner(System.in);
        String o;
        do {
            System.out.println("Choose option:");
            System.out.println("(f) Run algorithm from file \"in0501.txt\"");
            System.out.println("(g) Generate random input file");
            System.out.println("(x) Exit");
            o = sc.nextLine();
            option(o);
        } while (!o.equals("x"));
    }

    private void option(String o) {
        Random rand = new Random();
        switch (o) {
            case "g":
                int numberOfBanks = rand.nextInt(20) + 1;
                int safetyFactor = rand.nextInt(10) + 1;
                double chfPrice = 2 + rand.nextInt(4) + rand.nextDouble();
                double value = chfPrice * 100;
                long tmp = Math.round(value);
                chfPrice = (double) tmp / 100;
                double eurPrice = 3 + rand.nextInt(3) + rand.nextDouble();
                value = eurPrice * 100;
                tmp = Math.round(value);
                eurPrice = (double) tmp / 100;
                int firstBank = rand.nextInt(numberOfBanks) + 1;
                int secondBank;
                double[][] srMatrix = new double[numberOfBanks][numberOfBanks];
                for(int i = 0;i < numberOfBanks;i++) {
                    for(int j = 0;j < numberOfBanks;j++) {
                        srMatrix[i][j] = rand.nextDouble();
                        value = srMatrix[i][j] * 100;
                        tmp = Math.round(value);
                        srMatrix[i][j] = (double) tmp / 100;
                    }
                }
                do {
                    secondBank = rand.nextInt(numberOfBanks) + 1;
                } while(secondBank == firstBank);
                File file = new File("in0501.txt");
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
                StringBuilder input = new StringBuilder();
                input.append(numberOfBanks + " " + safetyFactor + " " + chfPrice + " " + eurPrice + " " + firstBank
                        + " " + secondBank);
                for(int i = 0;i < numberOfBanks;i++) {
                    input.append("\n");
                    for(int j = 0;j < numberOfBanks;j++) {
                        input.append(srMatrix[i][j] + " ");
                    }
                }
                writer.print(input.toString());
                System.out.println("Generated input:\n" + input.toString() + "\nsaved to file \"in0501.txt\"");
                writer.close();
                break;
            case "f":

//                runAlgorithm();
                break;
            default:
                break;
        }
    }

    private void fileWriter(int[][] result) {
        File file = new File("out0501.txt");
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
        StringBuilder resultStringified = new StringBuilder();
        for(int i = 0;i < result[0].length;i++) {
            resultStringified.append(result[0][i] + " ");
        }
        resultStringified.append("\n");
        for(int i = 0;i < result[1].length;i++) {
            resultStringified.append(result[1][i] + " ");
        }
        writer.print(resultStringified);
        writer.close();
    }

    private Input fileReader() {
        File file = null;
        try {
            file = new File("in0501.txt");
            reader = new Scanner(file);
        } catch (IOException e) {
            System.out.println("File " + file.getName() + " couldn't be opened!");
            return null;
        }
        int numberOfBanks = reader.nextInt();
        int safetyFactor = reader.nextInt();
        double chfPrice = reader.nextDouble();
        double eurPrice = reader.nextDouble();
        int firstBank = reader.nextInt();
        int secondBank = reader.nextInt();
        double[][] srMatrix = new double[numberOfBanks][numberOfBanks];
        for(int i = 0;i < numberOfBanks;i++) {
            for(int j = 0;j < numberOfBanks;j++) {
                srMatrix[i][j] = reader.nextDouble();
            }
        }
        Input input = new Input(numberOfBanks, safetyFactor, chfPrice, eurPrice, firstBank, secondBank, srMatrix);
        reader.close();
        return input;
    }

}