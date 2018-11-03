package com.wawrze.asd.exercise2;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class HanoiTestSuite {

    private Scanner reader;
    private Hanoi hanoi = new Hanoi();

    @Before
    public void beforeTest() {
        File file = null;
        try {
            file = new File("Out0202.txt");
            reader = new Scanner(file);
        }
        catch(IOException e) {}
    }

    @After
    public void afterTest() {
        reader.close();
    }

    @Test
    public void testHanoi1() {
        //Given
        int n = 3;
        String result;
        String expectedResult = "1->2, 1->3, 2->3, 1->2, 3->1, 3->2, 1->2";
        //When
        hanoi.runAlgorithm(n);
        reader.nextLine();
        result = reader.nextLine();
        //Then
        Assert.assertTrue(result.contains(expectedResult));
    }

    @Test
    public void testHanoi2() {
        //Given
        int n = 4;
        String result;
        String expectedResult = "1->3, 1->2, 3->2, 1->3, 2->1, 2->3, 1->3, 1->2, 3->2, 3->1, 2->1, 3->2, 1->3, 1->2, " +
                "3->2";
        //When
        hanoi.runAlgorithm(n);
        reader.nextLine();
        result = reader.nextLine();
        //Then
        Assert.assertTrue(result.contains(expectedResult));
    }

    @Test
    public void testHanoi3() {
        //Given
        int n = 5;
        String result;
        String expectedResult = "1->2, 1->3, 2->3, 1->2, 3->1, 3->2, 1->2, 1->3, 2->3, 2->1, 3->1, 2->3, 1->2, 1->3, " +
                "2->3, 1->2, 3->1, 3->2, 1->2, 3->1, 2->3, 2->1, 3->1, 3->2, 1->2, 1->3, 2->3, 1->2, 3->1, 3->2, 1->2";
        //When
        hanoi.runAlgorithm(n);
        reader.nextLine();
        result = reader.nextLine();
        //Then
        Assert.assertTrue(result.contains(expectedResult));
    }

}