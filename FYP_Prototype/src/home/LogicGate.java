package home;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class LogicGate extends ImageView {

    private int logicGateType;
    private Image type1 = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/AND.png"));
    private Image type2 = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/NAND.png"));
    private Image type3 = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/NOR.png"));
    private Image type4 = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/NOT.png"));
    private Image type5 = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/OR.png"));
    private Image type6 = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/XNOR.png"));
    private Image type7 = new Image(getClass().getResourceAsStream("/home/images/logicgateicons/XOR.png"));


    public LogicGate(int type)
    {
        this.logicGateType = type;
        this.setFitHeight(100);
        this.setFitWidth(100);

        switch (logicGateType)
        {
            case 1:
                this.logicGateType = 1;
                this.setImage(type1);
                break;
            case 2:
                this.logicGateType = 2;
                this.setImage(type2);
                break;
            case 3:
                this.logicGateType = 3;
                this.setImage(type3);
                break;
            case 4:
                this.logicGateType = 4;
                this.setImage(type4);
                break;
            case 5:
                this.logicGateType = 5;
                this.setImage(type5);
                break;
            case 6:
                this.logicGateType = 6;
                this.setImage(type6);
                break;
            case 7:
                this.logicGateType = 7;
                this.setImage(type7);
                break;
        }
    }
}
