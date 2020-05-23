package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignUpController {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private CheckBox FourCheckBox;

    @FXML
    private PasswordField PassButtton;

    @FXML
    private CheckBox FiveCheckBox;

    @FXML
    private TextField SurnameSignUp;

    @FXML
    private TextField NameSignUp;

    @FXML
    private TextField LoginButton;

    @FXML
    private Button SignupButton;

    @FXML
    private CheckBox ThreeCheckBox;

    @FXML
    void initialize() {



        SignupButton.setOnAction(actionEvent -> {
            try {
                signUpNewGuest();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }
    private void signUpNewGuest() throws IOException {
        Stage s = new Stage(StageStyle.TRANSPARENT);
        s.initModality(Modality.APPLICATION_MODAL);
        FXMLLoader loader = new FXMLLoader(getClass().getResource("ConfirmationDialog.fxml"));
        Scene scene = new Scene((Parent) loader.load());
        ConfirmationDialogController controller = (ConfirmationDialogController) loader.getController();
        controller.setMessage("This parameters will be saved.\n Will you continue?");
        controller.setStage(s);
        scene.setFill(Color.TRANSPARENT);
        s.setScene(scene);
        s.setWidth(260);
        s.setHeight(152);
        s.showAndWait();

        DatabaseHandler dbHandler = new DatabaseHandler();

        String name = NameSignUp.getText();
        String surname = SurnameSignUp.getText();
        String login = LoginButton.getText();
        String password = PassButtton.getText();
        String star ="";
            if(ThreeCheckBox.isSelected())
                star = "Three Stars";
            else if(FourCheckBox.isSelected())
                star = "Four Stars";
            else
                star = "Five Stars";

          Guests guests = new Guests(name, surname, login, password, star);

        dbHandler.signUpGuest(guests);

            SignupButton.getScene().getWindow().hide();
            FXMLLoader loade = new FXMLLoader();
            loade.setLocation(getClass().getResource("/sample/HomePage.fxml"));
            try {
                loade.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loade.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.show();

}
}

