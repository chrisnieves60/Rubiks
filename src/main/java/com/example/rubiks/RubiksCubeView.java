package com.example.rubiks;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;

import java.util.*;
import java.util.stream.Collectors;

//this class is to contain the cubes visual representation
public class RubiksCubeView {
    private Group cubeGroup;
    private Cubie[][][] cube = new Cubie[3][3][3];

    public RubiksCubeView() { //constructor!

        cubeGroup = new Group();

        //initialize and position Rubik's Cube Cubies
        double cubieSize = 1.0; // Size of each cubie
        double gap = 0.1; // Gap between cubies


        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {

                    //positions cubies
                    double xPosition = (x - 1) * (cubieSize + gap);
                    double yPosition = (y - 1) * (cubieSize + gap);
                    double zPosition = (z - 1) * (cubieSize + gap);
                    Point3D position = new Point3D(xPosition, yPosition, zPosition);

                    Cubie cubie = createCubie(position, cubieSize, x, y, z);
                    cube[x][y][z] = cubie;
                    cubie.initRotationMember(x, y, z); //init each cubie with rotation member
                    cubeGroup.getChildren().add(cubie);


                }
            }
        }
    }


    public Group getCubeGroup() {
        return cubeGroup;
    }

    public Cubie[][][] getCubieArr() {
        return cube;
    }


    private Cubie createCubie(Point3D position, double cubieSize, int x, int y, int z) {
        Map<String, Color> stickers = new HashMap<>();

        // Determine the stickers for this cubie based on its position
        if (x == 0) { // Front face of the cube
            stickers.put("front", Color.RED);
        }
        if (x == 2) { // Back face of the cube
            stickers.put("back", Color.ORANGE);
        }
        if (y == 0) { // Bottom face of the cube
            stickers.put("bottom", Color.WHITE);
        }
        if (y == 2) { // Top face of the cube
            stickers.put("top", Color.YELLOW);
        }
        if (z == 0) { // Left face of the cube
            stickers.put("left", Color.BLUE);
        }
        if (z == 2) { // Right face of the cube
            stickers.put("right", Color.GREEN);
        }

        // Create a new Cubie with the determined stickers
        return new Cubie(position, cubieSize, stickers);
    }

    public void reparentGroup(Cubie cubie, Group newParent, Group originalParent) {
                originalParent.getChildren().remove(cubie);
                newParent.getChildren().add(cubie);

                Point3D globalCoords = cubie.localToScene(0, 0, 0);
                Point3D localCoords = newParent.sceneToLocal(globalCoords);

                cubie.setTranslateX(localCoords.getX());
                cubie.setTranslateY(localCoords.getY());
                cubie.setTranslateZ(localCoords.getZ());
                System.out.println("We removed cubie from original group and added to new group");

    }
    public void reparentCubiesBack(Group newParent, Cubie cubie, Group originalParent) {
        // Remove the cubie from the temporary group
        newParent.getChildren().remove(cubie);

        // Add the cubie back to its original parent group
        originalParent.getChildren().add(cubie);

        // Convert the cubie's global coordinates (in the scene) to the local coordinates of the original parent
        Point3D globalCoords = cubie.localToScene(0, 0, 0);
        Point3D localCoords = originalParent.sceneToLocal(globalCoords);

        // Set the cubie's new local coordinates relative to the original parent
        cubie.setTranslateX(localCoords.getX());
        cubie.setTranslateY(localCoords.getY());
        cubie.setTranslateZ(localCoords.getZ());
    }




    public Cubie getCubie(int x, int y, int z){
        return cube[x][y][z];
    }
    public void updateCubieMembership() {

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    cube[x][y][z].clearRotationMember();
                    if (x == 0) {
                        cube[x][y][z].addRotationMember("front");
                        if (y == 0) {
                            cube[x][y][z].addRotationMember("up");

                        } else if (y == 2) {
                            cube[x][y][z].addRotationMember("down");
                        } else if (z == 0) {
                            cube[x][y][z].addRotationMember("left");
                        } else if (z == 2) {
                            cube[x][y][z].addRotationMember("right");
                        }
                    }
                    if (x == 2) {
                        cube[x][y][z].addRotationMember("back");
                        if (y == 0) {
                            cube[x][y][z].addRotationMember("up");

                        } else if (y == 2) {
                            cube[x][y][z].addRotationMember("down");
                        } else if (z == 0) {
                            cube[x][y][z].addRotationMember("left");
                        } else if (z == 2) {
                            cube[x][y][z].addRotationMember("right");
                        }
                    }
                    if (y == 0) {
                        cube[x][y][z].addRotationMember("up");
                        if (x == 0) {
                            cube[x][y][z].addRotationMember("front");

                        } else if (x == 2) {
                            cube[x][y][z].addRotationMember("back");
                        } else if (z == 0) {
                            cube[x][y][z].addRotationMember("left");
                        } else if (z == 2) {
                            cube[x][y][z].addRotationMember("right");
                        }
                    }
                    if (y == 2) {
                        cube[x][y][z].addRotationMember("down");
                        if (x == 0) {
                            cube[x][y][z].addRotationMember("front");

                        } else if (x == 2) {
                            cube[x][y][z].addRotationMember("back");
                        } else if (z == 0) {
                            cube[x][y][z].addRotationMember("left");
                        } else if (z == 2) {
                            cube[x][y][z].addRotationMember("right");
                        }
                    }
                    if (z == 0) {
                        cube[x][y][z].addRotationMember("left");
                        if (x == 0) {
                            cube[x][y][z].addRotationMember("front");

                        } else if (x == 2) {
                            cube[x][y][z].addRotationMember("back");
                        } else if (y == 0) {
                            cube[x][y][z].addRotationMember("up");
                        } else if (y == 2) {
                            cube[x][y][z].addRotationMember("down");
                        }
                    }
                    if (z == 2) {
                        cube[x][y][z].addRotationMember("right");
                        if (x == 0) {
                            cube[x][y][z].addRotationMember("front");

                        } else if (x == 2) {
                            cube[x][y][z].addRotationMember("back");
                        } else if (y == 0) {
                            cube[x][y][z].addRotationMember("up");
                        } else if (y == 2) {
                            cube[x][y][z].addRotationMember("down");
                        }
                    }
                }
            }
        }
    }
}










/*
    public List<Node> getNodesToRotate(String rotationType, RubiksCubeView rubiksCubeView) {
        List<Node> nodesToRotate = new ArrayList<>();
        Cubie[][][] cube = rubiksCubeView.getCube();

        switch (rotationType) {
            case "front":
                // Add all cubies on the front face to the rotation group
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        nodesToRotate.add(cube[0][i][j]);
                    }
                }
                break;
            // Add cases for other rotation types like "back", "left", "right", "up", "down"
            // For each case, add the appropriate cubies to the nodesToRotate list
        }

        return nodesToRotate;
    }



    public Cubie[][][] getCube() {
        return cube;
    }
    public Group getWorldGetFrontRotationGroup() {
        return worldGetFrontRotationGroup;
    }*/

