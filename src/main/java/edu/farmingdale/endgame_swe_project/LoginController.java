package edu.farmingdale.endgame_swe_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import java.io.IOException;

public class LoginController {
    @FXML
    private BorderPane borderPane;

    @FXML
    private StackPane loginBox;

    @FXML
    private Button loginButton;

    @FXML
    private Button registerButton;

    /**
     * Go from log in to the main menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void loginButtonAction(ActionEvent event) throws IOException {
        MainApplication.setRoot("main_menu");
    }

    /**
     * Go from login to registration.
     * @param event
     * @throws IOException
     */
    @FXML
    void registerButtonAction(ActionEvent event) throws IOException {
        MainApplication.setRoot("registration");
    }
}
