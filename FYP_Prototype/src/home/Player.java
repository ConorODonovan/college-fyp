package home;

import javafx.geometry.Point2D;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Player extends Rectangle {

    private Point2D playerSpeed = new Point2D(0, 0);
    private boolean canJump = true;

    private double x;
    private double y;
    private double width;
    private double height;
    private double gravity;
    private double speed;
    private String moveLeft;
    private String moveRight;
    private String moveUp;
    private String moveDown;
    private String jump;
    private double jumpHeight;
    private Color color;

    // Constructor
    Player(double x, double y, double width, double height, double gravity, double speed, String moveLeft, String moveRight, String moveUp, String moveDown, String jump, double jumpHeight, Color color) {

        super(x, y, width, height);
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

    Player getSelected() {
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

    Color getColor() {
        return color;
    }

    void setColor(Color newColor) {
        this.color = newColor;
    }
}
