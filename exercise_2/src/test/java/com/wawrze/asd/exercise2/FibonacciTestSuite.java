package com.wawrze.asd.exercise2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;
import java.util.stream.IntStream;

public class FibonacciTestSuite {

    private Scanner reader;
    private Fibonacci fibonacci = new Fibonacci();

    @Before
    public void beforeTest() {
        File file = null;
        try {
            file = new File("In0201.txt");
            reader = new Scanner(file);
        }
        catch(IOException e) {}
    }

    @After
    public void afterTest() {
        reader.close();
    }

    @Test
    public void testFibonacci1() {
        //Given
        int n = 400;
        int[] result;
        String tmpResult1;
        String[] tmpResult2;
        //When
        fibonacci.runAlgorithm(n);
        tmpResult1 = reader.nextLine();
        tmpResult2 = tmpResult1.split(", ");
        result = new int[tmpResult2.length];
        IntStream.iterate(0, i -> ++i)
                .limit(tmpResult2.length)
                .forEach(i -> result[i] = Integer.valueOf(tmpResult2[i]));
        //Then
        Assert.assertEquals(15, result.length);
        Assert.assertEquals(0, result[0]);
        Assert.assertEquals(1, result[1]);
        Assert.assertEquals(1, result[2]);
        Assert.assertEquals(2, result[3]);
        Assert.assertEquals(3, result[4]);
        Assert.assertEquals(5, result[5]);
        Assert.assertEquals(8, result[6]);
        Assert.assertEquals(13, result[7]);
        Assert.assertEquals(21, result[8]);
        Assert.assertEquals(34, result[9]);
        Assert.assertEquals(55, result[10]);
        Assert.assertEquals(89, result[11]);
        Assert.assertEquals(144, result[12]);
        Assert.assertEquals(233, result[13]);
        Assert.assertEquals(377, result[14]);
    }

    @Test
    public void testFibonacci2() {
        //Given
        int n = 0;
        int[] result;
        String tmpResult1;
        String[] tmpResult2;
        //When
        fibonacci.runAlgorithm(n);
        tmpResult1 = reader.nextLine();
        tmpResult2 = tmpResult1.split(", ");
        result = new int[tmpResult2.length];
        IntStream.iterate(0, i -> ++i)
                .limit(tmpResult2.length)
                .forEach(i -> result[i] = Integer.valueOf(tmpResult2[i]));
        //Then
        Assert.assertEquals(1, result.length);
        Assert.assertEquals(0, result[0]);
    }

    @Test
    public void testFibonacci3() {
        //Given
        int n = 1;
        int[] result;
        String tmpResult1;
        String[] tmpResult2;
        //When
        fibonacci.runAlgorithm(n);
        tmpResult1 = reader.nextLine();
        tmpResult2 = tmpResult1.split(", ");
        result = new int[tmpResult2.length];
        IntStream.iterate(0, i -> ++i)
                .limit(tmpResult2.length)
                .forEach(i -> result[i] = Integer.valueOf(tmpResult2[i]));
        //Then
        Assert.assertEquals(1, result.length);
        Assert.assertEquals(0, result[0]);
    }

    @Test
    public void testFibonacci4() {
        //Given
        int n = 2;
        int[] result;
        String tmpResult1;
        String[] tmpResult2;
        //When
        fibonacci.runAlgorithm(n);
        tmpResult1 = reader.nextLine();
        tmpResult2 = tmpResult1.split(", ");
        result = new int[tmpResult2.length];
        IntStream.iterate(0, i -> ++i)
                .limit(tmpResult2.length)
                .forEach(i -> result[i] = Integer.valueOf(tmpResult2[i]));
        //Then
        Assert.assertEquals(1, result.length);
        Assert.assertEquals(0, result[0]);
    }

    @Test
    public void testFibonacci5() {
        //Given
        int n = 4200;
        int[] result;
        String tmpResult1;
        String[] tmpResult2;
        //When
        fibonacci.runAlgorithm(n);
        tmpResult1 = reader.nextLine();
        tmpResult2 = tmpResult1.split(", ");
        result = new int[tmpResult2.length];
        IntStream.iterate(0, i -> ++i)
                .limit(tmpResult2.length)
                .forEach(i -> result[i] = Integer.valueOf(tmpResult2[i]));
        //Then
        Assert.assertEquals(20, result.length);
        Assert.assertEquals(4181, result[19]);
    }

}