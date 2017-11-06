package controllers;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import models.Main;

public class UserController {
	@FXML
	private TableView <Usuario> userTable;
	@FXML
	private TableColumn<Usuario,String> firstColumn;
	@FXML
	private TableColumn<Usuario,Integer>secondColumn;
	@FXML
	private Main main;
	@FXML
    private void initialize() {
        // Initialize the person table with the two columns.
    }

}
