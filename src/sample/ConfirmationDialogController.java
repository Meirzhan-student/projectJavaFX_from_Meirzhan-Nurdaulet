package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class ConfirmationDialogController {
    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private Button cancelButt;

    @FXML
    private Button confirmButt;

    @FXML
    private Label textMessage;

    private Stage s;
    private boolean result = false;

    @FXML
    void setStage(Stage s){
        this.s = s;
    }

    @FXML
    boolean getResult(){
        return result;
    }

    @FXML
    void setMessage(String str){
        textMessage.setText(str);
    }

    @FXML
    void cancel(ActionEvent event) throws SQLException {
        result = false;
        s.close();
    }

    @FXML
    void confirm(ActionEvent event) {
        result = true;
        s.close();
    }

    @FXML
    void initialize() {

    }

}
