package home;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class TestLineNode extends ImageView {

    private Image image = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/Transistor_ON.png"));

    public TestLineNode()
    {
        this.setImage(image);
    }
}
