package controllers;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

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
	public void initialize() {
		//todos los setOnAction es para darles funcionalidad a los botones del menú de navegación
				actualState.setOnAction(new EventHandler<ActionEvent>() {
		    	    @Override public void handle(ActionEvent e) {
		    	    	centralPane.getChildren().clear();
		    	    	try {
							centralPane.getChildren().add((Node) FXMLLoader.load(getClass().getResource("../views/GeneralInformation.fxml")));
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
		    	    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/User_Table.fxml"));
		    	    	
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
	public void setControllerToControllerUser(FXMLLoader fxmlLoader){
		ControllerUser control=fxmlLoader.getController();
		control.setController(this);
	}
	public void seeUserInfor(Usuario user){
		centralPane.getChildren().clear();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/userTable.fxml"));
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
