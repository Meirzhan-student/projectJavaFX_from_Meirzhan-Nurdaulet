package sample;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.animations.Shake;

public class Controller {

    @FXML
    private ResourceBundle resources;

    @FXML
    private URL location;

    @FXML
    private PasswordField PassButtton;

    @FXML
    private Button Signupbutton;

    @FXML
    private TextField LoginButton;

    @FXML
    private Button SigninButton;

    @FXML
    private AnchorPane LabelWelcome;

    @FXML
    void initialize() {
        SigninButton.setOnAction(actionEvent -> {
            String loginText = LoginButton.getText().trim();
            String loginPassword = PassButtton.getText().trim();
            if (!loginText.equals("") && !loginPassword.equals("")){
                loginUser(loginText, loginPassword);
            
            }
            else
                System.out.println("The field is empty!");
        });

        Signupbutton.setOnAction(actionEvent -> {
            Signupbutton.getScene().getWindow().hide();
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("/sample/signup.fxml"));
            try {
                loader.load();
            } catch (IOException e) {
                e.printStackTrace();
            }
            Parent root = loader.getRoot();
            Stage stage = new Stage();
            stage.setScene(new Scene(root));
            stage.showAndWait();
        });
    }

    private void loginUser(String loginText, String loginPassword) {
        DatabaseHandler dbHandler = new DatabaseHandler();
        Guests guests = new Guests();
        guests.setLogin(loginText);
        guests.setPassword(loginPassword);
        ResultSet result =  dbHandler.getGuest(guests);

        int count = 0;
        while (true) {
            try {
                if (!result.next()) break;
            } catch (SQLException e) {
                e.printStackTrace();
            }
            count++;
        }
        if (count >= 1){
            Signupbutton.getScene().getWindow().hide();
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
            stage.showAndWait();
        }

        else{
            Shake guestLoginAnim = new Shake(LoginButton);
            Shake guestPassAnim = new Shake(PassButtton);
            guestLoginAnim.playAnim();
            guestPassAnim.playAnim();
        }
    }

}

