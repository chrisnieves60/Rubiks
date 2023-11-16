package com.example.rubiks;
//this class maintains internal state of the cube (2d array)

public class RubiksCube {
    //best way to implement would be 6 2d arrays.

    private char[][] front = new char[3][3]; //2d character array = new 2d character array of size 3x3
    private char[][] back = new char[3][3];
    private char[][] left = new char[3][3];
    private char[][] right = new char[3][3];
    private char[][] up = new char[3][3];
    private char[][] down = new char[3][3];

    public char[][] getUp(){
        return up;
    }
    public char[][] getDown(){
        return down;
    } public char[][] getLeft(){
        return left;
    }
    public char[][] getRight(){
        return right;
    }

    //initialize each face with its color
    public RubiksCube() {
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                front[i][j] = 'R';
                back[i][j] = 'O';
                left[i][j] = 'G';
                right[i][j] = 'B';
                up[i][j] = 'W';
                down[i][j] = 'Y';
            }
        }
    }

}