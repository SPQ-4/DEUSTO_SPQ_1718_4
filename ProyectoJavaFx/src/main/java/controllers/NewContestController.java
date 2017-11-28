package controllers;

import db.MySQLDriver;
import db.NewContestBD;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;

import java.io.IOException;
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
import models.Popup;
 
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
	private NewContestMethods metodos;
	private NewContestBD BDrelation;
	
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
		BDrelation= new NewContestBD();
		fillSelectlist();
		create.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	if(checkEmpty()){
	    	    	if(checkings(contest.getText(), maxPart.getText(), minPart.getText(),
	    	    		entryFee.getText(),(LocalDate)openDate.getValue(),
	    	    		(LocalDate) closeDate.getValue()))
	    	    	{
	    	    		int type1= BDrelation.getContestType_id(type.getValue());
	    	    		int model=BDrelation.getGameMode_id(mode.getValue());;
	    	    		int matchday=BDrelation.getMatchday_id(matchDay.getValue());
	    	    		BDrelation.newContest(contest.getText(), password.getText(),
	    	    		(LocalDate)openDate.getValue(), (LocalDate) closeDate.getValue(), 
	    	    		Integer.parseInt(minPart.getText()), Integer.parseInt(maxPart.getText()),
	    	    		Integer.parseInt(entryFee.getText()), descrip.getText(),type1,model,matchday);
	    	    	}  
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
	public void fillSelectlist(){		
		type.setItems(BDrelation.listType());
		type.getSelectionModel().selectFirst();
				
		mode.setItems(BDrelation.listMode());
		mode.getSelectionModel().selectFirst();	
		
		matchDay.setItems(BDrelation.listMatchday());
		matchDay.getSelectionModel().selectFirst();
	}
	public boolean checkNumbers(){
		if(metodos.checkNumber(maxPart.getText())){
			try {
				new Popup("Debe ser un numero");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setBorderMaxPart(true);
			return true;
		}else{
			setBorderMaxPart(false);
		}
		if(metodos.checkNumber(minPart.getText())){
			try {
				new Popup("Debe ser un numero");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setBorderMinPart(true);
			return true;
		}else{
			setBorderMinPart(false);
		}
		if(metodos.checkNumber(entryFee.getText())){
			try {
				new Popup("Debe ser un numero");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			setBorderEntryFee(true);
			return true;
		}else{
			
			setBorderEntryFee(false);
		}
		return false;
	}
	public boolean checkEmpty(){
		if(contest.getText().isEmpty()|| maxPart.getText().isEmpty()|| minPart.getText().isEmpty()||
		   openDate.toString().isEmpty()|| closeDate.toString().isEmpty()|| 
		   password.getText().isEmpty()||descrip.getText().isEmpty()){
			 try {
				new Popup("Ningun campo puede ser null");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return false;
		}
		return true;
	}
	public boolean checkings(String contest,String max, String min, String fee, LocalDate open, 
			LocalDate close){
			if(metodos.testContestName(contest)){
				try {
					new Popup("Ese nombre ya esta en la BD");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setBorderContest(true);
				return false;
			}else{
				setBorderContest(false);
			}
			if(metodos.checkDates(open, close)){
				try {
					new Popup("la fecha de cierre es anterior a la de apertura");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				setBorderDate(true);
				return false;
			}else{
				setBorderDate(false);
			}
			if(checkNumbers()){
				return false;
			}
			if(metodos.checkMaxMin(Integer.parseInt(maxPart.getText()), Integer.parseInt(minPart.getText()))){
				try {
					new Popup("numero de participantes minimos debe ser menor que el de maximos");
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
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


