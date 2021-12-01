package home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class TransistorExerciseController {

    @FXML
    Pane transistormainpane;

    @FXML
    Button transistorOnButton;

    @FXML
    Button transistorOffButton;

    @FXML
    Button buttonAND;
    @FXML
    Button buttonNOT;
    @FXML
    Button buttonOR;
    @FXML
    Button buttonXOR;
    @FXML
    Button buttonNAND;
    @FXML
    Button buttonNOR;
    @FXML
    Button buttonXNOR;

    public TransistorExerciseController()
    {

    }

    @FXML
    public void createTransistorOn()
    {
        Transistor transistor = new Transistor(true);

        transistormainpane.getChildren().add(transistor);

        transistor.setPickOnBounds(true);

        transistor.setOnMouseClicked(e -> {
            transistor.changeState();
            System.out.println("Clicked the ON image!");
        });
    }

    @FXML
    public void createTransistorOff()
    {
        Transistor transistor = new Transistor(false);

        transistormainpane.getChildren().add(transistor);

        transistor.setPickOnBounds(true);

        transistor.setOnMouseClicked(e -> {
            transistor.changeState();
            System.out.println("Clicked the OFF image!");
        });
    }
}
