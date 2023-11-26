package com.example.rubiks;

import javafx.animation.RotateTransition;
import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class faceRotation {
    private Group tempCubeGroup; // Group containing all cubies
    private Cubie[][][] cube; // 3D array representing the Rubik's cube

    public Group tempCubeGroup() {
        tempCubeGroup = new Group();
        tempCubeGroup.setId("default"); // Default ID
        return tempCubeGroup;
    }


    public void rotateFace(boolean clockwise, Runnable onRotationCompletion) {
        //group is the group that is to be rotated!!
        String axisOfRotation = tempCubeGroup.getId();
        ; //need to set this to x
        RotateTransition rotateTransition = new RotateTransition();
        rotateTransition.setDuration(Duration.seconds(1));
        rotateTransition.setNode(tempCubeGroup); //group being rotated

        switch (axisOfRotation) {
            case "x" -> rotateTransition.setAxis(Rotate.X_AXIS);
            case "y" -> rotateTransition.setAxis(Rotate.Y_AXIS);
            case "z" -> rotateTransition.setAxis(Rotate.Z_AXIS);
        }
        tempCubeGroup.setId("");


        rotateTransition.setByAngle(clockwise ? 90 : -90);

        rotateTransition.setCycleCount(1);
        rotateTransition.setAutoReverse(false);
        rotateTransition.setOnFinished(event -> onRotationCompletion.run());
        rotateTransition.play();

    }


    public void performRotation(String rotationType, RubiksCubeView rubiksCubeView, boolean clockwise) {
        // Determine the axis of rotation based on rotationType
        String axisOfRotation = "";
        switch (rotationType) {
            case "front":
            case "back":
                axisOfRotation = "x";
                break;
            case "left":
            case "right":
                axisOfRotation = "y";
                break;
            case "up":
            case "down":
                axisOfRotation = "z";
                break;
            // Add more cases if needed
        }
        tempCubeGroup.setId(axisOfRotation);

        // ... (previous code setting axisOfRotation)


        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                for (int k = 0; k < 3; k++) {
                    if (rubiksCubeView.getCubie(i, j, k).getRotationMember().contains(rotationType)) {
                        rubiksCubeView.reparentGroup(rubiksCubeView.getCubie(i, j, k), tempCubeGroup, rubiksCubeView.getCubeGroup());
                    }
                }
            }
        }

        rotateFace(clockwise, () -> {
            // After rotation, reparent all involved cubies back to the main group
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    for (int k = 0; k < 3; k++) {
                        if (rubiksCubeView.getCubie(i, j, k).getRotationMember().contains(rotationType)) {
                            rubiksCubeView.reparentCubiesBack(tempCubeGroup, rubiksCubeView.getCubie(i, j, k), rubiksCubeView.getCubeGroup());

                        }
                    }
                }
            }
            frontRotationClockwiseLogic(rubiksCubeView.getCubieArr());
        });





    }







    private void frontRotationClockwiseLogic(Cubie[][][] cube) {
        Cubie[] tempTop = new Cubie[3];

        // Storing the top row of cubies (which will move to the right side)
        for (int i = 0; i < 3; i++) {
            tempTop[i] = cube[0][0][i];  // Top row (white face)
        }

        // Move left (green face) to top (white face)
        for (int i = 0; i < 3; i++) {
            cube[0][0][i] = cube[0][i][0];  // Left side to top row
        }

        // Move bottom (orange face) to left (green face)
        for (int i = 0; i < 3; i++) {
            cube[0][i][0] = cube[0][2][i];  // Bottom row to left side
        }

        // Move right (blue face) to bottom (orange face)
        for (int i = 0; i < 3; i++) {
            cube[0][2][i] = cube[0][2-i][2];  // Right side to bottom row
        }

        // Move stored top (white face) to right (blue face)
        for (int i = 0; i < 3; i++) {
            cube[0][i][2] = tempTop[i];  // Stored top row to right side
        }
    }

