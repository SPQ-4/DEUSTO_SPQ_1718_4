package controllers;

import java.awt.event.MouseEvent;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import javax.xml.stream.EventFilter;
import javax.xml.stream.events.XMLEvent;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TablePosition;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.User;

public class ControllerUser implements Initializable{
	@FXML
	TableView<User>UsersTable=new TableView<>();
	@FXML
	private TableColumn <User,String>nameCol;
	@FXML
	private TableColumn <User,String>mailCol;
	@FXML
	private TextField textField;
	ObservableList <User>UsersData=FXCollections.observableArrayList();
	FilteredList<User> filteredData ;
	User selectedUser;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectedUser=null;
		setUsersToTable();
		setUsersToList();
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		filteredData= new FilteredList<User>(UsersData);
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				filterTable();
				System.out.println(newValue);
			}
		});
		UsersTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<User>() {
			@Override
			public void changed(ObservableValue<? extends User> observableValue, User oldValue, User newValue) {
			       //Check whether item is selected and set value of selected item to Label
			       if(UsersTable.getSelectionModel().getSelectedItem() != null) 
			        {    
			           TableViewSelectionModel selectionModel = UsersTable.getSelectionModel();
			           ObservableList selectedItems = selectionModel.getSelectedItems();
			           selectedUser=(User) selectedItems.get(0);
			           System.out.println("El usuario se ha quedado seleccionado");
			         }
				}
			});
		UsersTable.setItems(filteredData);
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
		UsersData.add(new User("Virginia","virginia@mail.com"));
		UsersData.add(new User("Pablo","pablo@mail.com"));
		UsersData.add(new User("Iker","iker@mail.com"));
	}	
	public void filterTable() {
		filteredData.setPredicate(new Predicate<User>() {
			@Override
			public boolean test(User arg0) {				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = textField.getText().toLowerCase();			
				if (arg0.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}else {
				return false; // Does not match.
				}
			}	
		});
	}
}
