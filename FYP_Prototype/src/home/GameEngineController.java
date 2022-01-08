package home;

import javafx.animation.AnimationTimer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GameEngineController {

    private HashMap<KeyCode, Boolean> keys = new HashMap<KeyCode, Boolean>();

    private boolean gameState = false;
    private GameObject currentlySelected = null;
    private boolean objectSelected = false;
    private boolean cannotUnselect = false;

    private ObservableList<String> input = FXCollections.observableArrayList("None", "Left Arrow", "Right Arrow", "Up Arrow", "Down Arrow", "A", "D", "W", "S");

    private Image buttonPlay = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/play.png"));
    private Image buttonStop = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/stop.png"));

    private ImageView viewPlay = new ImageView(buttonPlay);
    private ImageView viewStop = new ImageView(buttonStop);

    @FXML
    AnchorPane gameEngine;
    @FXML
    Pane gameWindow;
    @FXML
    Button playButton;
    @FXML
    Label isObjectSelectedLabel;

    // Object property controls
    @FXML
    Slider gravitySlider;
    @FXML
    Slider speedSlider;
    @FXML
    ChoiceBox<String> leftInputChoiceBox;
    @FXML
    ChoiceBox<String> rightInputChoiceBox;
    @FXML
    ChoiceBox<String> upInputChoiceBox;
    @FXML
    ChoiceBox<String> downInputChoiceBox;
    @FXML
    ChoiceBox<String> jumpInputChoiceBox;
    @FXML
    Slider jumpHeightSlider;
    @FXML
    CheckBox solidCheckBox;
    @FXML
    ColorPicker colorPicker;
    @FXML
    Slider widthSlider;
    @FXML
    Slider heightSlider;
    @FXML
    Button savePropertiesButton;

    private Draggable draggable = new Draggable();

    @FXML
    VBox objectPropertiesPane;

    public GameEngineController() {
    }

    @FXML
    private void initialize() {

        System.out.println("This is initialized!");
        playButton.setGraphic(viewPlay);
        playButton.setText("PLAY!");
        leftInputChoiceBox.setItems(input);
        rightInputChoiceBox.setItems(input);
        upInputChoiceBox.setItems(input);
        downInputChoiceBox.setItems(input);
        jumpInputChoiceBox.setItems(input);
        unselectObject();
    }

    // List of game objects
    private List<GameObject> gameObjects() {

        return gameWindow.getChildren().stream().map(n -> (GameObject)n).collect(Collectors.toList());
    }

    @FXML
    public void changeGameState() {

        if (gameState) {
            gameState = false;
            playButton.setGraphic(viewPlay);
            playButton.setText("PLAY!");
            a.stop();
        } else {
            gameState = true;
            playButton.setGraphic(viewStop);
            playButton.setText("STOP");
            a.start();
        }

        System.out.println("Game State: " + gameState);
    }

    @FXML
    public void unselectObject() {

        gameWindow.setPickOnBounds(false);

        if (!cannotUnselect) {
            objectSelected = false;
            updateObjectProperties();
            System.out.println("Object selected: " + objectSelected);
        }
    }

    private void update() {

        gameWindow.requestFocus();

        // Movement methods for game objects
        gameObjects().forEach(s -> {

            s.setOnMouseEntered(e -> cannotUnselect = true);

            s.setOnMouseExited(e -> cannotUnselect = false);

            collisionCheck();

            if (isPressed(getKeyLeft(s.getMoveLeft()))) {
                if (s.getCanMoveLeft()) {
                    s.moveLeft(s.getSpeed());
                }
                else {
                    s.moveLeft(0);
                }
            }

            if (isPressed(getKeyRight(s.getMoveRight()))) {
                if (s.getCanMoveRight()) {
                    s.moveRight(s.getSpeed());
                }
                else {
                    s.moveRight(0);
                }
            }

            if (isPressed(getKeyUp(s.getMoveUp()))) {
                if (s.getCanMoveUp()) {
                    s.moveUp(s.getSpeed());
                }
                else {
                    s.moveUp(0);
                }
            }

            if (isPressed(getKeyDown(s.getMoveDown()))) {
                if (s.getCanMoveDown()) {
                    s.moveDown(s.getSpeed());
                }
                else {
                    s.moveDown(0);
                }
            }

            // Jump testing - needs work
            if (isPressed(getKeyJump(s.getJump()))) {
                s.jump();
            }

            s.fall();
        });
    }

    private AnimationTimer a = new AnimationTimer() {

        @Override
        public void handle(long currentNanoTime) {
            gameWindow.setOnKeyPressed(e -> keys.put(e.getCode(), true));
            gameWindow.setOnKeyReleased(e -> keys.put(e.getCode(), false));
            update();
        }
    };

    // This kind of works, but is extremely buggy
    private void collisionCheck() {
        gameObjects().forEach(s -> {

            gameObjects().forEach(t -> {
                // Collision checking
                if (s.getName().equals("Obj1") && t.getName().equals("Obj2") && s.getBoundsInParent().intersects(t.getBoundsInParent())) {

                    System.out.println("Collision!");

                    // Left
                    if (isPressed(getKeyLeft(s.getMoveLeft()))) {
                        if (s.getTranslateX() <= t.getTranslateX() + t.getWidth()) {
                            s.setCanMoveLeft(false);
                            s.setTranslateX(t.getTranslateX() + t.getWidth() + s.getSpeed());
                        } else {
                            s.setCanMoveLeft(true);
                        }
                    }

                    // Right
                    if (isPressed(getKeyRight(s.getMoveRight()))) {
                        if (s.getTranslateX() + s.getWidth() >= t.getTranslateX()) {
                            s.setCanMoveRight(false);
                            s.setTranslateX(s.getTranslateX() - s.getSpeed());
                        } else {
                            s.setCanMoveRight(true);
                        }
                    }

                    // Up
                    if (isPressed(getKeyUp(s.getMoveUp()))) {
                        if (s.getTranslateY() <= t.getTranslateY() + t.getHeight()) {
                            s.setCanMoveUp(false);
                            s.setTranslateY(s.getTranslateY() + s.getSpeed());
                        } else {
                            s.setCanMoveUp(true);
                        }
                    }

                    // Down
                    if (isPressed(getKeyDown(s.getMoveDown()))) {
                        if (s.getTranslateY() + s.getHeight() >= t.getTranslateY()) {
                            s.setCanMoveDown(false);
                            s.setTranslateY(s.getTranslateY() - s.getSpeed());
                        } else {
                            s.setCanMoveDown(true);
                        }
                    }
                } else {
                    s.setCanMoveLeft(true);
                    s.setCanMoveRight(true);
                    s.setCanMoveUp(true);
                    s.setCanMoveDown(true);
                }
            });
        });
    }

    public void createTestShape() throws FileNotFoundException {

        GameObject testObject = new GameObject(200.0, 200.0, 100.0, 100.0, 0.0, 0.0, "None", "None", "None", "None", "None", 0.0, false, Color.RED, "Obj1");
        draggable.makeDraggable(testObject);
        testObject.setFill(colorPicker.getValue());
        testObject.setStroke(Color.BLACK);
        gameWindow.getChildren().add(testObject);

        testObject.setOnMouseEntered(e -> cannotUnselect = true);

        testObject.setOnMouseExited(e -> cannotUnselect = false);

        testObject.setOnMouseClicked(e -> {
            objectSelected = true;
            currentlySelected = testObject.getSelected(); // Click on game object to select it

            gameObjects().forEach(s -> {
                if (s == currentlySelected) {
                    DropShadow borderGlow = new DropShadow();
                    borderGlow.setOffsetY(0f);
                    borderGlow.setOffsetX(0f);
                    borderGlow.setColor(Color.RED);
                    borderGlow.setWidth(60);
                    borderGlow.setHeight(60);
                    s.setEffect(borderGlow);
                }
                else {
                    s.setEffect(null);
                }
            });

            DropShadow borderGlow = new DropShadow();
            borderGlow.setOffsetY(0f);
            borderGlow.setOffsetX(0f);
            borderGlow.setColor(Color.RED);
            borderGlow.setWidth(60);
            borderGlow.setHeight(60);
            currentlySelected.setEffect(borderGlow);

            updateObjectProperties();
            getObjectProperties(testObject);
            System.out.println("Current Object Selected: " + currentlySelected); // For testing
        });
    }

    public void createTestShape2() throws FileNotFoundException {

        GameObject testObject2 = new GameObject(200.0, 200.0, 100.0, 100.0, 0.0, 0.0, "None", "None", "None", "None", "None", 0.0, false, Color.GREEN, "Obj2");
        draggable.makeDraggable(testObject2);
        testObject2.setFill(Color.GREEN);
        testObject2.setStroke(Color.BLACK);
        gameWindow.getChildren().add(testObject2);

        testObject2.setOnMouseEntered(e -> cannotUnselect = true);

        testObject2.setOnMouseExited(e -> cannotUnselect = false);

        testObject2.setOnMouseClicked(e -> {
            objectSelected = true;
            currentlySelected = testObject2.getSelected(); // Click on game object to select it

            gameObjects().forEach(s -> {
                if (s == currentlySelected) {
                    DropShadow borderGlow = new DropShadow();
                    borderGlow.setOffsetY(0f);
                    borderGlow.setOffsetX(0f);
                    borderGlow.setColor(Color.RED);
                    borderGlow.setWidth(60);
                    borderGlow.setHeight(60);
                    s.setEffect(borderGlow);
                }
                else {
                    s.setEffect(null);
                }
            });

            updateObjectProperties();
            getObjectProperties(testObject2);
            System.out.println("Current Object Selected: " + currentlySelected + currentlySelected.getName()); // For testing
        });
    }

    // Show object properties fields
    @FXML
    private void updateObjectProperties() {

        if (!objectSelected) {
            isObjectSelectedLabel.setText("Please select an object");

            gravitySlider.setDisable(true);
            gravitySlider.setValue(0.0);
            speedSlider.setDisable(true);
            speedSlider.setValue(0.0);
            leftInputChoiceBox.setDisable(true);
            leftInputChoiceBox.setValue("None");
            rightInputChoiceBox.setDisable(true);
            rightInputChoiceBox.setValue("None");
            upInputChoiceBox.setDisable(true);
            upInputChoiceBox.setValue("None");
            downInputChoiceBox.setDisable(true);
            downInputChoiceBox.setValue("None");
            jumpInputChoiceBox.setDisable(true);
            jumpInputChoiceBox.setValue("None");
            jumpHeightSlider.setDisable(true);
            jumpHeightSlider.setValue(0.0);
            solidCheckBox.setDisable(true);
            solidCheckBox.setSelected(false);
            widthSlider.setDisable(true);
            heightSlider.setDisable(true);
            colorPicker.setDisable(true);
            colorPicker.setValue(Color.WHITE);
            savePropertiesButton.setDisable(true);

            System.out.println("objectSelected = " + objectSelected); // For testing
        } else {
            isObjectSelectedLabel.setText("Object selected!");

            gravitySlider.setDisable(false);
            speedSlider.setDisable(false);
            leftInputChoiceBox.setDisable(false);
            rightInputChoiceBox.setDisable(false);
            upInputChoiceBox.setDisable(false);
            downInputChoiceBox.setDisable(false);
            jumpInputChoiceBox.setDisable(false);
            jumpHeightSlider.setDisable(false);
            solidCheckBox.setDisable(false);
            widthSlider.setDisable(false);
            heightSlider.setDisable(false);
            colorPicker.setDisable(false);
            savePropertiesButton.setDisable(false);

            System.out.println("objectSelected = " + objectSelected); // For testing
        }
    }

    // Get object properties
    @FXML
    public void getObjectProperties(GameObject gameObject) {

        // Gravity Slider
        double gravity = gameObject.getGravity();
        gravitySlider.setValue(gravity);

        // Speed Slider
        double speed = gameObject.getSpeed();
        speedSlider.setValue(speed);

        // Move Left ChoiceBox
        String leftInput = gameObject.getMoveLeft();
        leftInputChoiceBox.setValue(leftInput);

        // Move Right ChoiceBox
        String rightInput = gameObject.getMoveRight();
        rightInputChoiceBox.setValue(rightInput);

        // Move Up ChoiceBox
        String upInput = gameObject.getMoveUp();
        upInputChoiceBox.setValue(upInput);

        // Move Down ChoiceBox
        String downInput = gameObject.getMoveDown();
        downInputChoiceBox.setValue(downInput);

        // Jump ChoiceBox
        String jumpInput = gameObject.getJump();
        jumpInputChoiceBox.setValue(jumpInput);

        // Jump Height Slider
        double jumpHeight = gameObject.getJumpHeight();
        jumpHeightSlider.setValue(jumpHeight);

        // Solid CheckBox
        boolean isSolid = gameObject.getSolid();
        solidCheckBox.setSelected(isSolid);

        // Object width
        double objectWidth = gameObject.getWidth();
        widthSlider.setValue(objectWidth/100);

        // Object height
        double objectHeight = gameObject.getHeight();
        heightSlider.setValue(objectHeight/100);

        // Colour Picker
        Color color = gameObject.getColor();
        colorPicker.setValue(color);
    }

    @FXML
    public void setObjectProperties(GameObject gameObject) {

        if (currentlySelected.getClass() == GameObject.class) {
            System.out.println("Saving for" + gameObject);
            gameObject.setGravity(gravitySlider.getValue());
            gameObject.setSpeed(speedSlider.getValue());
            gameObject.setMoveLeft(leftInputChoiceBox.getValue());
            gameObject.setMoveRight(rightInputChoiceBox.getValue());
            gameObject.setMoveUp(upInputChoiceBox.getValue());
            gameObject.setMoveDown(downInputChoiceBox.getValue());
            gameObject.setJump(jumpInputChoiceBox.getValue());
            gameObject.setSolid(solidCheckBox.isSelected());
            gameObject.setWidth(widthSlider.getValue() * 100);
            gameObject.setHeight(heightSlider.getValue() * 100);
            gameObject.setColor(colorPicker.getValue());
        }
    }

    @FXML
    public void saveObjectProperties() {
        setObjectProperties(currentlySelected);
    }

    @FXML
    public KeyCode getKeyCode(String s) {
        KeyCode key = null;

        switch(s) {
            case "None":
                key = null;
                break;
            case "Left Arrow":
                key = KeyCode.LEFT;
                break;
            case "Right Arrow":
                key = KeyCode.RIGHT;
                break;
            case "Up Arrow":
                key = KeyCode.UP;
                break;
            case "Down Arrow":
                key = KeyCode.DOWN;
                break;
            case "A":
                key = KeyCode.A;
                break;
            case "D":
                key = KeyCode.D;
                break;
            case "W":
                key = KeyCode.W;
                break;
            case "S":
                key = KeyCode.S;
                break;
        }
        return key;
    }

    public KeyCode getKeyLeft(String s) {
        KeyCode leftInput;
        leftInput = getKeyCode(s);
        return leftInput;
    }

    public KeyCode getKeyRight(String s) {
        KeyCode rightInput;
        rightInput = getKeyCode(s);
        return rightInput;
    }

    public KeyCode getKeyUp(String s) {
        KeyCode upInput;
        upInput = getKeyCode(s);
        return upInput;
    }

    public KeyCode getKeyDown(String s) {
        KeyCode downInput;
        downInput = getKeyCode(s);
        return downInput;
    }

    public KeyCode getKeyJump(String s) {
        KeyCode jumpInput;
        jumpInput = getKeyCode(s);
        return jumpInput;
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
    }
}
