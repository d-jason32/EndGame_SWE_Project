module edu.farmingdale.endgame_swe_project {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;
    requires simple.openai;
    requires layout;
    requires kernel;
    requires java.net.http;
    requires javafx.web;



    opens edu.farmingdale.endgame_swe_project to javafx.fxml;
    exports edu.farmingdale.endgame_swe_project;
}