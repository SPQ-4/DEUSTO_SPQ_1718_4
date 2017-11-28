package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.Date;

import javafx.scene.control.DatePicker;

public class NewContestBD {
	
	private MySQLDriver driverDB;
	
	public NewContestBD(){
		driverDB= new MySQLDriver();
	}
	
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
	
	public void newContest(String contest, String password, LocalDate openDate, LocalDate closeDate,
	int min, int max, int fee, String descrip){
		Date open= new Date(openDate.getYear()-1900,openDate.getMonthValue()-1,openDate.getDayOfMonth());
		Date close= new Date(openDate.getYear()-1900,openDate.getMonthValue()-1,openDate.getDayOfMonth());
			String query = "INSERT INTO panenka_db.contests_contest (created_date, title, password, "
					+ "created_by_admin, open_date, close_date, minimum_participants, "
					+ "maximum_participants, entry_fee, description, contest_type_id, game_mode_id, "
					+ "matchday_id)"
					+ "VALUES (NOW(), '"+contest+"', '"+password+"', '1', '"
					+ new java.sql.Date(open.getTime())+"','"+new java.sql.Date(close.getTime())+"',"+min+","+max+","+fee+""
					+ ",'"+descrip+"','1','1','1');";
		int result = driverDB.runUpdate(query);
	}
}
