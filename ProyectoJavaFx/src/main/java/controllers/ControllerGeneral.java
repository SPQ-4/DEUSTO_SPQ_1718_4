package controllers;

import java.io.IOException;

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
	
	@FXML
	public void initialize() {
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
    	        stage.close();
    	    }
    	});
	}
}
