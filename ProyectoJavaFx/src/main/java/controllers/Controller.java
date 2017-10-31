package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Button;

public class Controller {
	@FXML
	private Button prueba;
	
	@FXML
	public void initialize() {
		prueba.setText("pruebilla");
	}
	public void button1() {
		System.out.println("Hellow");
	}
	public Controller() {
		
	}
}
