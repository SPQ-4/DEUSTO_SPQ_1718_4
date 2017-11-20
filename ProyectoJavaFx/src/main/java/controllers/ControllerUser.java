package controllers;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.function.Predicate;

import org.apache.log4j.Logger;

import db.MySQLDriver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
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
	private MySQLDriver driverDB;
	private ObservableList <Usuario>UsersData=FXCollections.observableArrayList();
	private FilteredList<Usuario> filteredData ;
	private Usuario selectedUser;
	private Controller control;
	static Logger logger = Logger.getLogger(ControllerGeneral.class);
	public ControllerUser() {
		driverDB=new MySQLDriver();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		selectedUser=null;
		driverDB= new MySQLDriver();
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
	public int setUsersToList() {
		String query1="select * from panenka_db.users_user";
		ResultSet type1= driverDB.runQuery(query1);
		try {
			while(type1.next()){
					Usuario user=new Usuario(type1.getString("username"),type1.getDouble("balance"),type1.getDouble("balance"),type1.getDate("created"));
					System.out.println(user.getFecha_registro());
					UsersData.add(user);
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			//e.printStackTrace();
			return 1;
		}	
		return 0;
	}	

	
	public void filterTable() {
		filteredData.setPredicate(new Predicate<Usuario>() {
			@Override
			public boolean test(Usuario arg0) {				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = textField.getText().toLowerCase();			
				if (arg0.getEmail().toLowerCase().contains(lowerCaseFilter)) {
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
