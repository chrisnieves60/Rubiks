package com.example.rubiks;

import javafx.geometry.Point3D;
import javafx.scene.Group;
import javafx.scene.Node;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.scene.shape.Box;

import java.util.*;

public class Cubie extends Group {
    private Box core; //core of cubie
    private List<String> rotationMember = new ArrayList<String>();   //each CUBIE (cubie has stickers and cubes) gets a string arr. called
                                                    //rotation member. add/remove if cubies get moved somewhere else.
                                                    //essentially. represents, how they are allowed to be rotated.

    private Map<String, Box> stickers; //map of stickers on each face!



    public void addRotationMember(String newRotationMember) {
        rotationMember.add(newRotationMember);
    }
    public void clearRotationMember() {
        rotationMember.clear();
    }

    public void initRotationMember(int x, int y, int z){
        if(x==0) { //its a red face. Its tags should alwaays include front
            rotationMember.add("front");
        }
        if(x==2){ //its an orange face. tags always will be back
            rotationMember.add("back");
        }
        if(y==0){
            rotationMember.add("up");
        }
        if(y==2){
            rotationMember.add("down");
        }
        if (z==0){
            rotationMember.add("left");
        }
        if (z==2){
            rotationMember.add("right");
        }

        //called within initialization. Each of the 27 cubies all get rotation members right?
        //as it itterates, some cubies , WILL be getting more than one rotation member.
    }

    public Cubie(Point3D position, double size, Map<String, Color> initialStickers) {
        // Initialize the Cubie, including the core Box
        core = new Box(size, size, size);

        PhongMaterial coreMaterial = new PhongMaterial();
        coreMaterial.setDiffuseColor(Color.GRAY); // Set the color you prefer
        core.setMaterial(coreMaterial);

        this.getChildren().add(core);

        // Set the position of the Cubie
        this.setTranslateX(position.getX());
        this.setTranslateY(position.getY());
        this.setTranslateZ(position.getZ());

        // Initialize stickers
        stickers = new HashMap<>();
        initialStickers.forEach((face, color) -> {
            Box sticker = createSticker(face, color, size);
            stickers.put(face, sticker);
            this.getChildren().add(sticker);
        });
    }
    private Box createSticker(String face, Color color, double cubieSize) {
        double stickerSize = this.core.getWidth() - 0.2; // Sticker is slightly smaller than the cubie
        double stickerDepth = 0.05; // The depth of the sticker

        Box sticker = new Box();

        // Determine the size and orientation of the sticker based on the face
        if (face.equals("front") || face.equals("back")) {
            sticker = new Box(stickerDepth, stickerSize, stickerSize);
        } else if (face.equals("left") || face.equals("right")) {
            sticker = new Box(stickerSize, stickerSize, stickerDepth);
        } else if (face.equals("top") || face.equals("bottom")) {
            sticker = new Box(stickerSize, stickerDepth, stickerSize);
        }

        // Set the color of the sticker
        PhongMaterial stickerMaterial = new PhongMaterial(color);
        sticker.setMaterial(stickerMaterial);

        // Position the sticker on the correct face of the cubie
        positionSticker(sticker, face, cubieSize);

        return sticker;
    }

    private void positionSticker(Box sticker, String face, double cubieSize) {
        // Assuming the cubie is centered at the origin, calculate half the side length
        double halfCubieSize = cubieSize / 2;
        // The depth of the sticker should be negligible for positioning, but if it's not,
        // you might want to adjust by half the depth to get the sticker flush with the cube surface.
        double halfStickerDepth = sticker.getDepth() / 2;

        // Reset sticker translation
        sticker.setTranslateX(0);
        sticker.setTranslateY(0);
        sticker.setTranslateZ(0);

        // Translate the sticker to the correct face of the cubie
        switch (face) {
            case "front":
                sticker.setTranslateX(-0.52);
                break;
            case "back":
                sticker.setTranslateX(0.52);
                break;
            case "left":
                sticker.setTranslateZ(-0.52);
                break;
            case "right":
                sticker.setTranslateZ(0.52);
                break;
            case "top":
                sticker.setTranslateY(0.52);
                break;
            case "bottom":
                sticker.setTranslateY(-0.52);
                break;
        }
    }

    public List<String> getRotationMember() {
        return rotationMember;
    }



}