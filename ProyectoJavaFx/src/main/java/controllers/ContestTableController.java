package controllers;

import db.MySQLDriver;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TableView.TableViewSelectionModel;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import models.Contest;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Predicate;

public class ContestTableController {

    @FXML TableView<Contest> contestTable;
    @FXML
    private TableColumn <Contest, String> titleCol;
    @FXML
    private TableColumn <Contest, String> minimumParticipantsCol;
    @FXML
    private TableColumn <Contest, String> maximumParticipantsCol;
    @FXML
    private TableColumn <Contest, String> entryFeeCol;
    @FXML
    private TextField searchField;
    private ObservableList<Contest> contests;
    private FilteredList<Contest> filteredData ;
    private MySQLDriver driver;
    private Contest selectedContest;
    private Controller control;
    static Logger logger = Logger.getLogger(ControllerGeneral.class);

    public ContestTableController() {
        super();
    }

    public void initialize() {
        contests = FXCollections.observableArrayList();
        setContestsToTable();
        getContests();
        contestTable.setItems(contests);
        filteredData= new FilteredList<Contest> (contests);
        searchField.textProperty().addListener(new ChangeListener<String>() {
            @Override
            public void changed(ObservableValue<? extends String> observable, String oldValue, String newValue) {
                // TODO Auto-generated method stub
                filterTable();
                System.out.println(newValue);
            }
        });
        contestTable.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Contest>() {
            @Override
            public void changed(ObservableValue<? extends Contest> observableValue, Contest oldValue, Contest newValue) {
                if (contestTable.getSelectionModel().getSelectedItem() != null)
                {
                    TableViewSelectionModel selectionModel = contestTable.getSelectionModel();
                    ObservableList selectedItems = selectionModel.getSelectedItems();
                    selectedContest = (Contest) selectedItems.get(0);
                }
            }
        });
        contestTable.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {
                // TODO Auto-generated method stub
                if (event.getClickCount() == 2) {
//                    System.out.println(selectedContest.getTitle() + " " + selectedContest.getId());
                    control.seeContestInfo(selectedContest);
                }
            }
        });
        contestTable.setItems(filteredData);
    }

    public void setContestsToTable() {
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

    public void getContests() {
        driver = new MySQLDriver();
        String query = "SELECT * FROM panenka_db.contests_contest";
        ResultSet result = driver.runQuery(query);
        try {
            while (result.next())
            {
                contests.add(new Contest(result.getInt("id"), result.getString("title"), Integer.toString(result.getInt("minimum_participants")), Integer.toString(result.getInt("maximum_participants")), Double.toString(result.getDouble("entry_fee"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        driver.close();
    }

    public void filterTable() {
        filteredData.setPredicate(new Predicate<Contest>() {
            @Override
            public boolean test(Contest arg0) {
                String lowerCaseFilter = searchField.getText().toLowerCase();
                if (arg0.getTitle().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                }else {
                    return false;
                }
            }
        });
    }

    public void selectContest(Contest contest) {
        ObservableList<Contest> data = contestTable.getItems();
        data.add(contest);
    }

}
