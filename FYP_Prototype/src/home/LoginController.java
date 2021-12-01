package home;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.io.IOException;

public class LoginController {

    private Stage stage;
    private Scene scene;
    private Parent root;
    private LoginController loginController;

    @FXML
    TextField loginUsernameField;

    @FXML
    TextField loginPasswordField;

    @FXML
    Button loginButton;

    SceneController homepage = new SceneController();

    @FXML
    public void switchToHomepage(ActionEvent event) throws IOException
    {
        if (loginCheck())
        {
            Parent root = FXMLLoader.load(getClass().getResource("/home/Homepage.fxml"));
            stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            scene = new Scene(root, 1280, 720);
            stage.setScene(scene);
            stage.show();
        }
    }

    public boolean loginCheck()
    {
        return loginUsernameField.getText().equals("test") && loginPasswordField.getText().equals("test");
    }



//    @FXML
//    public void login() throws IOException
//    {
//        if (loginUsernameField.getText().equals("test") && loginPasswordField.getText().equals("test"))
//        {
//            System.out.println("Login Successful");
//            SceneController loginSuccessful = new SceneController();
//            loginSuccessful.switchToHomepage();
//        }
//        else
//        {
//            System.out.println("Login Failed");
//        }
//    }
}
