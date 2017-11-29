package models;

import controllers.PopupController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;

public class Popup extends Window {
/**
 * Una clase que nos permite lanzar una ventana de alerta
 * @param message le pasamos el mensaje que queremos que aparezca
 * @throws IOException
 */
    public Popup(String message) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../views/popup.fxml"));
        Parent root = loader.load();
        PopupController controller = loader.getController();
        controller.showMessage(message);
        Stage primaryStage = new Stage();
        primaryStage.setTitle("Panenka - Admin");
        primaryStage.setScene(new Scene(root, 700, 150));
        primaryStage.show();
    }

}
