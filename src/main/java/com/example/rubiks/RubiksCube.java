package com.example.rubiks;
//this class maintains internal state of the cube (2d array)

public class RubiksCube {
    //best way to implement would be 6 2d arrays.
    private char[][] front = new char[3][3]; //2d character array = new 2d character array of size 3x3
    private char[][] back = new char[3][3];
    private char[][] left = new char[3][3];
    private char[][] right = new char[3][3];
    private char[][] up = new char [3][3];
    private char[][] down = new char[3][3];

    //initialize each face with its color
    public RubiksCube() {
        for (int i=0; i<3; i++){
            for (int j=0; j<3; j++){
                front[i][j] = 'R';
                back[i][j] = 'O';
                left[i][j] = 'G';
                right[i][j] = 'B';
                up[i][j] = 'W';
                down[i][j] = 'Y';
            }
        }
    }

    public frontRotationClockwise(RubiksCube cube){
        //white is UP, so up  has to probably first save the elements positions
        //that are being swapped out, and then swap them out.
        //so the bottom row of UP is being switched out
        //the top row of DOWN is being switched out
        //the right row of LEFT is being switched out
        //the left tow of RIGHT is being switched out
        char tempUp[][] = new char[1][3];
        char tempDown[][] = new char[1][3];
        char tempLeft[][] = new char[1][3];
        char tempRight[][] = new char[1][3];
        for(int i=0; i<3; i++){
            tempUp[0][i] = up[2][i]; //tempUp stores bottom row of up
            tempDown[0][i] = down[0][i]; //tempDown stores top row of down
            tempLeft[0][i] = left[i][2]; //tempLeft stores right row of left
            tempRight[0][i] = right[i][0]; //tempRight stores left row of right
        }
        //we have just stored the state of the sides in temporary values.
        //now, we can begin swapping in and out. we start with up(white)

        //so, goal is, we want UP to now be templeft!!!
        for(int i=0; i<3; i++){
            up[2][i] = tempLeft[0][i]; //set the bottom of up to the right of left
            down[0][i] = tempRight[0][2-i]; //set top row of down to
            left[i][2] = tempDown[0][i]; //set left's right to downs top row
        }//downs up row, is gonna be the left row of right




}