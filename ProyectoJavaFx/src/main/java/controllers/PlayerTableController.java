package controllers;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.input.MouseEvent;
import models.Player;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.function.Predicate;
import org.apache.log4j.Logger;

public class PlayerTableController {

	@FXML TableView<Player> playerTable;
	@FXML
	private TableColumn <Player, String> nameCol;
	@FXML
	private TableColumn <Player, String> positionCol;
	@FXML
	private TableColumn <Player, String> pointsCol;
	@FXML
	private TableColumn <Player, String> clubCol;
	@FXML
	private TableColumn <Player, String> salaryCol;
	@FXML
	private TextField searchField;
	ObservableList<Player> players;
	private FilteredList<Player> filteredData ;
	private Player selectedPlayer;
	private Controller control;
	static Logger logger = Logger.getLogger(ControllerGeneral.class);

	public PlayerTableController() {
		super();
	}
	
	public void initialize() {
		players = FXCollections.observableArrayList();
		setUsersToTable();
		getPlayers();
		playerTable.setItems(players);

        filteredData= new FilteredList<Player> (players);
        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // TODO Auto-generated method stub
                filterTable();
                System.out.println(newValue);
            }
        });
        playerTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Player>() {
            @Override
            public void changed(ObservableValue<? extends Player> observableValue, Player oldValue, Player newValue) {
                //Check whether item is selected and set value of selected item to Label
                if (playerTable.getSelectionModel().getSelectedItem() != null)
                {
                    TableViewSelectionModel selectionModel = playerTable.getSelectionModel();
                    ObservableList selectedItems = selectionModel.getSelectedItems();
                    selectedPlayer = (Player) selectedItems.get(0);
                }
            }
        });
        playerTable.setOnMouseClicked(new EventHandler<MouseEvent>() {

            @Override
            public void handle(MouseEvent event) {
                // TODO Auto-generated method stub
                if ( event.getClickCount() == 2) {
                    System.out.println("What up gangsta");
//                    control.seeUserInfor(selectedPlayer);

                }
            }
        });
        playerTable.setItems(filteredData);
	}

	public void setUsersToTable() {
		nameCol.setCellValueFactory(new PropertyValueFactory<Player, String>("name"));
		positionCol.setCellValueFactory(new PropertyValueFactory<Player, String>("position"));
		clubCol.setCellValueFactory(new PropertyValueFactory<Player, String>("club"));
		pointsCol.setCellValueFactory(new PropertyValueFactory<Player, String>("points"));
		salaryCol.setCellValueFactory(new PropertyValueFactory<Player, String>("salary"));
        playerTable.setItems(players);
		nameCol.setText("Nombre");
		positionCol.setText("Posicion");
		clubCol.setText("Club");
		pointsCol.setText("Puntos");
		salaryCol.setText("Salario");
	}

	public void getPlayers() {
		players.add(new Player("Cristiano Ronaldo", "DEL", "Real Madrid", "150", "50.000 €"));
		players.add(new Player("Cristiano Ronaldo", "DEL", "Real Madrid", "150", "50.000 €"));
		players.add(new Player("Cristiano Ronaldo", "DEL", "Real Madrid", "150", "50.000 €"));
		players.add(new Player("Cristiano Ronaldo", "DEL", "Real Madrid", "150", "50.000 €"));
		players.add(new Player("Javi Urbistondo", "DEL", "Real Madrid", "150", "50.000 €"));
	}

	public void filterTable() {
		filteredData.setPredicate(new Predicate<Player>() {
			@Override
			public boolean test(Player arg0) {
				// Compare first name and last name of every person with filter text.
				String lowerCaseFilter = searchField.getText().toLowerCase();
				if (arg0.getName().toLowerCase().contains(lowerCaseFilter)) {
					return true; // Filter matches first name.
				}else {
					return false; // Does not match.
				}
			}
		});
	}

}
