package edu.farmingdale.endgame_swe_project;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.animation.FadeTransition;
import javafx.util.Duration;
import java.io.IOException;
import java.util.Scanner;

/**
 * @author Jason Devaraj
 */
public class MainApplication extends Application {
    public static ConnDbOps cdbop;
    private static Scene scene;
    public Stage primaryStage;

    public static void main(String[] args) {
        cdbop = new ConnDbOps();
        launch();
    }

    static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml));
    }

    private static Parent loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(MainApplication.class.getResource(fxml + ".fxml"));
        return fxmlLoader.load();
    }

    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.primaryStage.setResizable(false);
        try {
            Parent root = FXMLLoader.load(getClass().getResource("splash_screen.fxml"));
            scene = new Scene(root, 1200, 800);
            primaryStage.setScene(scene);
            primaryStage.show();
            changeScene();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void changeScene() {
        try {
            Parent newRoot = FXMLLoader.load(getClass().getResource("login.fxml"));

            Scene currentScene = primaryStage.getScene();
            Parent currentRoot = currentScene.getRoot();
            FadeTransition fadeOut = new FadeTransition(Duration.seconds(4), currentRoot);
            fadeOut.setFromValue(1);
            fadeOut.setToValue(0);
            fadeOut.setOnFinished(e -> {
                scene.setRoot(newRoot);
            });
            fadeOut.play();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}