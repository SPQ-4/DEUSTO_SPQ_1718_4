package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.DatePicker;

public class NewContestBD {
	
	private MySQLDriver driverDB;
	/**
	 * necesitamos tener la conexion con la BD inicializada desde el principio
	 */
	public NewContestBD(){
		driverDB= new MySQLDriver();
	}
	/**
	 * para comprobar si el nombre que hemos elegido está ya cogido o no
	 * @param name es el nombre que ha metido el usuario por parametro
	 * @return true si ya está cogido, false si está libre
	 */
	public boolean testName(String name){
			boolean a=false;
			String query = "SELECT * FROM panenka_db.contests_contest WHERE title='" +name+ "'";
	        ResultSet result = driverDB.runQuery(query);
	         try {
				if(result.next()){
					a=true;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
	         return a;
	}
	/**
	 * es el metodo al que se llama despues de chequear todos los posibles parametros,
	 * la mayoría los pasamos directamente, pero algunos es necesario adaptarlos, como las fechas
	 * @param contest
	 * @param password
	 * @param openDate
	 * @param closeDate
	 * @param min
	 * @param max
	 * @param fee
	 * @param descrip
	 * @param contestType
	 * @param game_mode_id
	 * @param matchday_id
	 */
	public void newContest(String contest, String password, LocalDate openDate, LocalDate closeDate,
	int min, int max, int fee, String descrip, int contestType, int game_mode_id, int matchday_id){
		Date open= new Date(openDate.getYear()-1900,openDate.getMonthValue()-1,openDate.getDayOfMonth());
		Date close= new Date(openDate.getYear()-1900,openDate.getMonthValue()-1,openDate.getDayOfMonth());
			String query = "INSERT INTO panenka_db.contests_contest (created_date, title, password, "
					+ "created_by_admin, open_date, close_date, minimum_participants, "
					+ "maximum_participants, entry_fee, description, contest_type_id, game_mode_id, "
					+ "matchday_id)"
					+ "VALUES (NOW(), '"+contest+"', '"+password+"', '1', '"
					+ new java.sql.Date(open.getTime())+"','"+new java.sql.Date(close.getTime())+"',"+min+","+max+","+fee+""
					+ ",'"+descrip+"',"+contestType+","+game_mode_id+","+matchday_id+");";
		int result = driverDB.runUpdate(query);
	}
	/**
	 * es para obtener para rellenar el combobox de tipos de torneo
	 * @return devuelve una lista de strings con esos datos
	 */
	public ObservableList<String> listType(){
		ObservableList<String> listType = FXCollections.observableArrayList();
		String query1="select * from panenka_db.contests_contesttype;";
		ResultSet type1= driverDB.runQuery(query1);
		try {
			while(type1.next()){
				 listType.add(type1.getString("contest_type"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listType;
	}
	/**
	 * es para obtener para rellenar el combobox de modos de torneo
	 * @return devuelve una lista de strings con esos datos
	 */
	public ObservableList<String> listMode(){
		ObservableList<String> listMode = FXCollections.observableArrayList();
		String query2="select * from panenka_db.contests_gamemode;";
		ResultSet type2= driverDB.runQuery(query2);
		try {
			while(type2.next()){
				 listMode.add(type2.getString("game_mode"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listMode;
	}
	/**
	 * es para obtener para rellenar el combobox del día del torneo
	 * @return devuelve una lista de strings con esos datos
	 */
	public ObservableList<String> listMatchday(){
		ObservableList<String> listMatchDay = FXCollections.observableArrayList();
		String query3="select * from panenka_db.contests_matchday;";
		ResultSet type3= driverDB.runQuery(query3);
		try {
			while(type3.next()){
				listMatchDay.add(type3.getString("matchday"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listMatchDay;
	}
	/**
	 * necesitamos el id del tipo de torneo elegido
	 * @param name tipo de torneo seleccionado en el combobox
	 * @return el id del tipo
	 */
	public int getMatchday_id(String name){
		int id1 = 0;
		String query3="select * from panenka_db.contests_matchday where matchday= '" + name+"';";
		ResultSet type3= driverDB.runQuery(query3);
		try {
			while(type3.next()){
				id1= Integer.parseInt(type3.getString("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id1;
	}
	/**
	 * necesitamos el id del modo de torneo elegido
	 * @param name modo de torneo seleccionado en el combobox
	 * @return el id del modo
	 */
	public int getGameMode_id(String name){
		int id1 = 0;
		String query3="select * from panenka_db.contests_gamemode where game_mode= '" + name+"';";
		ResultSet type3= driverDB.runQuery(query3);
		try {
			while(type3.next()){
				id1= Integer.parseInt(type3.getString("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id1;
	}
	/**
	 * necesitamos el id del dia de torneo elegido
	 * @param name dia de torneo seleccionado en el combobox
	 * @return el id del dia
	 */
	public int getContestType_id(String name){
		int id1 = 0;
		String query3="select * from panenka_db.contests_contesttype where contest_type= '" + name+"';";
		ResultSet type3= driverDB.runQuery(query3);
		try {
			while(type3.next()){
				id1= Integer.parseInt(type3.getString("id"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return id1;
	}
	
}
