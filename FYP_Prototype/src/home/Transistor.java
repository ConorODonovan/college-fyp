package home;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;

import java.io.File;

public class Transistor extends ImageView {

    private boolean transistorState;
    private Image transistorOn = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/Transistor_ON.png"));
    private Image transistorOff = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/Transistor_OFF.png"));

    public Transistor(boolean state)
    {
        this.transistorState = state;

        if (state)
        {
//            this.transistorOn = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/Transistor_ON.png"));
            this.setImage(transistorOn);
        }
        else
        {
//            this.transistorOff = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/Transistor_OFF.png"));
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
