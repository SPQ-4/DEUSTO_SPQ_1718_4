package controllers;

import db.MySQLDriver;
import java.net.URL;
import java.util.ResourceBundle;
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
import models.Contest;
import org.apache.log4j.Logger;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.function.Predicate;

public class ContestTableController implements Initializable {

    @FXML
    protected TableView<Contest> contestTable;
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
    protected ObservableList<Contest> contests;
    private FilteredList<Contest> filteredData ;
    private MySQLDriver driver;
    private Contest selectedContest;
    private Controller controller;
    static Logger logger = Logger.getLogger(ControllerGeneral.class);

    /**
     * Constructor vacío
     */
    public ContestTableController() {
        super();
    }

    /**
     * Método inicializador que genera la lista, la rellena con los datos de los torneos de la BD,
     * añade la lista a la tabla y establece los listeners necesarios para mostrar el torneo que
     * se seleccione y para filtrar la tabla.
     */
    public void initialize(URL location, ResourceBundle resources) {
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
                    controller.seeContestInfo(selectedContest);
                }
            }
        });
        contestTable.setItems(filteredData);
    }

    /**
     * Método que enlaza los TableColumns del controller con los del FXML y añade los items a la
     * tabla
     */
    public void setContestsToTable() {
        titleCol.setCellValueFactory(new PropertyValueFactory<Contest, String>("title"));
        minimumParticipantsCol.setCellValueFactory(new PropertyValueFactory<Contest, String>("minimumParticipants"));
        maximumParticipantsCol.setCellValueFactory(new PropertyValueFactory<Contest, String>("maximumParticipants"));
        entryFeeCol.setCellValueFactory(new PropertyValueFactory<Contest, String>("entryFee"));
    }

    /**
     * Método que lee los datos de todos los torneos existentes en la BD y que los añade a la lista
     * correspondiente convertiéndolos a objetos Contest
     */
    public void getContests() {
        driver = new MySQLDriver();
        String query = "SELECT * FROM panenka_db.contests_contest";
        ResultSet result = driver.runQuery(query);
        try {
            while (result.next())
            {
                contests.add(new Contest(result.getInt("id"), result.getString("title"), result.getString("description"), result.getString("open_date"), result.getString("close_date"), Integer.toString(result.getInt("minimum_participants")), Integer.toString(result.getInt("maximum_participants")), Double.toString(result.getDouble("entry_fee"))));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        driver.close();
    }

    /**
     * Método que filtra la tabla de Contests en función de los términos de búsqueda introducidos
     */
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

    /**
     * Método que establece el controller a la clase
     * @param Controller El controller que se quiere asignar a la clase
     */
    public void setController(Controller control){
        this.controller = control;
    }

    public Controller getController() {
        return this.controller;
    }

}