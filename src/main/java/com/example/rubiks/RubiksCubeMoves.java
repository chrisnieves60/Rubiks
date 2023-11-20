package com.example.rubiks;

public class RubiksCubeMoves {

    //helper function  rotates a face given its 2D array.
    public void rotateFaceClockwise(char[][] face) {
        char temp = face[0][0];
        face[0][0] = face[2][0];
        face[2][0] = face[2][2];
        face[2][2] = face[0][2];
        face[0][2] = temp;

        temp = face[0][1];
        face[0][1] = face[1][0];
        face[1][0] = face[2][1];
        face[2][1] = face[1][2];
        face[1][2] = temp;
    }


    public void frontRotationClockwise(RubiksCube cube) {
        rotateFaceClockwise(cube.getFront());
        char[][] up = cube.getUp();
        char[][] down = cube.getDown();
        char[][] left = cube.getLeft();
        char[][] right = cube.getRight();
        char tempUp[][] = new char[1][3];
        char tempDown[][] = new char[1][3];
        char tempLeft[][] = new char[1][3];
        char tempRight[][] = new char[1][3];
        for (int i = 0; i < 3; i++) {
            tempUp[0][i] = up[2][i]; //tempUp stores bottom row of up
            tempDown[0][i] = down[0][i]; //tempDown stores top row of down
            tempLeft[0][i] = left[i][2]; //tempLeft stores right row of left
            tempRight[0][i] = right[i][0]; //tempRight stores left row of right
        }
        //we have just stored the state of the sides in temporary values.
        //now, we can begin swapping in and out. we start with up(white)

        //so, goal is, we want UP to now be templeft!!!
        for (int i = 0; i < 3; i++) {
            up[2][i] = tempLeft[0][i]; //set the bottom of up to the right of left
            down[0][i] = tempRight[0][2 - i]; //set top row of down to left row of right
            left[i][2] = tempDown[0][i]; //set left's right to downs top row
            right[i][0] = tempUp[0][i];
        }//downs up row, is gonna be the left row of right


    }
}
