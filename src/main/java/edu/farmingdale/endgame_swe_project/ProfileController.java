package edu.farmingdale.endgame_swe_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

import java.io.IOException;

public class ProfileController {
    /**
     * Goes back to the main menu from the profile page.
     * @param event
     * @throws IOException
     */
    @FXML
    void backButtonAction(ActionEvent event) throws IOException {
        MainApplication.setRoot("main_menu");
    }

    /**
     * Allows you to open the finder application.
     * @param event the event triggered by clicking the upload button.
     */
    @FXML
    void uploadProfileButton(ActionEvent event) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choose a Profile Picture");

        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Open File explorer
        fileChooser.showOpenDialog(stage);
    }
}
