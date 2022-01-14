package home;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Wall extends Rectangle {

    private Color color;

    Wall(double x, double y, double height, double width, Color color) {

        super(x, y, height, width);

        this.color = color;
    }

    public Wall getSelected() {
        return this;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color newColor) {
        this.color = newColor;
        this.setFill(newColor);
    }
}
