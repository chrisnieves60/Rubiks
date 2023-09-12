package com.example.rubiks;

import javafx.scene.Group;
import javafx.scene.SubScene;
import javafx.scene.transform.Rotate;

// Class to handle mouse interactions
class MouseHandler {
    private double anchorX, anchorY;
    private double anchorAngleX = 0;
    private double anchorAngleY = 0;
    private final double dragRate = 0.1;
    private final Group cameraHolder;
    private final Rotate rotateX = new Rotate(0, Rotate.X_AXIS);
    private final Rotate rotateY = new Rotate(0, Rotate.Y_AXIS);

    public MouseHandler(SubScene scene, Group cameraHolder) {
        this.cameraHolder = cameraHolder;
        cameraHolder.getTransforms().addAll(rotateY, rotateX);

        // Capture initial values on mouse press
        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = rotateX.getAngle();
            anchorAngleY = rotateY.getAngle();
        });

        // Adjust rotations based on mouse drag
        scene.setOnMouseDragged(event -> {
            rotateY.setAngle(anchorAngleY + (anchorX - event.getSceneX()) * dragRate);
            rotateX.setAngle(anchorAngleX - (anchorY - event.getSceneY()) * dragRate);
        });
    }
}
