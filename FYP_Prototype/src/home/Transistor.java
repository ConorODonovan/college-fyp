package home;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Transistor extends ImageView {

    private boolean transistorState;
    private Image transistorOn = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/Transistor_ON.png"));
    private Image transistorOff = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/Transistor_OFF.png"));

    public Transistor(boolean state)
    {
        this.transistorState = state;

        this.setFitHeight(100);
        this.setFitWidth(100);

        if (state)
        {
            this.setImage(transistorOn);
        }
        else
        {
            this.setImage(transistorOff);
        }
    }

    public void changeState()
    {
        if (this.transistorState)
        {
            this.transistorState = false;
            this.setImage(transistorOff);
        }
        else
            {
            this.transistorState = true;
            this.setImage(transistorOn);
        }
    }
}
