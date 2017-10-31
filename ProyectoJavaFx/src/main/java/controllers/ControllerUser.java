package controllers;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.User;

public class ControllerUser implements Initializable{
	@FXML
	TableView<User>UsersTable=new TableView<>();
	@FXML
	private TableColumn <User,String>nameCol;
	@FXML
	private TableColumn <User,String>mailCol;
	
	ObservableList <User>UsersData=FXCollections.observableArrayList();
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		setUsersToTable();
		setUsersToList();
	}
	public void setUsersToTable() {
		/*Investigar por qué solo funciona si se llama desde el initialize y sino da fallo*/
		nameCol.setCellValueFactory(new PropertyValueFactory<User,String>("name"));
		mailCol.setCellValueFactory(new PropertyValueFactory<User,String>("mail"));
		UsersTable.setItems(UsersData);
		nameCol.setText("NAME");
		mailCol.setText("COL");
		
	}
	public void setUsersToList() {
		UsersData.add(new User("Asier","asier@mail.com"));
		UsersData.add(new User("Paula","paula@mail.com"));
		UsersData.add(new User("Juan","juan@mail.com"));
		UsersData.add(new User("Javier","javier@mail.com"));
	}
	
}
