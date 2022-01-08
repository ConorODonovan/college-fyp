package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent rootLogin = FXMLLoader.load(getClass().getResource("/home/Login.fxml"));
        primaryStage.setTitle("FYP - Prototype");
        Scene login = new Scene(rootLogin, 1600, 900);
        primaryStage.setScene(login);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}