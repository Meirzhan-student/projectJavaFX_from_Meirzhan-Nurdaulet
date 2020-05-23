package sample;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/// Ерлан агай Здравствуйте! Этот код работает но к сожелению мы не смогли соеденить с главный мэйном
public class Chat extends Application {
    private boolean isServer = false;

    private TextArea message = new TextArea();
    private Service service = isServer ? createServer() : createClient();

    private Parent createContent(){
        message.setPrefHeight(600);
        TextField input = new TextField();
        input.setOnAction(event -> {
            String messages = isServer ? "Administration: " : "Guest: ";
            messages += input.getText();
            input.clear();

            message.appendText(messages + "\n");

            try {
                service.send(messages);
            } catch (Exception e) {
                message.appendText("Failed \n");
            }
        });

        VBox root = new VBox(20, message , input);
        root.setPrefSize(600, 600);
        return root;
    }



    @Override
    public void init() throws Exception{
        service.startConn();
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        primaryStage.setScene(new Scene(createContent()));
        primaryStage.show();
    }

    @Override
    public void stop() throws Exception{
        service.closeConn();
    }

    private Server createServer(){
        return new Server(55555, data ->{
            Platform.runLater(() -> {
               message.appendText(data.toString() + "\n");
            });
        });
    }


    private Client createClient(){

        return new Client("127.0.0.1", 55555, data -> {
            Platform.runLater(() -> {
              message.appendText(data.toString() + "\n" );
            });
        });
    }


    public static void main(String[] args){
        launch(args);
    }
}
