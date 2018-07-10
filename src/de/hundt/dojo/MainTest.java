package de.hundt.dojo;


import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;


public class MainTest {


    @Test
    public void testGameField(){
        Main main = new Main(10,10,0.30f);
        System.out.println(main);
        Assert.assertEquals(30, main.countBombs());
    }

    @Test
    public void countNeighbours(){
        boolean[][] initField = {{false, true, false, false},
                {true, true, true, true},
                {false, false, false, false}};
        Main main = new Main(initField);
        Assert.assertEquals(3, main.countNeighbours(0, 0));
    }

    @Test
    public void testGameCount(){
        boolean[][] initField = {{false, true, false, false},
                                 {true, true, true, true},
                                 {false, false, false, false}};
        Main main = new Main(initField);
        int[][] countField = {{3, 3, 4, 2},
                {2, 3, 3, 1},
                {2, 3, 3, 2}};
        System.out.println(main);
        System.out.println(main.getPrintableCheatNote());
        int[][] cheatNote = main.getCheatNote();
        Assert.assertTrue(Arrays.deepEquals(countField, cheatNote));
    }

}