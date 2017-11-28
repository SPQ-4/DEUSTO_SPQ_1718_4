package models;
import controllers.Usuario;

import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.collections.ObservableList;
import javafx.collections.FXCollections;

public class Main2 extends Application {
	private ObservableList<Usuario> userData = FXCollections.observableArrayList();
	public Main2() {
		
	}
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	URL resource =this.getClass().getResource("../views/contestTable.fxml");
    	Parent root = FXMLLoader.load(resource);
      primaryStage.setTitle("Panenka - Admin");
      primaryStage.setScene(new Scene(root, 800, 475));
      primaryStage.show();
    }

    /**
     * Returns the data as an observable list of Persons. 
     * @return
     */
    public ObservableList<Usuario> getUserData() {
        return userData;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
