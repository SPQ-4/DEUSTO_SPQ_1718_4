package models;

import controllers.Usuario;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

	private ObservableList<Usuario> userData = FXCollections.observableArrayList();
	public Main() {
		//System.out.println("HOLA");
//		userData.add(new Usuario("Asier",23));
//		userData.add(new Usuario("Paula",23));
//		userData.add(new Usuario("Juan",22));
	}
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	Parent root = FXMLLoader.load(getClass().getResource("../views/login.fxml"));
        primaryStage.setTitle("Panenka - Admin");
        primaryStage.setScene(new Scene(root));
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
