package edu.farmingdale.endgame_swe_project;

import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import io.github.sashirestela.openai.SimpleOpenAI;
import io.github.sashirestela.openai.domain.chat.ChatMessage;
import io.github.sashirestela.openai.domain.chat.ChatRequest;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import static edu.farmingdale.endgame_swe_project.MainApplication.cdbop;

/**
 * Class that connects a database to the GUI.
 * @author Jason Devaraj
 */
public class DatabaseController implements Initializable {
    @FXML
    TextField id, first_name, last_name;

    @FXML
    private TableView<Player> tv;

    @FXML
    private TableColumn<Player, Integer> tv_id;

    @FXML
    private TableColumn<Player, String> tv_fn, tv_ln;

    @FXML
    ImageView img_view;

    @FXML
    private TextArea feedback;

    private final ObservableList<Player> data = FXCollections.observableArrayList();

    String report;

    /**
     * Initializes table view.
     * @param url
     * @param resourceBundle
     */
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        tv_id.setCellValueFactory(new PropertyValueFactory<>("id"));
        tv_fn.setCellValueFactory(new PropertyValueFactory<>("firstName"));
        tv_ln.setCellValueFactory(new PropertyValueFactory<>("lastName"));

        tv.setItems(data);
    }

    /**
     * Opens finder to select an image.
     */
    @FXML
    protected void showImage() {
        File file= (new FileChooser()).showOpenDialog(img_view.getScene().getWindow());
        if(file!=null){
            img_view.setImage(new Image(file.toURI().toString()));
        }
    }

    /**
     * Change from the database to the main menu.
     * @param event
     * @throws IOException
     */
    @FXML
    void mainMenuButton(ActionEvent event) throws IOException {
        MainApplication.setRoot("main_menu");
    }

    /**
     * Allows you to select an item in a table.
     * @param mouseEvent
     */
    @FXML
    protected void selectedItemTV(MouseEvent mouseEvent) {
        Player p= tv.getSelectionModel().getSelectedItem();
        first_name.setText(p.getFirstName());
        last_name.setText(p.getLastName());
    }

    /**
     * Open up a page that shows the help screen.
     * @param event
     * @throws IOException
     */
    @FXML
    void helpButton(ActionEvent event) throws IOException {
        MainApplication.setRoot("help");
    }

    /**
     * Method to connect to a database.
     * @param event
     */
    @FXML
    void connectButton(ActionEvent event) {
        try {
            cdbop.connectToDatabase();
            feedback.setText("Connected to " + cdbop.MYSQL_SERVER_URL);
        } catch (Exception e) {
            feedback.setText(String.valueOf(e));
        }
        display();
    }

    /**
     * Method to enter student id and delete it from the database.
     * @param event
     */
    @FXML
    void deleteByID(ActionEvent event) {
        cdbop.delete(id.getText());
        display();
        feedback.setText("Deleted!");
    }

    /**
     * Assigns display to the display button.
     * @param event
     */
    @FXML
    void displayButton(ActionEvent event) {
        display();
    }

    /**
     * Adds database to the table view.
     */
    void display(){
        data.clear();
        data.addAll(cdbop.displayAllUsers());
        tv.setItems(data);
    }

    /**
     * Edit a student record based on their id.
     * @param event
     * @throws SQLException
     */
    @FXML
    void editButton(ActionEvent event) throws SQLException {
        String num = id.getText();
        String firstName = first_name.getText();
        String lastName = last_name.getText();

        cdbop.editUser(num, firstName, lastName);
        feedback.setText("Edit user: " + firstName + " " + lastName);
        display();

    }

    /**
     * Insert student into the database.
     * @param event
     */
    @FXML
    void insertButton(ActionEvent event) {
        String num = id.getText();
        String firstName = first_name.getText();
        String lastName = last_name.getText();

        cdbop.insertUser(num, firstName, lastName);
        feedback.setText("Added user: " + firstName + " " + lastName);
        display();
    }

    /**
     * Query button gets the id from the text field, searches the database
     * and displays the entire Player.
     * @param event
     */
    @FXML
    void queryButton(ActionEvent event) {
        String userID = id.getText();
        String s = cdbop.queryUser(userID);
        feedback.setText(s);
    }

    @FXML
    void generateBracket(ActionEvent event) throws IOException {
        pdfGeneration();
    }

    public void chatGPT(){
        // The message sent to the OpenAI API model.
        String messageToChatbot = "Create a tournament bracket for everyone here. Just show me the bracket in text." + data.toString();

        System.out.println(messageToChatbot);
        String apiKey = "";

        var openAI = SimpleOpenAI.builder()
                .apiKey(apiKey)
                .build();

        var chatRequest = ChatRequest.builder()
                .model("gpt-4o")
                .message(ChatMessage.SystemMessage.of("Please check your answer."))
                .message(ChatMessage.UserMessage.of(messageToChatbot))
                .temperature(0.0)
                .maxCompletionTokens(300)
                .build();

        var futureChat = openAI.chatCompletions().create(chatRequest);
        var chatResponse = futureChat.join();
        report = chatResponse.firstContent();
        feedback.setText(chatResponse.firstContent());
    }


    void pdfGeneration() throws IOException {
        try (PdfWriter w = new PdfWriter("generated_bracket.pdf");
             PdfDocument pdf = new PdfDocument(w);
             Document doc = new Document(pdf)) {
            chatGPT();
            doc.add(new Paragraph(report));
        }
    }

}