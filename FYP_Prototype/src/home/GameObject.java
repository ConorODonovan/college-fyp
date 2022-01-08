package home;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class GameObject extends Rectangle {

    private double gravity;
    private double speed;
    private String moveLeft;
    private String moveRight;
    private String moveUp;
    private String moveDown;
    private String jump;
    private double jumpHeight;
    private boolean solid;
    private Color color;
    private String name;
    private boolean canMoveLeft;
    private boolean canMoveRight;
    private boolean canMoveUp;
    private boolean canMoveDown;

    // Constructor
    GameObject(double x, double y, double height, double width, double gravity, double speed, String moveLeft, String moveRight, String moveUp, String moveDown, String jump, double jumpHeight, boolean solid, Color color, String name) {

        super(x, y, height, width);

        this.gravity = gravity;
        this.speed = speed;
        this.moveLeft = moveLeft;
        this.moveRight = moveRight;
        this.moveUp = moveUp;
        this.moveDown = moveDown;
        this.jump = jump;
        this.jumpHeight = jumpHeight;
        this.solid = solid;
        this.color = color;
        this.name = name;
        this.canMoveLeft = true;
        this.canMoveRight = true;
        this.canMoveUp = true;
        this.canMoveDown = true;
    }

    GameObject getSelected() {
        return this;
    }

    // Getters and setters
    boolean getCanMoveLeft() {
        return canMoveLeft;
    }

    boolean getCanMoveRight() {
        return canMoveRight;
    }

    boolean getCanMoveUp() {
        return canMoveUp;
    }

    boolean getCanMoveDown() {
        return canMoveDown;
    }

    void setCanMoveLeft(boolean newMoveLeft) {
        canMoveLeft = newMoveLeft;
    }

    void setCanMoveRight(boolean newMoveRight) {
        canMoveRight = newMoveRight;
    }

    void setCanMoveUp(boolean newMoveUp) {
        canMoveUp = newMoveUp;
    }

    void setCanMoveDown(boolean newMoveDown) {
        canMoveLeft = newMoveDown;
    }

    double getGravity() {
        return gravity;
    }

    void setGravity(double newGravity) {
        this.gravity = newGravity;
    }

    double getSpeed() {
        return speed;
    }

    void setSpeed(double newSpeed) {
        this.speed = newSpeed;
    }

    String getMoveLeft() {
        return moveLeft;
    }

    void setMoveLeft(String newMoveLeft) {
        this.moveLeft = newMoveLeft;
    }

    String getMoveRight() {
        return moveRight;
    }

    void setMoveRight(String newMoveRight) {
        this.moveRight = newMoveRight;
    }

    String getMoveUp() {
        return moveUp;
    }

    void setMoveUp(String newMoveUp) {
        this.moveUp = newMoveUp;
    }

    String getMoveDown() {
        return moveDown;
    }

    void setMoveDown(String newMoveDown) {
        this.moveDown = newMoveDown;
    }

    public String getJump() {
        return jump;
    }

    void setJump(String newJump) {
        this.jump = newJump;
    }

    double getJumpHeight() {
        return jumpHeight;
    }

    public void setJumpHeight(double newJumpHeight) {
        this.jumpHeight = newJumpHeight;
    }

    boolean getSolid() {
        return solid;
    }

    void setSolid(boolean newSolid) {
        this.solid = newSolid;
    }

    Color getColor() {
        return color;
    }

    void setColor(Color newColor) {
        this.color = newColor;
        this.setFill(color);
    }

    // Movement
    void moveLeft(double speed) {
        setTranslateX(getTranslateX() - speed);
    }

    void moveRight(double speed) {
        setTranslateX(getTranslateX() + speed);
    }

    void moveUp(double speed) {
        setTranslateY(getTranslateY() - speed);
    }

    void moveDown(double speed) {
        setTranslateY(getTranslateY() + speed);
    }

    void fall() {
        setTranslateY(getTranslateY() + gravity);
    }

    // Test code - need to experiment with this
    public void jump() {
        setTranslateY(getTranslateY() - 5);
    }

    String getName() {
        return name;
    }
}
