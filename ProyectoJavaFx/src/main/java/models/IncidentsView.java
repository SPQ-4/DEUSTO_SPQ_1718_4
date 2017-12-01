package models;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.Parent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import controllers.IncidentsTablesController;
import controllers.UserTableController;

public class IncidentsView extends Application {
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
		ArrayList<Incident> incidents = new ArrayList<Incident>();
		Incident i1 = new Incident("juangara", "queja", "No puedo fichar");
		Incident i2 = new Incident("paula", "mal funcionamiento", "Me sale error 404");
		Incident i3 = new Incident("asier", "queja", "el dinero de mi cuenta no es correcto");
		Incident i4 = new Incident("javi", "queja", "el dinero de mi cuenta no es correcto");
		i4.setRespuesta("Dinero reestablecido en cuenta");
		i4.setGestionada(true);
		incidents.add(i1);
		incidents.add(i2);
		incidents.add(i3);
		incidents.add(i4);
       primaryStage.setTitle("Incidencias");
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/Incident_View.fxml"));   
       Pane myPane = (Pane)fxmlLoader.load();
       IncidentsTablesController controller = fxmlLoader.getController();
      // controller.llenarTablas(incidents);
       controller.selectIncident(i4);
      
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
