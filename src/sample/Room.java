package sample;

import java.io.IOException;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Random;
import java.util.ResourceBundle;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Room {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private TextArea infoText;

    @FXML
    private Button removeBttn;

    @FXML
    private Label roomNumber;

    @FXML
    private Label personQuan;

    @FXML
    private RadioButton deLuxeRadio;

    @FXML
    private Button chooseBttn;

    @FXML
    private Button removePerson;

    @FXML
    private Button addPerson;

    @FXML
    private ToggleGroup Room;

    @FXML
    private RadioButton presidentRadio;

    @FXML
    private RadioButton villaRadio;

    @FXML
    private Button generateBttn;

    @FXML
    private Button backBttn;



    typeRoom room = new typeRoom();

    @FXML
    void handleAddRoom(ActionEvent event) throws IOException {

        DecimalFormat currency = new DecimalFormat("$,###.00");

        if (villaRadio.isSelected()){
            room.setType(1);
            room.setPrice(999.99);
            room.setQuantity(Integer.parseInt(personQuan.getText()));
            room.setNumber(Integer.parseInt(roomNumber.getText()));
            infoText.appendText(villaRadio.getText() + " "
                    + currency.format(room.getPrice()) + "   "
                    + room.getQuantity() + " "  + " person(-s) "
                    + " Your room number is: " + " " + room.getNuumber()
                            + "\n");


        } else if (deLuxeRadio.isSelected()){
            room.setType(2);
            room.setPrice(1599.99);
            room.setQuantity(Integer.parseInt(personQuan.getText()));
            room.setNumber(Integer.parseInt(roomNumber.getText()));
            infoText.appendText(deLuxeRadio.getText() + " "
                    + currency.format(room.getPrice()) + "   "
                    + room.getQuantity() + " " + " person(-s)" + " "
                    + " Your room number is: " + " " + room.getNuumber()
                    + "\n");

        } else if (presidentRadio.isSelected()){
            room.setType(3); // fix this!
            room.setPrice(2999.99);
            room.setQuantity(Integer.parseInt(personQuan.getText()));
            room.setNumber(Integer.parseInt(roomNumber.getText()));
            infoText.appendText(presidentRadio.getText() + " "
                    + currency.format(room.getPrice()) + "   "
                    + room.getQuantity() + " " + " person(-s) "
                    + " Your room number is: " + " " + room.getNuumber()
                    + "\n");

        } else {
            infoText.appendText("Oops! Something is wrong\n");

            Stage s = new Stage(StageStyle.TRANSPARENT);
            s.initModality(Modality.APPLICATION_MODAL);
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ReportDialog.fxml"));
            Scene scene = new Scene((Parent)loader.load());
            ReportDialogController controller = (ReportDialogController)loader.getController();
            controller.setMessage("You haven't choose any room!");
            controller.setStage(s);
            scene.setFill(Color.TRANSPARENT);
            s.setScene(scene);
            s.show();
        }

        infoText.appendText("Have a good day!\n");
        infoText.setStyle("-fx-text-fill: aqua; -fx-font-size: 16px;");
    }


    @FXML
    void handleRemoveRoom(ActionEvent event) {
        villaRadio.setSelected(false);
        deLuxeRadio.setSelected(false);
        presidentRadio.setSelected(false);
        personQuan.setText(null);
        infoText.setText(null);
    }


    @FXML
    private void handleAddPerson(ActionEvent event) {
        room.setQuantity(room.getQuantity() + 1);
    }

    @FXML
    private void handleRemovePerson(ActionEvent event) {
        room.setQuantity(room.getQuantity() - 1);
    }

    @FXML
    void handleGenerateNumber(ActionEvent event) {
        Random random = new Random();
        int num = random.nextInt(100);
        room.setNumber(room.getNuumber() + num);
    }

    @FXML
    void initialize() {
        room.setQuantity(0);
        room.quantityProperty().addListener(new ChangeListener<Object>(){
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                personQuan.setText(String.valueOf(room.getQuantity()));
            }
        });

        room.setNumber(0);
        room.numberProperty().addListener(new ChangeListener<Object>(){
            @Override
            public void changed(ObservableValue<? extends Object> observable, Object oldValue, Object newValue) {
                roomNumber.setText(String.valueOf(room.getNuumber()));
            }
        });

        backBttn.setOnAction(actionEvent -> {
            backBttn.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/HomePage.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();
        });
    }
}


