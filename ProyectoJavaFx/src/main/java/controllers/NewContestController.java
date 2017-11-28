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
import java.time.LocalDate;
import java.util.Date;
import java.util.ResourceBundle;
import org.apache.log4j.Logger;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import models.NewContestMethods;
 
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
	private int min;
	private int max;
	private int fee;
	private NewContestMethods metodos;
	
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
		metodos= new NewContestMethods();
		try {
			fillSelectlist();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		create.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	if(checkings(contest.getText(), maxPart.getText(), minPart.getText(),
    	    		entryFee.getText(),(LocalDate)openDate.getValue(),
    	    		(LocalDate) closeDate.getValue()))
    	    	{
    	    		metodos.newContest(contest.getText(), password.getText(),
    	    		(LocalDate)openDate.getValue(), (LocalDate) closeDate.getValue(), 
    	    		Integer.parseInt(minPart.getText()), Integer.parseInt(maxPart.getText()),
    	    		Integer.parseInt(entryFee.getText()), descrip.getText());
    	    	}  
    	    }
    	});	 
	}
	public void setBorderContest(Boolean yesNo){
		if(yesNo){
			contest.setStyle("-fx-border-color: RED");
		}else{
			contest.setStyle("-fx-border-color: GREEN");
		}
	}
	public void setBorderMinPart(Boolean yesNo){
		if(yesNo){
			minPart.setStyle("-fx-border-color: RED");
		}else{
			minPart.setStyle("-fx-border-color: GREEN");
		}
	}
	public void setBorderMaxPart(Boolean yesNo){
		if(yesNo){
			maxPart.setStyle("-fx-border-color: RED");
		}else{
			maxPart.setStyle("-fx-border-color: GREEN");
		}
	}
	public void setBorderEntryFee(boolean yesNo){
		if(yesNo){
			entryFee.setStyle("-fx-border-color: RED");
		}else{
			entryFee.setStyle("-fx-border-color: GREEN");
		}
	}
	public void setBorderDate(boolean yesNo){
		if(yesNo){
			openDate.setStyle("-fx-border-color: RED");
			closeDate.setStyle("-fx-border-color: RED");
		}else{
			openDate.setStyle("-fx-border-color: GREEN");
			closeDate.setStyle("-fx-border-color: GREEN");
		}
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
	public boolean checkNumbers(){
		if(metodos.checkNumber(maxPart.getText())){
			setBorderMaxPart(true);
			return true;
		}else{
			setBorderMaxPart(false);
		}
		if(metodos.checkNumber(minPart.getText())){
			setBorderMinPart(true);
			return true;
		}else{
			setBorderMinPart(false);
		}
		if(metodos.checkNumber(entryFee.getText())){
			setBorderEntryFee(true);
			return true;
		}else{
			
			setBorderEntryFee(false);
		}
		return false;
	}
	public boolean checkings(String contest,String max, String min, String fee, LocalDate open, 
			LocalDate close){
			if(metodos.testContestName(contest)){
				setBorderContest(true);
				return false;
			}else{
				setBorderContest(false);
			}
			if(metodos.checkDates(open, close)){
				setBorderDate(true);
				return false;
			}else{
				setBorderDate(false);
			}
			if(checkNumbers()){
				return false;
			}
			if(metodos.checkMaxMin(Integer.parseInt(maxPart.getText()), Integer.parseInt(minPart.getText()))){
				setBorderMaxPart(true);
				setBorderMinPart(true);
				return false;
			}else{
				setBorderMaxPart(false);
				setBorderMinPart(false);
			}	
			
		return true;
	}
}


