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
    private double x;
    private double y;
    private double width;
    private double height;
    private Color color;

    // Constructor
    GameObject(double x, double y, double width, double height, double gravity, double speed, String moveLeft, String moveRight, String moveUp, String moveDown, String jump, double jumpHeight, Color color) {

        super(x, y, height, width);

        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.gravity = gravity;
        this.speed = speed;
        this.moveLeft = moveLeft;
        this.moveRight = moveRight;
        this.moveUp = moveUp;
        this.moveDown = moveDown;
        this.jump = jump;
        this.jumpHeight = jumpHeight;
        this.color = color;
    }

    GameObject getSelected() {
        return this;
    }

    // Getters and setters

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

    public String getMoveLeft() {
        return moveLeft;
    }

    public void setMoveLeft(String newMoveLeft) {
        this.moveLeft = newMoveLeft;
    }

    public String getMoveRight() {
        return moveRight;
    }

    public void setMoveRight(String newMoveRight) {
        this.moveRight = newMoveRight;
    }

    public String getMoveUp() {
        return moveUp;
    }

    public void setMoveUp(String newMoveUp) {
        this.moveUp = newMoveUp;
    }

    public String getMoveDown() {
        return moveDown;
    }

    public void setMoveDown(String newMoveDown) {
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

    Color getColor() {
        return color;
    }

    void setColor(Color newColor) {
        this.color = newColor;
        this.setFill(color);
    }
}
