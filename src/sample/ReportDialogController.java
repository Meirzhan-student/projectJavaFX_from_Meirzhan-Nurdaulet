package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class ReportDialogController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button okButt;

    @FXML
    private Label textMessage;

    private Stage s;

    @FXML
    void ok(ActionEvent event) {
        s.close();
    }

    @FXML
    void setStage(Stage s){
        this.s = s;
    }

    @FXML
    void setMessage(String str){
        textMessage.setText(str);
    }

    @FXML
    void initialize() {

    }
}

