package controllers;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
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
	private TableColumn <Usuario,String>emailCol;
	@FXML
	private TableColumn <Usuario,Integer>saldoCol;
	@FXML
	private TextField textField;
	ObservableList <Usuario>UsersData=FXCollections.observableArrayList();
	FilteredList<Usuario> filteredData ;
	Usuario selectedUser;
	Controller control;
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
	                control.seeUserInfor(selectedUser);
	                
	            }
			}
		});
		UsersTable.setItems(filteredData);
	}
	public void setUsersToTable() {
		/*Investigar por qué solo funciona si se llama desde el initialize y sino da fallo*/
		emailCol.setCellValueFactory(new PropertyValueFactory<Usuario,String>("email"));
		saldoCol.setCellValueFactory(new PropertyValueFactory<Usuario,Integer>("saldo"));
		UsersTable.setItems(UsersData);
		emailCol.setText("EMAIL");
		saldoCol.setText("SALDO");	
	}
	public void setUsersToList() {
		//HAY QUE LEER DE LA BD
		String str = "04/11/2017";
		Date fecha;
		try {
			fecha = new SimpleDateFormat("dd/mm/yyyy").parse(str);
			Usuario user = new Usuario("juan@hotmail.com", 20, 500, fecha);
			UsersData.add(user);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
//		UsersData.add(new Usuario("Asier","asier@mail.com"));
//		UsersData.add(new Usuario("Paula","paula@mail.com"));
//		UsersData.add(new Usuario("Juan","juan@mail.com"));
//		UsersData.add(new Usuario("Javier","javier@mail.com"));
//		UsersData.add(new Usuario("Virginia","virginia@mail.com"));
//		UsersData.add(new Usuario("Pablo","pablo@mail.com"));
//		UsersData.add(new Usuario("Iker","iker@mail.com"));
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
	public void setController(Controller control){
		this.control=control;
	}
}
