package models;
import db.MySQLDriver;
import java.net.URL;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class Main2 extends Application {

  private static MySQLDriver driver;

  public static MySQLDriver getDBDriver() {
      if (driver == null)
          driver = new MySQLDriver();
      return driver;
  }

	/**
	 * este método se lanza con el launch y se manda automáticamente un Stage,
	 * para eso tiene que extender de aplicación
	 * como es el del main, cargamos el fxml donde tenemos la pantalla de login y lo metemos en la escena
	 */
    @Override
    public void start(Stage primaryStage) throws Exception {
        URL resource =this.getClass().getResource("/views/HomePage.fxml");
        Parent root = FXMLLoader.load(resource);
        primaryStage.setTitle("HomePage");
        primaryStage.setScene(new Scene(root, 1000, 700));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
