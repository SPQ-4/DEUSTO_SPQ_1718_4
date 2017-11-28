package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import models.Usuario;

public class Controller {


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
	@FXML
	private Pane pieChart;
	@FXML
	private Pane barChart;
	@FXML
	private GridPane economic;
	
	/**
	 * este método se llama automáticamente desde el FXMl,
	 * es una especie de constructor pero sin necesidad de hacer new() porque eso lo hace el XML
	 * en este caso le vamos a dar a los botones del menú la funcionalidad, por eso el setOnAction para cada uno
	 * es necesario que exista el equivalente a los elementos dentro del XML
	 */
	@FXML
	public void initialize() {
		//todos los setOnAction es para darles funcionalidad a los botones del menú de navegación
				actualState.setOnAction(new EventHandler<ActionEvent>() {
		    	    @Override public void handle(ActionEvent e) {
		    	    	centralPane.getChildren().clear();
		    	    	try {
							centralPane.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/views/GeneralInformation.fxml")));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    	    }
		    	});
				
				economicState.setOnAction(new EventHandler<ActionEvent>() {
		    	    @Override public void handle(ActionEvent e) {
		    	    	centralPane.getChildren().clear();
		    	    	centralPane.getChildren().add((GridPane)economic);
		    	    	try {
							pieChart.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/views/piechart.fxml")));
		    	    	} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    	    	try {
							barChart.getChildren().add((Node) FXMLLoader.load(getClass().getResource("/views/barchart.fxml")));
						} catch (IOException e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
		    	    }
		    	});
				
				userState.setOnAction(new EventHandler<ActionEvent>() {
		    	    @Override public void handle(ActionEvent e) {
		    	    	centralPane.getChildren().clear();
		    	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/User_Table.fxml"));
		    	    	
		    			try {
		    				centralPane.getChildren().add((Pane)fxmlLoader.load());
		    				setControllerToControllerUser(fxmlLoader);
		    			} catch (IOException a) {
		    				// TODO Auto-generated catch block
		    				a.printStackTrace();
		    			}
		    	    }
		    	});
				exit.setOnAction(new EventHandler<ActionEvent>() {
		    	    @Override public void handle(ActionEvent e) {
		    	    	Stage stage = (Stage) exit.getScene().getWindow();        
		    	        stage.close();
		    	    }
		    	});
	}
	/**
	 * este método lo utilizamos para obtener el controller asociado al xml que vamos a cargar, 
	 * lo necesitamos para después poder trabajar con los métodos de ese controller
	 * @param fxmlLoader el XML del que queramos obtener el controllerUser
	 */
	public void setControllerToControllerUser(FXMLLoader fxmlLoader){
		ControllerUser control=fxmlLoader.getController();
		control.setController(this);
	}
	/**
	 * con este método se carga la información de un usuario concreto, primero cargamos el XML con la nueva pantalla
	 * después llamamos al metodo del controller asociado a ese xml para seleccionar los datos de un usuario
	 * @param user el usuario del que queremos ver la información
	 */
	public void seeUserInfor(Usuario user){
		centralPane.getChildren().clear();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/userTable.fxml"));
		try {
			centralPane.getChildren().add((Pane)fxmlLoader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UserTableController userTableController = fxmlLoader.getController();
	    userTableController.selectUser(user);
	}
}