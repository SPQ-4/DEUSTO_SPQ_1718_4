package controllers;

import db.MySQLDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
 
public class NewContestController implements Initializable{
	
	@FXML
	DatePicker openDate;
	@FXML
    DatePicker closeDate;
	@FXML
    TextArea descrip;
	@FXML
    TextField contest;
	@FXML
    TextField password;
	@FXML
    TextField minPart;
	@FXML
    TextField maxPart;
	@FXML
    ComboBox <String> mode;
	@FXML
	TextField entryFee;
	@FXML
    ComboBox <String> type;
	@FXML
    ComboBox <String> matchDay;
	@FXML
	Button create;
	
	private MySQLDriver driverDB;
	static Logger logger = Logger.getLogger(NewContestController.class);
	/**
	 * este constructor es para cuando queremos utilizar métodos de
	 * este controller y hace falta que el driver de MySQL esté activo
	 */
	public NewContestController(){
		driverDB= new MySQLDriver();
	}
	@Override
	/**
	 * método que se llama desde el fxml al cargarse,
	 * inicializamos los drivers
	 */
	public void initialize(URL location, ResourceBundle resources) {
		logger.info("inicializando controller general");
		driverDB= new MySQLDriver();
		try {
			fillSelectlist();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		create.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	System.out.println(contest.getText());
    	    	System.out.println(openDate.getValue());
    	    }
    	});	 
	}
	/**
	 * rellenar los combobox para permitir al usuario elegir entre las opciones
	 * @throws SQLException al lanzar la query
	 */
	public void fillSelectlist() throws SQLException{
		ObservableList<String> listType = FXCollections.observableArrayList();
		String query1="select * from panenka_db.contests_contesttype;";
		ResultSet type1= driverDB.runQuery(query1);
		while(type1.next()){
			 listType.add(type1.getString("contest_type"));
		}
		
		type.setItems(listType);
		
		ObservableList<String> listMode = FXCollections.observableArrayList();
		String query2="select * from panenka_db.contests_gamemode;";
		ResultSet type2= driverDB.runQuery(query2);
		while(type2.next()){
			 listType.add(type2.getString("game_mode"));
		}
		
		mode.setItems(listMode);
		
		ObservableList<String> listMatchDay = FXCollections.observableArrayList();
		String query3="select * from panenka_db.contests_matchday;";
		ResultSet type3= driverDB.runQuery(query3);
		while(type3.next()){
			listMatchDay.add(type3.getString("matchday"));
		}
		
		mode.setItems(listMatchDay);
	}
	/**
	 * En este método comprobamos que el nombre que hemos metido no existe ya en la BD
	 * @param name nombre que le hemos dado al torneo
	 * @return devolvemos si está cogido o no
	 */
	public boolean testContestName(String name){
		return true;
	}
	/**
	 * comprobamos que la fecha de open es anterior a la fecha de close. no nos hace falta comprobar
	 * que sea una fecha porque al meterlo por datepicker solo te deja elegir una fecha real
	 * @param dateOpen fecha de apertura del torneo
	 * @param dateClose fecha  de cierre del torneo
	 * @return devolvemos si la de cierre es posterior o no
	 */
	public boolean checkDates(String dateOpen, String dateClose){
		return true;
	}
	/**
	 * pasamos string del valor que queremos convertir en numero
	 * @param number es el string que queremos pasar a number
	 * @return devolvemos el numero creado
	 */
	public int checkAreNumbers(String number){
		int a= Integer.parseInt(number);
		return a;
	}
	/**
	 * Comprobamos que el numero de participantes maximo es mayor que el minimo
	 * @param max
	 * @param min
	 * @return
	 */
	public boolean checkMaxMin(int max, int min){
		boolean a=false;
			if(max>min){
				a=true;
			}
		return a;
	}
	/**
	 * este método llama a la BD para crear un torneo
	 */
	public void newContest(){
		
	}
}


