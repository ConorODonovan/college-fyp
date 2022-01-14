package home;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {

    private Stage stage;
    private Scene sceneTransistor;
    private Scene gameEngine;
    private Scene login;

    @FXML
    private Button transistorExercise;
    @FXML
    Button buttonLogout;
    @FXML
    MenuItem menuItemExit;

    @FXML
    public void exitApplication() {
        Platform.exit();
    }

    @FXML
    public void switchToTransistorExercise(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/home/TransistorExercise.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        sceneTransistor = new Scene(root, 1600, 900);
        stage.setScene(sceneTransistor);
        stage.show();
    }

    @FXML
    public void switchToGameEngine(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/GameEngine.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        gameEngine = new Scene(root, 1600, 900);
        stage.setScene(gameEngine);
        stage.show();
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        login = new Scene(root, 1600, 900);
        stage.setScene(login);
        stage.show();
    }
}
