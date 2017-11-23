package controllers;

import db.MySQLDriver;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import java.net.URL;
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
    ComboBox <String> entryFee;
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
		create.setOnAction(new EventHandler<ActionEvent>() {
    	    @Override public void handle(ActionEvent e) {
    	    	System.out.println(contest.getText());
    	    	System.out.println(openDate.getValue());
    	    }
    	});	 
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


