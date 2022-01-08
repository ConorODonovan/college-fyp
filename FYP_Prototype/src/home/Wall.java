package home;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Rectangle {

    private double height;
    private double width;
    private double x;
    private double y;
    private Color color;

    public Wall(double x, double y, double height, double width, Color color) {

        super(x, y, height, width);
        this.color = color;
    }
}