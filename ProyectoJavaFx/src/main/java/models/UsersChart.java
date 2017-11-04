package models;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import controllers.UserTableController;
import controllers.Usuario;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import controllers.UsersChartController;

public class UsersChart extends Application{

	 public void start(Stage primaryStage) throws Exception {
		 
		 ArrayList<Integer> datos = new ArrayList();
		 int suma=0;
		 for(int i=0;i<30;i++) {
			 datos.add(i+3);
			 suma=suma + i +3;
		 }
		 
		double media = suma/datos.size();
	       primaryStage.setTitle("Datos Usuario");
	       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/monthUsersChart.fxml"));   
	       Pane myPane = (Pane)fxmlLoader.load();
	       UsersChartController controller = fxmlLoader.getController();
	       controller.cargarDatos(datos, media);
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
