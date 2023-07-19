package com.example.rubiks;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import javafx.scene.paint.PhongMaterial;
import javafx.stage.Stage;
import javafx.scene.shape.Box;
import javafx.scene.Group;
import javafx.scene.transform.Rotate;
import javafx.scene.PerspectiveCamera;
import javafx.scene.SubScene;
import javafx.scene.SceneAntialiasing;

public class HelloApplication extends Application {
    private SubScene subScene;
    private MouseHandler mouseHandler;

    @Override
    public void start(Stage stage) {
        Group world = new Group();//this contains all other 3d objects, this is the WORLD
        subScene = new SubScene(world, 800, 600, true, SceneAntialiasing.BALANCED);

        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1);
        camera.setFarClip(1000.0);
        camera.setFieldOfView(30);

        Group cameraHolder = new Group();
        cameraHolder.getChildren().add(camera);
        world.getChildren().add(cameraHolder);

        camera.setTranslateZ(-15);

        subScene.setCamera(camera);

        Group cubeGroup = new Group();
        world.getChildren().add(cubeGroup);

        double cubieSize = 1.0; // Size of each cubie
        double gap = 0.1; // Gap between cubies
        for (int x = 0; x < 3; x++) {
            for (int y = 0; y < 3; y++) {
                for (int z = 0; z < 3; z++) {
                    double xPosition = (x - 1) * (cubieSize + gap);
                    double yPosition = (y - 1) * (cubieSize + gap);
                    double zPosition = (z - 1) * (cubieSize + gap);

                    Box cubie = new Box(cubieSize, cubieSize, cubieSize);
                    cubie.setTranslateX(xPosition);
                    cubie.setTranslateY(yPosition);
                    cubie.setTranslateZ(zPosition);
                    cubie.setMaterial(new PhongMaterial(Color.RED));

                    cubeGroup.getChildren().add(cubie);
                }
            }
        }

        Group root = new Group();
        root.getChildren().add(subScene);
        stage.setTitle("Rubik's Cube Application");
        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.show();

        mouseHandler = new MouseHandler(subScene, cameraHolder);
    }

    public static void main(String[] args) {
        launch();
    }
}

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

        scene.setOnMousePressed(event -> {
            anchorX = event.getSceneX();
            anchorY = event.getSceneY();
            anchorAngleX = rotateX.getAngle();
            anchorAngleY = rotateY.getAngle();
        });

        scene.setOnMouseDragged(event -> {
            rotateY.setAngle(anchorAngleY + (anchorX - event.getSceneX()) * dragRate);
            rotateX.setAngle(anchorAngleX - (anchorY - event.getSceneY()) * dragRate);
        });
    }
}

