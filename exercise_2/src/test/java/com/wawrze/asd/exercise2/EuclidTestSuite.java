package com.wawrze.asd.exercise2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class EuclidTestSuite {

    private Scanner reader;
    private Euclid euclid = new Euclid();

    @Before
    public void beforeTest() {
        File file = null;
        try {
            file = new File("Out0203.txt");
            reader = new Scanner(file);
        }
        catch(IOException e) {}
    }

    @After
    public void afterTest() {
        reader.close();
    }

    @Test
    public void testEuclid1() {
        //Given
        int a = 54;
        int b = 69;
        int result;
        String[] tmpResult;
        //When
        euclid.runAlgorithm(a, b);
        tmpResult = reader.nextLine().split("=");
        result = Integer.valueOf(tmpResult[1]);
        //Then
        Assert.assertEquals(3, result);
    }

    @Test
    public void testEuclid2() {
        //Given
        int a = 54;
        int b = 0;
        int result;
        String[] tmpResult;
        //When
        euclid.runAlgorithm(a, b);
        tmpResult = reader.nextLine().split("=");
        result = Integer.valueOf(tmpResult[1]);
        //Then
        Assert.assertEquals(54, result);
    }

    @Test
    public void testEuclid3() {
        //Given
        int a = 1127;
        int b = 867;
        int result;
        String[] tmpResult;
        //When
        euclid.runAlgorithm(a, b);
        tmpResult = reader.nextLine().split("=");
        result = Integer.valueOf(tmpResult[1]);
        //Then
        Assert.assertEquals(51, result);
    }

    @Test
    public void testEuclid4() {
        //Given
        int a = 282;
        int b = 78;
        int result;
        String[] tmpResult;
        //When
        euclid.runAlgorithm(a, b);
        tmpResult = reader.nextLine().split("=");
        result = Integer.valueOf(tmpResult[1]);
        //Then
        Assert.assertEquals(6, result);
    }

    @Test
    public void testEuclid5() {
        //Given
        int a = 243;
        int b = 111;
        int result;
        String[] tmpResult;
        //When
        euclid.runAlgorithm(a, b);
        tmpResult = reader.nextLine().split("=");
        result = Integer.valueOf(tmpResult[1]);
        //Then
        Assert.assertEquals(3, result);
    }

}