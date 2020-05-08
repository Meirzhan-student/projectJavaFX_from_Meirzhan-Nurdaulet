package sample;

import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Font;

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
            signUpNewGuest();
        });
    }
    private void signUpNewGuest() {
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
    }
}

