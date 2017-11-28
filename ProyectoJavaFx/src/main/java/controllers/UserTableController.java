package controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import models.Usuario;
import javafx.beans.property.*;

public class UserTableController {
	public UserTableController() {
		super();
	}
	
	public void initialize() {
		
	}

	@FXML private TableView<Usuario> tableView;
	
	public void selectUser(Usuario user) {
		
		
		 ObservableList<Usuario> data = tableView.getItems();
		 data.add(user);
	}

}
