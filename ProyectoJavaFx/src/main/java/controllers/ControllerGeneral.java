package controllers;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import db.MySQLDriver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ControllerGeneral {
	@FXML
	private Button actualState;
	@FXML
	private Button economicState;
	@FXML
	private Button userState;
	@FXML
	private Button exit;
	@FXML
	private Pane centralPane;
	
	private MySQLDriver driverDB;
	
	@FXML
	public void initialize() {

		driverDB= new MySQLDriver();
		//todos los setOnAction es para darles funcionalidad a los botones del menú de navegación
		actualState.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	centralPane.getChildren().clear();
    	    	try {
					centralPane.getChildren().add((Node) FXMLLoader.load(getClass().getResource("../views/sample.fxml")));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    }
    	});
		
		economicState.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	centralPane.getChildren().clear();
    	    	try {
					centralPane.getChildren().add((Node) FXMLLoader.load(getClass().getResource("../views/sample.fxml")));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    }
    	});
		
		userState.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	centralPane.getChildren().clear();
    	    	try {
					centralPane.getChildren().add((Node) FXMLLoader.load(getClass().getResource("../views/User_Table.fxml")));
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    }
    	});
		exit.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	Stage stage = (Stage) exit.getScene().getWindow();
    	        // do what you have to do
    	       // stage.close();
    	    	try {
    	    		//para hacer las pruebas de si está bien la query después esto irá metido en un textarea para visualizar
					datosTorneoPorTipo();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
    	    }
    	});
	}
	public void datosTorneoPorTipo() throws SQLException{
		//habrá que hacer una query por cada tipo de torneo, por ahora hay 4 tipos (en caso de haber más se podría hacer con un while)
		String query2="select count(case id_contest_type when 'contest_type_1' then 1 else NULL end ) as value from panenka.contests "
				+ "where YEAR(open_date) = YEAR(CURDATE()) ;";
		ResultSet jornadas= driverDB.runQuery(query2);
		String queJornada=new String();
		while(jornadas.next()){
			queJornada=jornadas.getString("value");
		}
		System.out.println(queJornada);
	}
}


