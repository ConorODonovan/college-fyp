package home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class SceneController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private LoginController loginController;

    @FXML
    public void switchToLogin(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/home/Login.fxml"));
        stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }

    @FXML
    public void switchToHomepage(ActionEvent event) throws IOException
    {
        if (loginController.loginCheck())
        {
            Parent root = FXMLLoader.load(getClass().getResource("/home/Homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        }
    }

    @FXML
    public void switchToTransistorExercise(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/home/TransistorExercise.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root);
        stage.setScene(scene);
        stage.show();
    }
}
