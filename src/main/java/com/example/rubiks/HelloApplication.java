package com.example.rubiks;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
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

import static javafx.scene.input.KeyCode.R;

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
        //instantiate Rubiks cube view
        RubiksCubeView rubiksCubeView = new RubiksCubeView();
        faceRotation faceRotation = new faceRotation();
        world.getChildren().add(rubiksCubeView.getCubeGroup());
        world.getChildren().add(faceRotation.tempCubeGroup());

        // Set up the main scene and display it
        Group root = new Group();
        root.getChildren().add(subScene);
        stage.setTitle("Rubik's Cube Application");
        Scene scene = new Scene(root, 800, 600);
        scene.setOnKeyPressed((KeyEvent event) -> {
            switch (event.getCode()) {
                case R: //call rotation method when r is pressed
                    faceRotation.performRotation("front", rubiksCubeView, true);
                    rubiksCubeView.updateCubieMembership();
                    /*for(int i=0; i<3; i++) {
                        for (int j = 0; j < 3; j++) {
                            System.out.println("rotation membership of front elements:"+ rubiksCubeView.getCubie(0, i, j).getRotationMember() + "rubikscubeelement x: 0" + "Y: "+ i +" Z: "+j);
                        }
                   */
                    break;
                case T: //call rotation method when r is pressed
                    faceRotation.performRotation("back", rubiksCubeView, true);
                    rubiksCubeView.updateCubieMembership();

                    break;
                case Y: //call rotation method when r is pressedt

                    faceRotation.performRotation("left", rubiksCubeView, true);
                    rubiksCubeView.updateCubieMembership();
                    break;
                case F: //call rotation method when r is pressed

                    faceRotation.performRotation("right", rubiksCubeView, true);
                    rubiksCubeView.updateCubieMembership();
                    break;
                case G: //call rotation method when r is pressed

                    faceRotation.performRotation("up", rubiksCubeView, true);
                    rubiksCubeView.updateCubieMembership();
                    break;
                case H: //call rotation method when r is pressed

                    faceRotation.performRotation("down", rubiksCubeView, true);
                    rubiksCubeView.updateCubieMembership();
                    break;



            }
        });
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

