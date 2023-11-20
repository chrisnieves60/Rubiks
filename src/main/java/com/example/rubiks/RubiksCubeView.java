package com.example.rubiks;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.ArrayList;
import java.util.List;

//this class is to contain the cubes visual representation
public class RubiksCubeView {
    private Group cubeGroup;
    private Group frontRotationGroup; //this will hold all cubes/stickers to be rotated?

    public RubiksCubeView() { //constructor!
        cubeGroup = new Group();
        frontRotationGroup = new Group();
        cubeGroup.setId("cubegroup"); // Set a meaningful name for the group
        frontRotationGroup.setId("frontrotationgroup"); // Set a meaningful name for the group
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
                   // if(x==0){
                     //   System.out.println("Xpositon: " + xPosition +" yposition: "+ yPosition+ " zposition: " + zPosition);
                    //}
                    cubie.setTranslateX(xPosition);
                    cubie.setTranslateY(yPosition);
                    cubie.setTranslateZ(zPosition);
                    cubie.setMaterial(new PhongMaterial(Color.GRAY));
                    cubeGroup.getChildren().add(cubie); //add cubie to scene

                    if (x==0) {
                        //THIS REPRESENTS FRONT FACE
                        Box sticker = new Box(stickerDepth, stickerSize, stickerSize);
                        sticker.setTranslateX(xPosition-offset);
                        sticker.setTranslateY(yPosition);
                        sticker.setTranslateZ(zPosition);
                        sticker.setMaterial(new PhongMaterial(Color.RED));
                        cubeGroup.getChildren().add(sticker);
                        Point3D globalPosition = cubie.localToScene(cubie.getTranslateX(), cubie.getTranslateY(), cubie.getTranslateZ());
                        frontRotationGroup.getChildren().add(cubie);

                        frontRotationGroup.getChildren().add(sticker);//add sticker to rotationgroup
                    }
                    if (x==2) {

                        Box sticker = new Box(stickerDepth, stickerSize, stickerSize);
                        sticker.setTranslateX(xPosition+offset);
                        sticker.setTranslateY(yPosition);
                        sticker.setTranslateZ(zPosition);
                        sticker.setMaterial(new PhongMaterial(Color.ORANGE));
                        cubeGroup.getChildren().add(sticker);
                    }
                    //FOR Y
                    if (y==0) {

                        Box sticker = new Box(stickerSize, stickerDepth, stickerSize);
                        sticker.setTranslateX(xPosition);
                        sticker.setTranslateY(yPosition-offset);
                        sticker.setTranslateZ(zPosition);
                        sticker.setMaterial(new PhongMaterial(Color.WHITE));
                        cubeGroup.getChildren().add(sticker);
                        if(x==0){//TOP EDGE NEEDS TO BE ROTATED 90 DEG ->
                            frontRotationGroup.getChildren().add(sticker);
                        }
                    }
                    if (y==2) {

                        Box sticker = new Box(stickerSize, stickerDepth, stickerSize);
                        sticker.setTranslateX(xPosition);
                        sticker.setTranslateY(yPosition+offset);
                        sticker.setTranslateZ(zPosition);
                        sticker.setMaterial(new PhongMaterial(Color.YELLOW));
                        cubeGroup.getChildren().add(sticker);
                        if(x==0){
                            frontRotationGroup.getChildren().add(sticker);
                        }
                    }
                    //FOR Z
                    if (z==0) {

                        Box sticker = new Box(stickerSize, stickerSize, stickerDepth);
                        sticker.setTranslateX(xPosition);
                        sticker.setTranslateY(yPosition);
                        sticker.setTranslateZ(zPosition-offset);
                        sticker.setMaterial(new PhongMaterial(Color.BLUE));
                        cubeGroup.getChildren().add(sticker);
                        if(x==0){
                            frontRotationGroup.getChildren().add(sticker);
                        }
                    }
                    if (z==2) {

                        Box sticker = new Box(stickerSize, stickerSize, stickerDepth);
                        sticker.setTranslateX(xPosition);
                        sticker.setTranslateY(yPosition);
                        sticker.setTranslateZ(zPosition+offset);
                        sticker.setMaterial(new PhongMaterial(Color.GREEN));
                        cubeGroup.getChildren().add(sticker);
                        if(x==0){
                            frontRotationGroup.getChildren().add(sticker);
                        }
                    }

                }
            }
        }
    }

    //method to get visual representation of cube
    public Group getCubeGroup() {
        return cubeGroup;
    }
    public Group getFrontRotationGroup() {return frontRotationGroup;}
}
