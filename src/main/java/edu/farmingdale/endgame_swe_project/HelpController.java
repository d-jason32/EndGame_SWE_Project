package edu.farmingdale.endgame_swe_project;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import java.io.IOException;

public class HelpController {
    @FXML
    private Button backButton;

    @FXML
    private BorderPane borderPane;

    @FXML
    private StackPane loginBox;

    @FXML
    private TextFlow words;

    @FXML
    void backButtonAction(ActionEvent event) throws IOException {
        MainApplication.setRoot("database_view");
    }

    @FXML
    void load(){
        Text paragraph = new Text("""
                                        How do you use connect? Just press connect, the data base is already 
                                        configured to connect.
                                        
                                        How to use Display All? Make sure you are connected to the database and 
                                        just click the button.
                                        
                                        How to use insert? Put every single field into the textfields and 
                                        press insert.
                                        
                                        How to use query ID? Just enter the ID of the person and click on the 
                                        button. 
                                        
                                        How to use delete id?Enter the ID of the person and click 
                                        on the button.
                                        
                                        How to use edit ID? Enter the ID of the person and click on the 
                                        button.
                                      """);
        paragraph.setFont(Font.font("Segoe UI", 8));
        words.getChildren().add(paragraph);
    }
}
