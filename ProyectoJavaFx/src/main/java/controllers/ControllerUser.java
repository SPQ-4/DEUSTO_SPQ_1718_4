package controllers;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class ControllerUser implements Initializable{
	@FXML
	TableView<Usuario>UsersTable=new TableView<>();
	@FXML
	private TableColumn <Usuario,String>nameCol;
	@FXML
	private TableColumn <Usuario,String>mailCol;
	@FXML
	private TextField textField;
	ObservableList <Usuario>UsersData=FXCollections.observableArrayList();
	FilteredList<Usuario> filteredData ;
	Usuario selectedUser;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectedUser=null;
		setUsersToTable();
		setUsersToList();
		// 1. Wrap the ObservableList in a FilteredList (initially display all data).
		filteredData= new FilteredList<Usuario>(UsersData);
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				filterTable();
				System.out.println(newValue);
			}
		});
		UsersTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Usuario>() {
			@Override
			public void changed(ObservableValue<? extends Usuario> observableValue, Usuario oldValue, Usuario newValue) {
			       //Check whether item is selected and set value of selected item to Label
			       if(UsersTable.getSelectionModel().getSelectedItem() != null) 
			        {    
			           TableViewSelectionModel selectionModel = UsersTable.getSelectionModel();
			           ObservableList selectedItems = selectionModel.getSelectedItems();
			           selectedUser=(Usuario) selectedItems.get(0);
			         }
				}
			});
		UsersTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				// TODO Auto-generated method stub
				if ( event.getClickCount() == 2) {
	                System.out.println("Doble click en "+selectedUser.getNombre());
	                System.out.println("insertar aquí llamada a Juan");
	                
	            }
			}
		});
		UsersTable.setItems(filteredData);
	}
	public void setUsersToTable() {
		/*Investigar por qué solo funciona si se llama desde el initialize y sino da fallo*/
		nameCol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("nombre"));
		mailCol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("email"));
		UsersTable.setItems(UsersData);
		nameCol.setText("NAME");
		mailCol.setText("COL");	
	}
	public void setUsersToList() {
		//HAY QUE LEER DE LA BD
		UsersData.add(new Usuario("Asier","asier@mail.com"));
		UsersData.add(new Usuario("Paula","paula@mail.com"));
		UsersData.add(new Usuario("Juan","juan@mail.com"));
		UsersData.add(new Usuario("Javier","javier@mail.com"));
		UsersData.add(new Usuario("Virginia","virginia@mail.com"));
		UsersData.add(new Usuario("Pablo","pablo@mail.com"));
		UsersData.add(new Usuario("Iker","iker@mail.com"));
	}	
	public void filterTable() {
		filteredData.setPredicate(new Predicate<Usuario>() {
			@Override
			public boolean test(Usuario arg0) {				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = textField.getText().toLowerCase();			
				if (arg0.getNombre().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}else {
				return false; // Does not match.
				}
			}	
		});
	}
}
