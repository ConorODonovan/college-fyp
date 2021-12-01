package home;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{

        Parent rootLogin = FXMLLoader.load(getClass().getResource("/home/Login.fxml"));
//    Parent rootTransistorExercise = FXMLLoader.load(getClass().getResource("Main.Login.fxml"));
//    Parent rootGameEngine = FXMLLoader.load(getClass().getResource("Main.Login.fxml"));


//
//    Scene transistorExercise = new Scene(rootTransistorExercise, 1280, 720);
//    Scene gameEngine = new Scene(rootGameEngine, 1280, 720);

        primaryStage.setTitle("FYP - Prototype");
        Scene login = new Scene(rootLogin, 1280, 720);

        primaryStage.setScene(login);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}