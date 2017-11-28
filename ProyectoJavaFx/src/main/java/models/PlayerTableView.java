package models;

import controllers.PlayerTableController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.text.SimpleDateFormat;
import java.util.Date;

public class PlayerTableView extends Application {
   private Player player;

	@Override
    public void start(Stage primaryStage) throws Exception {
		String str = "07/12/2017";
		Date fecha =new SimpleDateFormat("dd/mm/yyyy").parse(str);
		player = new Player("Cristiano Ronaldo", "DEL", "Real Madrid", "150", "50.000 €");
		
       primaryStage.setTitle("Datos jugador");
       FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/playerTable.fxml"));
       Pane myPane = (Pane)fxmlLoader.load();
       PlayerTableController playerTableController = fxmlLoader.getController();
//       playerTableController.selectPlayer(player);
       Scene myScene = new Scene(myPane);
       primaryStage.setScene(myScene);
       primaryStage.show();
    }
 
    public static void main(String[] args) {
        launch(args);
    }
}
