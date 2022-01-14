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
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class TransistorExerciseController {

    private Stage stage;
    private Scene login;

    @FXML
    Pane transistormainpane;

    @FXML
    Button buttonLogout;

    @FXML
    MenuItem menuItemExit;

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

    Draggable draggable = new Draggable();
    DrawLine drawLine = new DrawLine();

    public TransistorExerciseController()
    {

    }

    @FXML
    public void exitApplication() {
        Platform.exit();
    }

    @FXML
    public void createTransistorOn()
    {
        Transistor transistor = new Transistor(true);

        transistormainpane.getChildren().add(transistor);

        transistor.setPickOnBounds(true);

        draggable.makeDraggable(transistor);

        transistor.setOnMouseClicked(e -> {
            transistor.changeState();
//            System.out.println("Clicked the ON image!");
        });
    }

    @FXML
    public void createTransistorOff()
    {
        Transistor transistor = new Transistor(false);

        transistormainpane.getChildren().add(transistor);

        transistor.setPickOnBounds(true);

        draggable.makeDraggable(transistor);

        transistor.setOnMouseClicked(e -> {
            transistor.changeState();
//            System.out.println("Clicked the OFF image!");
        });
    }

    @FXML
    public void createLogicGateAND()
    {
        LogicGate logicGate = new LogicGate(1);
        transistormainpane.getChildren().add(logicGate);
        logicGate.setPickOnBounds(true);
        draggable.makeDraggable(logicGate);
    }

    @FXML
    public void createLogicGateNAND()
    {
        LogicGate logicGate = new LogicGate(2);
        transistormainpane.getChildren().add(logicGate);
        logicGate.setPickOnBounds(true);
        draggable.makeDraggable(logicGate);
    }

    @FXML
    public void createLogicGateNOR()
    {
        LogicGate logicGate = new LogicGate(3);
        transistormainpane.getChildren().add(logicGate);
        logicGate.setPickOnBounds(true);
        draggable.makeDraggable(logicGate);
    }

    @FXML
    public void createLogicGateNOT()
    {
        LogicGate logicGate = new LogicGate(4);
        transistormainpane.getChildren().add(logicGate);
        logicGate.setPickOnBounds(true);
        draggable.makeDraggable(logicGate);
    }

    @FXML
    public void createLogicGateOR()
    {
        LogicGate logicGate = new LogicGate(5);
        transistormainpane.getChildren().add(logicGate);
        logicGate.setPickOnBounds(true);
        draggable.makeDraggable(logicGate);
    }

    @FXML
    public void createLogicGateXNOR()
    {
        LogicGate logicGate = new LogicGate(6);
        transistormainpane.getChildren().add(logicGate);
        logicGate.setPickOnBounds(true);
        draggable.makeDraggable(logicGate);
    }

    @FXML
    public void createLogicGateXOR()
    {
        LogicGate logicGate = new LogicGate(7);
        transistormainpane.getChildren().add(logicGate);
        logicGate.setPickOnBounds(true);
        draggable.makeDraggable(logicGate);
    }

    @FXML
    public void logout(ActionEvent event) throws IOException {
        Parent root = FXMLLoader.load(getClass().getResource("/home/Login.fxml"));
        stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        login = new Scene(root, 1600, 900);
        stage.setScene(login);
        stage.show();
    }

    // FOR TESTING - Drawing lines
    public void createLineMaker()
    {
        TestLineNode testLineNode = new TestLineNode();
        transistormainpane.getChildren().add(testLineNode);
        testLineNode.setPickOnBounds(true);
        drawLine.drawLine(testLineNode);
    }
}
