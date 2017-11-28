package models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;

import controllers.UserTableController;

public class UserTableView extends Application {
   private Usuario user;
    /*public UserTableView(Usuario user) {
	super();
	this.user = user;
}*/

	@Override
    public void start(Stage primaryStage) throws Exception {
		String str = "04/11/2017";
		Date fecha =new SimpleDateFormat("dd/mm/yyyy").parse(str);	
		user = new Usuario("juan@hotmail.com", 20, 500, fecha);
		
       primaryStage.setTitle("Datos Usuario");
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/userTable.fxml"));   
       Pane myPane = (Pane)fxmlLoader.load();
       UserTableController userTableController = fxmlLoader.getController();
       userTableController.selectUser(user);
       Scene myScene = new Scene(myPane);
       primaryStage.setScene(myScene);
       primaryStage.show();
       
       /*Parent root = FXMLLoader.load(getClass().getResource("../views/userTable.fxml"));
       primaryStage.setTitle("Hello World");
       primaryStage.setScene(new Scene(root, 500, 500));
       primaryStage.show();*/
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
