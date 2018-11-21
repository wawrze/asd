package com.wawrze.ads.exercise3;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class LongestCommonSubsequence implements Algorithm {

    private PrintWriter writer;
    private Scanner reader;

    private String lcsAlgorithm(String[] input) {
        String s1 = input[0];
        String s2 = input[1];
        int n = s1.length() + 1;
        int m = s2.length() + 1;
        int array[][] = new int[n][m];
        for(int i = 0;i < n;i++) {
            for(int j = 0;j < m;j++) {
                if(i == 0 || j ==0) {
                    array[i][j] = 0;
                }
                else if(s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    array[i][j] = array[i - 1][j - 1] + 1;
                }
                else {
                    array[i][j] = Math.max(array[i][j - 1], array[i - 1][j]);
                }
            }
        }
        char[] result = new char[array[n - 1][m - 1]];
        int i = n - 1;
        int j = m - 1;
        int k = result.length - 1;
        while(i >= 0 && j >= 0 && k >= 0) {
            if(array[i - 1][j] == array[i][j]) {
                i--;
            }
            else if(array[i][j - 1] == array[i][j]) {
                j--;
            }
            else {
                result[k] = s1.charAt(i - 1);
                i--;
                j--;
                k--;
            }
        }
        return String.copyValueOf(result);
    }

    @Override
    public void menu() {
        Scanner sc = new Scanner(System.in);
        String o;
        do {
            System.out.println("Choose option:");
            System.out.println("(f) Run algorithm from file \"In0301.txt\"");
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
                StringBuilder line1 = new StringBuilder();
                for(int i = 0;i < n;i++) {
                    line1.append((char) (rand.nextInt(58) + 65));
                }
                n = rand.nextInt(100) + 1;
                StringBuilder line2 = new StringBuilder();
                for(int i = 0;i < n;i++) {
                    line2.append((char) (rand.nextInt(58) + 65));
                }
                File file = new File("In0301.txt");
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
                writer.println(line1.toString());
                writer.print(line2.toString());
                System.out.println("Generated strings:");
                System.out.println(line1.toString());
                System.out.println(line2.toString());
                System.out.println("saved to file \"In0301.txt\"");
                writer.close();
                break;
            case "f":
                String[] input = fileReader();
                if(input.length < 2) {
                    System.out.println("Incorrect input!!!");
                }
                else {
                    runAlgorithm(input);
                }
                break;
            case "m":
                input = new String[2];
                System.out.println("Enter first string:");
                input[0] = sc.nextLine();
                System.out.println("Enter second string:");
                input[1] = sc.nextLine();
                runAlgorithm(input);
                break;
            default:
                break;
        }
    }

    public void runAlgorithm(String[] input) {
        String result = lcsAlgorithm(input);
        fileWriter(result);
        System.out.println("Result string:");
        System.out.println(result);
        System.out.println("saved to file \"Out0301.txt\"");
    }

    private void fileWriter(String result) {
        File file = new File("Out0301.txt");
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

    private String[] fileReader() {
        File file = null;
        try {
            file = new File("In0301.txt");
            reader = new Scanner(file);
        }
        catch(IOException e) {
            System.out.println("File " + file.getName() + " couldn't be opened!");
            return new String[0];
        }
        String[] input = new String[2];
        input[0] = reader.nextLine();
        input[1] = reader.nextLine();
        reader.close();
        return input;
    }

}