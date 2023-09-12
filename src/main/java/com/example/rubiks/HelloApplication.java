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
import javafx.scene.paint.Color;

public class HelloApplication extends Application {
    private SubScene subScene;
    private MouseHandler mouseHandler;

    @Override
    public void start(Stage stage) {
        // Create the main 3D world container
        Group world = new Group();

        // Initialize a subscene for 3D rendering
        subScene = new SubScene(world, 800, 600, true, SceneAntialiasing.BALANCED);

        // Create and set up the camera
        PerspectiveCamera camera = new PerspectiveCamera(true);
        camera.setNearClip(0.1); //anything closer than 0.1 from camera wont render
        camera.setFarClip(1000.0); //anything farther than 1000.0 wont render
        camera.setFieldOfView(30); //FOV of vertical, is 30

        // Container for the camera, useful for transformations
        Group cameraHolder = new Group();
        cameraHolder.getChildren().add(camera);
        world.getChildren().add(cameraHolder);

        // Set initial camera position
        camera.setTranslateZ(-15);
        subScene.setCamera(camera);

        // Create a group for the Rubik's Cube
        Group cubeGroup = new Group();
        //instantiate Rubiks cube view
        RubiksCubeView rubiksCubeView = new RubiksCubeView();
        world.getChildren().add(rubiksCubeView.getCubeGroup());



        // Set up the main scene and display it
        Group root = new Group();
        root.getChildren().add(subScene);
        stage.setTitle("Rubik's Cube Application");
        Scene scene = new Scene(root, 800, 600);
        scene.setFill(Color.LIGHTBLUE); //set background color of main screen
        stage.setScene(scene);
        stage.show();

        // Attach mouse handler for 3D scene interactions
        mouseHandler = new MouseHandler(subScene, cameraHolder);
    }

    public static void main(String[] args) {
        launch();
    }
}

