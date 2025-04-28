package edu.farmingdale.endgame_swe_project;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import java.io.IOException;

public class MainMenuController {
    @FXML
    private Button button1;

    @FXML
    private Button button2;

    @FXML
    private Button button3;

    @FXML
    private BorderPane menuBorder;

    boolean isDarkMode = false;

    /**
     * Change from main menu to database.
     * @param event
     * @throws IOException
     */
    @FXML
    void goToDatabase(ActionEvent event) throws IOException {
        MainApplication.setRoot("database_view");
    }

    /**
     * Change from main menu to profile.
     * @param event
     * @throws IOException
     */
    @FXML
    void goToProfile(ActionEvent event) throws IOException {
        MainApplication.setRoot("profile");
    }

    /**
     * Change from main menu to log in screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void logoutButton(ActionEvent event) throws IOException {
        MainApplication.setRoot("login");
    }

    /**
     * Switches from light to dark and vice versa.
     * @param event
     */
    @FXML
    void changeToDark(ActionEvent event) {
        ObservableList<String> stylesheets = menuBorder.getStylesheets();
        stylesheets.clear();
        /*
        If the stylesheet is in light mode, change it to dark mode.
         */
        if (isDarkMode) {
            stylesheets.add(getClass().getResource("styles.css").toExternalForm());
        } else {
            stylesheets.add(getClass().getResource("darkstyles.css").toExternalForm());
        }
        isDarkMode = !isDarkMode;
    }

    @FXML
    void playChess(ActionEvent event) throws IOException {
        MainApplication.setRoot("game");
    }
}
