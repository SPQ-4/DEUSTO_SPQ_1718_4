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
		//System.out.println("HOLA");
		userData.add(new Usuario("Asier", 23));
		userData.add(new Usuario("Paula", 23));
		userData.add(new Usuario("Juan", 22));
	}
	
    @Override
    public void start(Stage primaryStage) throws Exception {
    	URL resource =this.getClass().getResource("../views/barchart.fxml");
    	Parent root = FXMLLoader.load(resource);
        primaryStage.setTitle("HomePage");
        primaryStage.setScene(new Scene(root, 1000, 700));
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
