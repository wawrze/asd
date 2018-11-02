package com.wawrze.asd.exercise1;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class TestSuite {

    private Scanner reader;
    private Main main = new Main();

    @Before
    public void beforeTest() throws IOException {
        File file = new File("out.txt");
        if(!file.exists()) {
            try {
                file.createNewFile();
            }
            catch(IOException e) {}
        }
        reader = new Scanner(file);
    }

    @After
    public void afterTest() {
        reader.close();
    }

    @Test
    public void testAlgorithmsfromFile() throws Exception {
        //Given
        int[] array;
        int[] result;
        int result1;
        int result2;
        //When
        array = main.fileReader(new File("test.txt"));
        result = main.runAlgorithms(array);
        result1 = Integer.valueOf(reader.nextLine());
        result2 = Integer.valueOf(reader.nextLine());
        //Then
        Assert.assertEquals(4, result1);
        Assert.assertEquals(4, result2);
        Assert.assertEquals(4, result[0]);
        Assert.assertEquals(4, result[1]);
    }

    @Test
    public void testAlgorithmsFromGivenArray1() throws Exception {
        //Given
        int[] result;
        int result1;
        int result2;
        int[] array = {1, 6, 4, 7, 6, 6, 3, 7, 3, 9};
        //When
        result = main.runAlgorithms(array);
        result1 = Integer.valueOf(reader.nextLine());
        result2 = Integer.valueOf(reader.nextLine());
        //Then
        Assert.assertEquals(5, result1);
        Assert.assertEquals(5, result2);
        Assert.assertEquals(5, result[0]);
        Assert.assertEquals(5, result[1]);
    }

    @Test
    public void testAlgorithmsFromGivenArray2() throws Exception {
        //Given
        int[] result;
        int result1;
        int result2;
        int[] array = {2, 6, 2, 6, 7, 8, 7, 0, 8, 0, 9, 0, 0, 3, 1};
        //When
        result = main.runAlgorithms(array);
        result1 = Integer.valueOf(reader.nextLine());
        result2 = Integer.valueOf(reader.nextLine());
        //Then
        Assert.assertEquals(8, result1);
        Assert.assertEquals(8, result2);
        Assert.assertEquals(8, result[0]);
        Assert.assertEquals(8, result[1]);
    }

    @Test
    public void testAlgorithmsFromGivenArray3() throws Exception {
        //Given
        int[] result;
        int result1;
        int result2;
        int[] array = {9, 2, 5, 8, 9, 0, 9, 9, 1, 2, 3, 7, 4, 3, 3, 1, 2, 9, 0, 1};
        //When
        result = main.runAlgorithms(array);
        result1 = Integer.valueOf(reader.nextLine());
        result2 = Integer.valueOf(reader.nextLine());
        //Then
        Assert.assertEquals(10, result1);
        Assert.assertEquals(10, result2);
        Assert.assertEquals(10, result[0]);
        Assert.assertEquals(10, result[1]);
    }

}