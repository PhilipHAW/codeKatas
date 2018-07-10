package de.hundt.dojo;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

    private boolean[][] gameField;
    private int sizeX;
    private int sizeY;

    public Main(int sizeX, int sizeY, float bombFactor) {
        this.sizeX = sizeX;
        this.sizeY = sizeY;
        this.gameField = new boolean[sizeX][sizeY];
        initBombField(bombFactor);
    }

    public Main(boolean[][] initField){
        this.setGameField(initField);
    }

    private void initBombField(float bombFactor) {
        if (bombFactor < 0 || bombFactor > 1){
            throw new IllegalArgumentException("illegal bombFactor: " + bombFactor);
        }

        int numberOfFields = sizeX * sizeY;
        int numberOfBombs = (int) (numberOfFields * bombFactor);

        for (int i =0; i< numberOfBombs; i++) {
            boolean found = false;
            while (! found) {
                int coorX = (int) Math.round(Math.random() * (sizeX - 1));
                int coorY = (int) Math.round(Math.random() * (sizeY - 1));
                if(!gameField[coorX][coorY]){
                    gameField[coorX][coorY] = true;
                    found = true;
                }
            }
        }
    }

    public boolean[][] getGameField() {
        return gameField;
    }

    public int countBombs(){
        int bombCount = 0;
        for(int i = 0; i < gameField.length; i++){
            for(int j = 0; j < gameField[i].length; j++){
                bombCount = bombCount + (gameField[i][j] ? 1 : 0);
            }
        }
        return bombCount;
    }

    @Override
    public String toString(){
        StringBuilder picture = new StringBuilder();
        for(int i = 0; i < gameField.length; i++){
            for(int j = 0; j < gameField[i].length; j++){
                picture.append((gameField[i][j] ? "X " : ". "));
            }
            picture.append("\n");
        }
        return picture.toString();
    }

    public int countNeighbours (int fieldX, int fieldY) {
        int count = 0;
        for (int i = fieldX - 1; i < fieldX +2; i++) {
            for (int j = fieldY - 1; j < fieldY +2; j++) {
                if(!(i == fieldX && j == fieldY)) {
                    if (i >= 0 && j >= 0 && i < this.gameField.length && j < this.gameField[i].length) {
                        count = count + (this.gameField[i][j] ? 1 : 0);
                    }
                }
            }
        }
        return count;
    }

    public int[][] getCheatNote() {
        int[][] cheatNote = new int[sizeX][sizeY];
        for(int i = 0; i < gameField.length; i++){
            for(int j = 0; j < gameField[i].length; j++){
                cheatNote[i][j] = this.countNeighbours(i, j);
            }
        }
        return cheatNote;
    }

    public String getPrintableCheatNote(){
        StringBuilder picture = new StringBuilder();
        for(int i = 0; i < gameField.length; i++){
            for(int j = 0; j < gameField[i].length; j++){
                picture.append((gameField[i][j] ? "X " : countNeighbours(i, j) + " "));
            }
            picture.append("\n");
        }
        return picture.toString();
    }

    public void setGameField(boolean[][] newField){
        gameField = newField;
        sizeX = newField.length;
        sizeY = newField[0].length;
    }
    public static void main(String[] args) {


    }
}
