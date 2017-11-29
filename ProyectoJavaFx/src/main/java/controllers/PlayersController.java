package controllers;

import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import org.apache.log4j.Logger;
import db.MySQLDriver;
import db.PlayersBD;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import models.MarketPlace;
import models.Player;

public class PlayersController implements Initializable {
	@FXML
	TableView<Player>PlayersTable;
	@FXML
	private TableColumn <Player,String>playerShirtCol;
	@FXML
	private TableColumn <Player,String>player_positionCol;
	@FXML
	private TableColumn <Player,String>player_teamCol;
	@FXML
	private TableColumn <Player,String>player_nationalityCol;
	@FXML
	private TableColumn <Player,Double>player_valueCol;
	@FXML
	private TableColumn <Player,Double>player_pointsCol;
	@FXML
	private TextField textField;
	@FXML
	private Button actualizarPrecio;
	private PlayersBD playerDB;
	private MarketPlace market;
	ObservableList <Player>PlayersData;
	private FilteredList<Player> filteredData ;
	static Logger logger = Logger.getLogger(ControllerGeneral.class);
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		PlayersData=FXCollections.observableArrayList();
		playerDB=new PlayersBD(new MySQLDriver());
		setPlayersToTable();
		setPlayersToList();
		filteredData= new FilteredList<Player>(PlayersData);
		textField.textProperty().addListener(new ChangeListener<String>() {
			@Override
			public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
				// TODO Auto-generated method stub
				filterTable();
			}
		});
		actualizarPrecio.setOnAction(new EventHandler<ActionEvent>() {
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				System.out.println("LLAMAR");
				market.setMarketValuePlayers();
			}
		});
		PlayersTable.setItems(filteredData);
	}
	/**
	 * DOXYFUNCIONA
	 * método que inicializa la tabla con los valores por defecto de los jugadores:nombre, posición, equipo, nacionalidad,valor
	 * y puntos
	 */
	public void setPlayersToTable() {
		playerShirtCol.setCellValueFactory(new PropertyValueFactory<Player,String>("playerShirt"));
		player_positionCol.setCellValueFactory(new PropertyValueFactory<Player,String>("player_position"));
		player_teamCol.setCellValueFactory(new PropertyValueFactory<Player,String>("player_team"));
		player_nationalityCol.setCellValueFactory(new PropertyValueFactory<Player,String>("player_nationality"));
		player_valueCol.setCellValueFactory(new PropertyValueFactory<Player,Double>("player_value"));
		player_pointsCol.setCellValueFactory(new PropertyValueFactory<Player,Double>("player_points"));
		PlayersTable.setItems(PlayersData);
	}
	/**
	 * Método que inserta los jugadores en la tabla. Para ello utiliza un metodo getPlayers() que lee
	 * de la BD los jugadores y devuelve un ArrayList
	 * @return devuelve 1 si ha habido fallo, 0 si todo OK
	 */
	public int setPlayersToList() {
		ArrayList <Player>parcial=playerDB.getPlayers();
		if(parcial==null) {
			logger.error("No había jugadores en la BD");
			return 1;
		}else {
			logger.info("Se han leído los jugadores de la BD");
			//PlayersData.add(parcial.get(0));
			PlayersData.addAll(parcial);	
			market=new MarketPlace(parcial);
			return 0;
		}
		
	}	
	/**
	 * Método que llama cada vez que ocurre una acción en el TextField para filtrar la tabla
	 */
	public void filterTable() {
		filteredData.setPredicate(new Predicate<Player>() {
			@Override
			public boolean test(Player arg0) {				
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = textField.getText().toLowerCase();			
				if (arg0.getPlayerShirt().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}else {
				return false; // Does not match.
				}
			}	
		});
	}
}