/*
    private void frontRotationClockwiseLogic(Cubie[][][] cube) {
        Cubie[] tempRight = new Cubie[3];
        Cubie[] tempLeft = new Cubie[3];
        Cubie[] tempTop = new Cubie[3];
        Cubie[] tempBottom = new Cubie[3];
        for(int i=0; i<3; i++){
            //we wanna temporarily get all the rows on front race, then rotate!
            tempTop[i] = cube[0][0][i]; //store top row of red in tempTop
            tempRight[i] = cube[0][i][2]; //stoore right side cubies
            tempLeft[i] = cube[0][i][0]; //store left side cubies
            tempBottom[i] = cube[0][2][i]; //store bottom cubies
        }
        for(int i=0; i<3; i++){
            cube[0][0][i] = tempLeft[i];
            cube[0][i][2] = tempTop[i];
            cube[0][i][0] = tempBottom[i];
            cube[0][2][i] = tempRight[2-i];
        }



    }*/

    /*
private void backRotationClockwiseLogic(Cubie[][][] cube) {
        Cubie[] tempRight = new Cubie[3];
        Cubie[] tempLeft = new Cubie[3];
        Cubie[] tempTop = new Cubie[3];
        Cubie[] tempBottom = new Cubie[3];
        for(int i=0; i<3; i++){
            //we wanna temporarily get all the rows on front race, then rotate!
            tempTop[i] = cube[2][0][i]; //store top row of green in tempTop
            tempLeft[i] = cube[2][i][2]; //stoore left side cubies
            tempRight[i] = cube[2][i][0]; //store right side cubies
            tempBottom[i] = cube[2][2][i]; //store bottom cubies
        }
        for(int i=0; i<3; i++){
            cube[2][0][i] = tempLeft[i]; //top becomes
            cube[2][i][2] = tempBottom[2-i]; //left becomes tempBottom
            cube[2][i][0] = tempTop[i]; //right becomes
            cube[2][2][i] = tempRight[i]; //bottom becomes
        }
    }*/
    private void backRotationClockwiseLogic(Cubie[][][] cube) {
        Cubie[] tempTop = new Cubie[3];

        // Storing the top row of cubies (which will move to the left side)
        for (int i = 0; i < 3; i++) {
            tempTop[i] = cube[2][0][i];  // Top row of the back face
        }

        // Move right to top
        for (int i = 0; i < 3; i++) {
            cube[2][0][i] = cube[i][2][2];  // Right side to top row
        }

        // Move bottom to right
        for (int i = 0; i < 3; i++) {
            cube[i][2][2] = cube[2][2][2 - i];  // Bottom row to right side
        }

        // Move left to bottom
        for (int i = 0; i < 3; i++) {
            cube[2][2][i] = cube[2 - i][2][0];  // Left side to bottom row
        }

        // Move stored top to left
        for (int i = 0; i < 3; i++) {
            cube[i][2][0] = tempTop[2 - i];  // Stored top row to left side
        }
    }

/*
    private void leftRotationClockwiseLogic(Cubie[][][] cube) {
        Cubie[] tempRight = new Cubie[3];
        Cubie[] tempLeft = new Cubie[3];
        Cubie[] tempTop = new Cubie[3];
        Cubie[] tempBottom = new Cubie[3];
        for(int i=0; i<3; i++){
            //we wanna temporarily get all the rows on front race, then rotate!
            tempTop[i] = cube[i][0][0]; //store top row of green in tempTop
            tempRight[i] = cube[2][i][0]; //stoore right side of green cubies
            tempLeft[i] = cube[0][i][0]; //store left side of green cubies
            tempBottom[i] = cube[i][2][0]; //store bottom of green cubies
        }
        for(int i=0; i<3; i++){
            cube[0][0][i] = tempLeft[i];
            cube[0][i][2] = tempTop[i];
            cube[0][i][0] = tempBottom[i];
            cube[0][2][i] = tempRight[2-i];
        }



    }
    */
private void leftRotationClockwiseLogic(Cubie[][][] cube) {
    Cubie[] tempFront = new Cubie[3];

    // Storing the front column of cubies (which will move to the bottom)
    for (int i = 0; i < 3; i++) {
        tempFront[i] = cube[0][i][0];  // Front column of the left face
    }

    // Move bottom to front
    for (int i = 0; i < 3; i++) {
        cube[0][i][0] = cube[i][2][0];  // Bottom row to front column
    }

    // Move back to bottom
    for (int i = 0; i < 3; i++) {
        cube[i][2][0] = cube[2][2 - i][0];  // Back column to bottom row
    }

    // Move top to back
    for (int i = 0; i < 3; i++) {
        cube[2][i][0] = cube[2 - i][0][0];  // Top row to back column
    }

    // Move stored front to top
    for (int i = 0; i < 3; i++) {
        cube[i][0][0] = tempFront[2 - i];  // Stored front column to top row
    }
}
/*
    private void rightRotationClockwiseLogic(Cubie[][][] cube) {
        Cubie[] tempRight = new Cubie[3];
        Cubie[] tempLeft = new Cubie[3];
        Cubie[] tempTop = new Cubie[3];
        Cubie[] tempBottom = new Cubie[3];
        for(int i=0; i<3; i++){
            //we wanna temporarily get all the rows on front race, then rotate!
            tempTop[i] = cube[i][0][2]; //store top row of red in tempTop
            tempRight[i] = cube[2][i][2]; //stoore right side cubies
            tempLeft[i] = cube[0][i][2]; //store left side cubies
            tempBottom[i] = cube[i][2][2]; //store bottom cubies
        }
        for(int i=0; i<3; i++){
            cube[0][0][i] = tempLeft[i];
            cube[0][i][2] = tempTop[i];
            cube[0][i][0] = tempBottom[i];
            cube[0][2][i] = tempRight[2-i];
        }



    }
    */
