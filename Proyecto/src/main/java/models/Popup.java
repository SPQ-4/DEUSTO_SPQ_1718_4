package models;

import controllers.PopupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Popup extends Window {

    public Popup(String message) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/popup.fxml"));
        Parent root = loader.load();
        PopupController controller = loader.getController();
        controller.showMessage(message);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Panenka - Admin");
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

}
