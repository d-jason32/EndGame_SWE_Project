package edu.farmingdale.endgame_swe_project;

import javafx.event.ActionEvent;
import java.io.IOException;

/**
 * Registration controller class.
 * @author Jason Devaraj
 */
public class RegistrationController {
    public void RegisterButtonAction(ActionEvent actionEvent) {
    }

    /**
     * If the back button is clicked, the application changes to the login screen.
     * @param actionEvent the event triggered by clicking the back button
     * @throws IOException if the FXML file cannot be loaded
     */
    public void backButtonAction(ActionEvent actionEvent) throws IOException {
        MainApplication.setRoot("login");
    }
}
