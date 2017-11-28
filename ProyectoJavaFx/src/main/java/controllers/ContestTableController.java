package controllers;

import db.MySQLDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import models.Contest;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;

public class ContestTableController {

    @FXML
    TableView<Contest> contestTable;
    @FXML
    private TableColumn<Contest, String> titleCol;
    @FXML
    private TableColumn <Contest, String> minimumParticipantsCol;
    @FXML
    private TableColumn <Contest, String> maximumParticipantsCol;
    @FXML
    private TableColumn <Contest, String> entryFeeCol;
    private ObservableList<Contest> contests;
    private MySQLDriver driver;

    public ContestTableController() {
        super();
    }

    public void initialize() {
        contests = FXCollections.observableArrayList();
        setUsersToTable();
        getPlayers();
        contestTable.setItems(contests);
    }

    public void setUsersToTable() {
        titleCol.setCellValueFactory(new PropertyValueFactory<Contest, String>("title"));
        minimumParticipantsCol.setCellValueFactory(new PropertyValueFactory<Contest, String>("minimumParticipants"));
        maximumParticipantsCol.setCellValueFactory(new PropertyValueFactory<Contest, String>("maximumParticipants"));
        entryFeeCol.setCellValueFactory(new PropertyValueFactory<Contest, String>("entryFee"));
        contestTable.setItems(contests);
        titleCol.setText("Nombre");
        minimumParticipantsCol.setText("Participantes (min)");
        maximumParticipantsCol.setText("Participantes (max)");
        entryFeeCol.setText("Entrada");
    }

    public void getPlayers() {
        driver = new MySQLDriver();
        String query = "SELECT * FROM panenka_db.contests_contest";
        ResultSet result = driver.runQuery(query);
        try {
            while (result.next())
            {
                contests.add(new Contest(result.getString("title"), Integer.toString(result.getInt("minimum_participants")), Integer.toString(result.getInt("maximum_participants")), Double.toString(result.getDouble("entry_fee"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        driver.close();
    }

}
