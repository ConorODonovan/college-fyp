package home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class HomepageController {

    private Stage stage;
    private Scene scene;

    @FXML
    private Button transistorExercise;

    @FXML
    public void switchToTransistorExercise(ActionEvent event) throws IOException
    {
        Parent root = FXMLLoader.load(getClass().getResource("/home/TransistorExercise.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        scene = new Scene(root, 1600, 900);
        stage.setScene(scene);
        stage.show();
    }
}
