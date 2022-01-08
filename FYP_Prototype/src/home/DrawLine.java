package home;
import javafx.scene.Node;
import javafx.scene.shape.Line;

public class DrawLine {

    private double startX;
    private double startY;

    private double endX;
    private double endY;

    private double currentX;
    private double currentY;

    private Line line;

    public double getStartX()
    {
        return startX;
    }

    public double getStartY()
    {
        return startY;
    }
    public double getEndX()
    {
        return endX;
    }

    public double getEndY()
    {
        return endY;
    }

    public Line drawLine(Node node)
    {
        node.setOnMousePressed(mouseEvent -> {
            startX = mouseEvent.getSceneX();
            startY = mouseEvent.getSceneY();
        });

        node.setOnMouseDragged(mouseEvent -> {
//            Line line = new Line(10.0f, 10.0f, 200.0f, 140.0f);
//
//            line.setStartX(startX);
//            line.setStartX(startY);
//
//            currentX = mouseEvent.getSceneX() - startX;
//            currentY = mouseEvent.getSceneY() - startY;
        });

        node.setOnMouseReleased(mouseEvent -> {
            endX = mouseEvent.getSceneX();
            endY = mouseEvent.getSceneY();
        });

        line = new Line(startX, startY, endX, endY);

        return line;
    }
}
