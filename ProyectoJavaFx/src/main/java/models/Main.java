package models;
import controllers.Controller;

import java.io.InputStream;
import java.net.URL;
import controllers.ControllerUser;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;


public class Main extends Application {
	private ObservableList<Usuario> userData = FXCollections.observableArrayList();
	public Main() {
		//System.out.println("HOLA");
		userData.add(new Usuario("Asier",23));
		userData.add(new Usuario("Paula",23));
		userData.add(new Usuario("Juan",22));
	}
	
    @Override

    public void start(Stage primaryStage) throws Exception{

    	//InputStream resource=getClass().getResourceAsStream("../../resources/views/sample.fxml");
    	//URL resource =this.getClass().getResource("../views/sample.fxml");
    	Usuario a=new Usuario("A",1);
    	//System.out.println(resource);
    	//Parent rooti = FXMLLoader.load(resource);
    	//Button btn = new Button("Sign in");
    	//GridPane grid=new GridPane();
    	/*grid.add(btn, 5, 5);
    	btn.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	        Controller.button1();
    	    }
    	});*/	
        primaryStage.setTitle("Hello World");
     //   primaryStage.setScene(new Scene(rooti, 800, 475));

        //Parent root = FXMLLoader.load(getClass().getResource("../views/User_Table.fxml"));

        Parent root = FXMLLoader.load(getClass().getResource("../views/Market_Players.fxml"));

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 500, 500));

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
