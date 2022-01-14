package home;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Point2D;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class GameEngineController {

    private HashMap<KeyCode, Boolean> keys = new HashMap<>();

    private boolean gameState = false;
    private Node currentlySelected = null;
    private boolean objectSelected = false;
    private boolean cannotUnselect = false;

    private ObservableList<String> input = FXCollections.observableArrayList("None", "Left Arrow", "Right Arrow", "Up Arrow", "Down Arrow", "A", "D", "W", "S");

    private Image buttonPlay = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/play.png"));
    private Image buttonStop = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/stop.png"));

    private ImageView viewPlay = new ImageView(buttonPlay);
    private ImageView viewStop = new ImageView(buttonStop);

    @FXML
    Button buttonLogout;

    private Stage stage;
    private Scene login;

    @FXML
    AnchorPane gameEngine;
    @FXML
    Pane gameWindow;
    @FXML
    VBox objectPropertiesPane;
    @FXML
    MenuItem menuItemExit;
    @FXML
    Button playButton;
    @FXML
    Label isObjectSelectedLabel;
    @FXML
    Button createPlayerButton;
    @FXML
    Button createWallButton;
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
    private ArrayList<Wall> platforms = new ArrayList<Wall>();
    private GameObject player;
    private Wall wall;
    private Point2D playerVelocity = new Point2D(0, 0);
    private boolean canJump = true;

    // List of game objects
    private List<Node> allObjects() {
        return gameWindow.getChildren().stream().map(n -> (Node)n).collect(Collectors.toList());
    }

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
    public void exitApplication() {
        Platform.exit();
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

        allObjects().forEach(s -> {
            s.setOnMouseEntered(e -> cannotUnselect = true);
            s.setOnMouseExited(e -> cannotUnselect = false);
        });

        // Testing new collision
        if (isPressed(getKeyJump(jumpInputChoiceBox.getValue())) && player.getTranslateY() >= jumpHeightSlider.getValue()) {
            jumpPlayer();
        }

        if (isPressed(getKeyLeft(player.getMoveLeft()))) {
            movePlayerX(-((int) player.getSpeed()));
        }

        if (isPressed(getKeyRight(rightInputChoiceBox.getValue()))) {
            movePlayerX((int) player.getSpeed());
        }

        if (playerVelocity.getY() < player.getGravity()) {
            playerVelocity = playerVelocity.add(0, player.getGravity());
        }

        movePlayerY((int)playerVelocity.getY());
    }

    private void movePlayerX(int value) {
        boolean movingRight = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform: platforms) {
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingRight) {
                        if (player.getTranslateX() + player.getWidth() == platform.getTranslateX()) {
                            return;
                        }
                    } else {
                        if (player.getTranslateX() == platform.getTranslateX() + wall.getWidth()) {
                            return;
                        }
                    }
                }
            }
            player.setTranslateX(player.getTranslateX() + (movingRight ? 1 : -1));
        }
    }

    private void movePlayerY(int value) {
        boolean movingDown = value > 0;

        for (int i = 0; i < Math.abs(value); i++) {
            for (Node platform : platforms) {
                if (player.getBoundsInParent().intersects(platform.getBoundsInParent())) {
                    if (movingDown) {
                        if (player.getTranslateY() + player.getWidth() == platform.getTranslateY()) {
                            player.setTranslateY(player.getTranslateY() - 1);
                            canJump = true;
                            return;
                        }
                    } else {
                        if (player.getTranslateY() == platform.getTranslateY() + wall.getWidth()) {
                            return;
                        }
                    }
                }
            }
            player.setTranslateY(player.getTranslateY() + (movingDown ? 1 : -1));
        }
    }

    private void jumpPlayer() {
        if (canJump) {
            playerVelocity = playerVelocity.add(0, -player.getJumpHeight());
            canJump = false;
        }
    }

    private AnimationTimer a = new AnimationTimer() {

        @Override
        public void handle(long currentNanoTime) {
            gameWindow.setOnKeyPressed(e -> keys.put(e.getCode(), true));
            gameWindow.setOnKeyReleased(e -> keys.put(e.getCode(), false));
            update();
        }
    };

    @FXML
    public void createPlayer() {
        player = new GameObject(200.0, 200.0, 100.0, 100.0, 0.0, 0.0, "None", "None", "None", "None", "None", 0.0, Color.BLUE);
        player.setFill(Color.GREEN);
        player.setStroke(Color.BLACK);
        draggable.makeDraggable(player);
        gameWindow.getChildren().add(player);

        player.setOnMouseEntered(e -> cannotUnselect = true);
        player.setOnMouseExited(e -> cannotUnselect = false);

        player.setOnMouseClicked(e -> {
            objectSelected = true;
            currentlySelected = player.getSelected();

            allObjects().forEach(s -> {
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
            getObjectProperties(currentlySelected);
            System.out.println("Current Object Selected: " + currentlySelected); // For testing
        });
    }

    @FXML
    public void createWall() {
        wall = new Wall(200.0, 200.0, 100.0, 100.0, Color.GREY);
        draggable.makeDraggable(wall);
        wall.setFill(Color.GREY);
        wall.setStroke(Color.BLACK);
        gameWindow.getChildren().add(wall);
        platforms.add(wall);

        wall.setOnMouseEntered(e -> cannotUnselect = true);
        wall.setOnMouseExited(e -> cannotUnselect = false);

        wall.setOnMouseClicked(e -> {
            objectSelected = true;
            currentlySelected = wall.getSelected();

            allObjects().forEach(s -> {
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
            getObjectProperties(currentlySelected);
            System.out.println("Current Object Selected: " + currentlySelected); // For testing
        });
    }

    // Show object properties fields
    @FXML
    private void updateObjectProperties() {

        if (!objectSelected) {
            isObjectSelectedLabel.setText("Please select an object");

            gravitySlider.setDisable(true);
            gravitySlider.setValue(0);
            speedSlider.setDisable(true);
            speedSlider.setValue(0);
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
            jumpHeightSlider.setValue(0);
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

            if (currentlySelected.getClass() == GameObject.class) {

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

            } else if (currentlySelected.getClass() == Wall.class) {

                gravitySlider.setDisable(true);
                gravitySlider.setValue(0);
                speedSlider.setDisable(true);
                speedSlider.setValue(0);
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
                jumpHeightSlider.setValue(0);
                solidCheckBox.setDisable(true);
                solidCheckBox.setSelected(false);
                widthSlider.setDisable(false);
                heightSlider.setDisable(false);
                colorPicker.setDisable(false);
                savePropertiesButton.setDisable(false);
            }

            System.out.println("objectSelected = " + objectSelected); // For testing
        }
    }

    // Get object properties
    @FXML
    private void getObjectProperties(Node currentlySelected) {
        if (currentlySelected.getClass() == GameObject.class) {

            double gravity = player.getGravity();
            gravitySlider.setValue(gravity);

            double speed = player.getSpeed();
            speedSlider.setValue(speed);

            String moveLeft = player.getMoveLeft();
            leftInputChoiceBox.setValue(moveLeft);

            String moveRight = player.getMoveRight();
            rightInputChoiceBox.setValue(moveRight);

            String moveUp = player.getMoveUp();
            upInputChoiceBox.setValue(moveUp);

            String moveDown = player.getMoveDown();
            downInputChoiceBox.setValue(moveDown);

            String jumpKey = player.getJump();
            jumpInputChoiceBox.setValue(jumpKey);

            double jumpHeight = player.getJumpHeight();
            jumpHeightSlider.setValue(jumpHeight);

            double width = player.getWidth();
            widthSlider.setValue(width / 100);

            double height = player.getHeight();
            heightSlider.setValue(height / 100);

            Color color = player.getColor();
            colorPicker.setValue(color);

        } else if (currentlySelected.getClass() == Wall.class) {

            double width = wall.getWidth();
            widthSlider.setValue(width / 100);

            double height = wall.getHeight();
            heightSlider.setValue(height / 100);

            Color color = wall.getColor();
            colorPicker.setValue(color);
        }
    }

    @FXML
    private void setObjectProperties(Node currentlySelected) {
        if (currentlySelected.getClass() == GameObject.class) {

            System.out.println("Saving for" + currentlySelected);
            player.setGravity(gravitySlider.getValue());
            player.setSpeed(speedSlider.getValue());
            player.setMoveLeft(leftInputChoiceBox.getValue());
            player.setMoveRight(rightInputChoiceBox.getValue());
            player.setMoveUp(upInputChoiceBox.getValue());
            player.setMoveDown(downInputChoiceBox.getValue());
            player.setJump(jumpInputChoiceBox.getValue());
            player.setJumpHeight(jumpHeightSlider.getValue());
            player.setWidth(widthSlider.getValue() * 100);
            player.setHeight(heightSlider.getValue() * 100);
            player.setColor(colorPicker.getValue());

        } else {

            if (currentlySelected.getClass() == Wall.class) {
                wall.setWidth(widthSlider.getValue() * 100);
                wall.setHeight(heightSlider.getValue() * 100);
                wall.setColor(colorPicker.getValue());
            }
        }
    }

    @FXML
    public void saveObjectProperties() {
        setObjectProperties(currentlySelected);
    }

    @FXML
    private KeyCode getKeyCode(String s) {
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

    private KeyCode getKeyLeft(String s) {
        KeyCode leftInput;
        leftInput = getKeyCode(s);
        return leftInput;
    }

    private KeyCode getKeyRight(String s) {
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

    private KeyCode getKeyJump(String s) {
        KeyCode jumpInput;
        jumpInput = getKeyCode(s);
        return jumpInput;
    }

    private boolean isPressed(KeyCode key) {
        return keys.getOrDefault(key, false);
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
