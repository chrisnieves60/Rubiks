package com.example.rubiks;

import javafx.scene.Group;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

//this class is to contain the cubes visual representation
public class RubiksCubeView {
    private Group cubeGroup;

    public RubiksCubeView() { //constructor!
        cubeGroup = new Group();

        //initialize and position Rubik's Cube Cubies
        double cubieSize = 1.0; // Size of each cubie
        double gap = 0.1; // Gap between cubies

        //initialize and position colorized stickers for the rubiks cube
        double stickerSize = cubieSize - 0.2; //Slightly smaller than the cubie
        double stickerDepth = 0.05; //very thin sticker
        double offset = (cubieSize + stickerDepth) / 2;  // Calculate offset once

        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    //positions cubies
                    double xPosition = (x - 1) * (cubieSize + gap);
                    double yPosition = (y - 1) * (cubieSize + gap);
                    double zPosition = (z - 1) * (cubieSize + gap);

                    Box cubie = new Box(cubieSize, cubieSize, cubieSize);
                    cubie.setTranslateX(xPosition);
                    cubie.setTranslateY(yPosition);
                    cubie.setTranslateZ(zPosition);
                    cubie.setMaterial(new PhongMaterial(Color.DARKMAGENTA));
                    //FOR X
                    if (x==0) {

                        Box sticker = new Box(stickerDepth, stickerSize, stickerSize);
                        sticker.setTranslateX(xPosition-offset);
                        sticker.setTranslateY(yPosition);
                        sticker.setTranslateZ(zPosition);
                        sticker.setMaterial(new PhongMaterial(Color.RED));
                        cubeGroup.getChildren().add(sticker);
                    }
                    if (x==2) {

                        Box sticker = new Box(stickerDepth, stickerSize, stickerSize);
                        sticker.setTranslateX(xPosition+offset);
                        sticker.setTranslateY(yPosition);
                        sticker.setTranslateZ(zPosition);
                        sticker.setMaterial(new PhongMaterial(Color.BLUE));
                        cubeGroup.getChildren().add(sticker);
                    }
                    //FOR Y
                    if (y==0) {

                        Box sticker = new Box(stickerSize, stickerDepth, stickerSize);
                        sticker.setTranslateX(xPosition);
                        sticker.setTranslateY(yPosition-offset);
                        sticker.setTranslateZ(zPosition);
                        sticker.setMaterial(new PhongMaterial(Color.GREEN));
                        cubeGroup.getChildren().add(sticker);
                    }
                    if (y==2) {

                        Box sticker = new Box(stickerSize, stickerDepth, stickerSize);
                        sticker.setTranslateX(xPosition);
                        sticker.setTranslateY(yPosition+offset);
                        sticker.setTranslateZ(zPosition);
                        sticker.setMaterial(new PhongMaterial(Color.YELLOW));
                        cubeGroup.getChildren().add(sticker);
                    }
                    //FOR Z
                    if (z==0) {

                        Box sticker = new Box(stickerSize, stickerSize, stickerDepth);
                        sticker.setTranslateX(xPosition);
                        sticker.setTranslateY(yPosition);
                        sticker.setTranslateZ(zPosition-offset);
                        sticker.setMaterial(new PhongMaterial(Color.ORANGE));
                        cubeGroup.getChildren().add(sticker);
                    }
                    if (z==2) {

                        Box sticker = new Box(stickerSize, stickerSize, stickerDepth);
                        sticker.setTranslateX(xPosition);
                        sticker.setTranslateY(yPosition);
                        sticker.setTranslateZ(zPosition+offset);
                        sticker.setMaterial(new PhongMaterial(Color.WHITE));
                        cubeGroup.getChildren().add(sticker);
                    }

                    cubeGroup.getChildren().add(cubie);
                }
            }
        }
    }

    //method to get visual representation of cube
    public Group getCubeGroup() {
        return cubeGroup;
    }
}