private void rightRotationClockwiseLogic(Cubie[][][] cube) {
    Cubie[] tempFront = new Cubie[3];

    // Storing the front column of cubies (which will move to the top)
    for (int i = 0; i < 3; i++) {
        tempFront[i] = cube[2][i][2];  // Front column of the right face
    }

    // Move bottom to front
    for (int i = 0; i < 3; i++) {
        cube[2][i][2] = cube[i][2][2];  // Bottom row to front column
    }

    // Move back to bottom
    for (int i = 0; i < 3; i++) {
        cube[i][2][2] = cube[0][2 - i][2];  // Back column to bottom row
    }

    // Move top to back
    for (int i = 0; i < 3; i++) {
        cube[0][i][2] = cube[2 - i][0][2];  // Top row to back column
    }

    // Move stored front to top
    for (int i = 0; i < 3; i++) {
        cube[i][0][2] = tempFront[2 - i];  // Stored front column to top row
    }
}

    /*
    private void upRotationClockwiseLogic(Cubie[][][] cube) {
        Cubie[][] frontFaceCubies = new Cubie[3][3];
        Cubie[] tempRight = new Cubie[3];
        Cubie[] tempLeft = new Cubie[3];
        Cubie[] tempTop = new Cubie[3];
        Cubie[] tempBottom = new Cubie[3];
        for(int i=0; i<3; i++){
            //we wanna temporarily get all the rows on front race, then rotate!
            tempTop[i] = cube[2][0][i]; //store top row of red in tempTop
            tempRight[i] = cube[i][0][2]; //stoore right side cubies
            tempLeft[i] = cube[i][0][0]; //store left side cubies
            tempBottom[i] = cube[0][0][i]; //store bottom cubies
        }
        for(int i=0; i<3; i++){
            cube[i][0][0] = tempLeft[i];
            cube[0][0][i] = tempTop[2-i];
            cube[i][0][0] = tempBottom[2-i];
            cube[0][2][i] = tempRight[2-i];
        }



    }
*/
    private void upRotationClockwiseLogic(Cubie[][][] cube) {
        Cubie[] tempFront = new Cubie[3];

        // Storing the top row of the front face (which will move to the right)
        for (int i = 0; i < 3; i++) {
            tempFront[i] = cube[0][0][i];  // Front face, top row
        }

        // Front becomes right
        for (int i = 0; i < 3; i++) {
            cube[0][0][i] = cube[2-i][0][0];  // Left face, top row to front face, top row
        }

        // Right becomes back
        for (int i = 0; i < 3; i++) {
            cube[2-i][0][0] = cube[2][0][2-i];  // Back face, top row to right face, top row
        }

        // Back becomes left
        for (int i = 0; i < 3; i++) {
            cube[2][0][2-i] = cube[i][0][2];  // Right face, top row to back face, top row
        }

        // Left becomes front
        for (int i = 0; i < 3; i++) {
            cube[i][0][2] = tempFront[i];  // Stored front face, top row to left face, top row
        }
    }


    private void downRotationClockwiseLogic(Cubie[][][] cube) {
        Cubie[] tempRow = new Cubie[3];  // Temporary storage for a row of cubies

        // Store the bottom row of the front face
        for (int i = 0; i < 3; i++) {
            tempRow[i] = cube[0][2][i];  // Front face, bottom row
        }

        // Move the bottom row of the left face to the front face
        for (int i = 0; i < 3; i++) {
            cube[0][2][i] = cube[i][2][0];  // Left face, bottom row -> Front face, bottom row
        }

        // Move the bottom row of the back face to the left face
        for (int i = 0; i < 3; i++) {
            cube[i][2][0] = cube[2][2][2 - i];  // Back face, bottom row -> Left face, bottom row
        }

        // Move the bottom row of the right face to the back face
        for (int i = 0; i < 3; i++) {
            cube[2][2][i] = cube[2 - i][2][2];  // Right face, bottom row -> Back face, bottom row
        }

        // Move the stored bottom row of the front face to the right face
        for (int i = 0; i < 3; i++) {
            cube[i][2][2] = tempRow[2 - i];  // Stored front face, bottom row -> Right face, bottom row
        }
    }

    /*private void downRotationClockwiseLogic(Cubie[][][] cube) {
        Cubie[] tempRight = new Cubie[3];
        Cubie[] tempLeft = new Cubie[3];
        Cubie[] tempTop = new Cubie[3];
        Cubie[] tempBottom = new Cubie[3];
        for(int i=0; i<3; i++){
            //we wanna temporarily get all the rows on front race, then rotate!
            tempTop[i] = cube[0][2][i]; //store top row of red in tempTop
            tempRight[i] = cube[i][2][2]; //stoore right side cubies
            tempLeft[i] = cube[i][2][0]; //store left side cubies
            tempBottom[i] = cube[2][2][i]; //store bottom cubies
        }
        for(int i=0; i<3; i++){
            cube[0][2][i] = tempLeft[i]; //top becomes left
            cube[i][2][0] = tempBottom[i]; //left becomes bottom
            cube[2][2][i] = tempRight[i]; //buttom  becomes right
            cube[i][2][2] = tempTop[i]; //right becomes top
        }



    }*/





}
