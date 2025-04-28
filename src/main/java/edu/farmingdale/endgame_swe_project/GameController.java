package edu.farmingdale.endgame_swe_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.web.WebView;
import javafx.fxml.Initializable;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class GameController implements Initializable{
    @FXML
    private WebView webview;

    @FXML
    void backToMainMenu(ActionEvent event) throws IOException {
        MainApplication.setRoot("main_menu");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        webview.getEngine().load("https://www.chess.com");
    }
}
