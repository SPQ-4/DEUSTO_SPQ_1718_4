package db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import org.apache.log4j.Logger;
import controllers.ControllerGeneral;
import controllers.Usuario;
import models.Main;
import models.Player;

public class PlayersBD {
	private MySQLDriver driver;
	public static Logger logger=Logger.getLogger(ControllerGeneral.class);
	public PlayersBD(MySQLDriver driver) {
		this.driver=driver;
	}
	public ArrayList<Player>getPlayers(){
		ArrayList<Player>playerList=new ArrayList<Player>();
		String query="select * from panenka_db.players_player, panenka_db.contests_club where panenka_db.contests_club.id=panenka_db.players_player.id_club";
		ResultSet type1= driver.runQuery(query);
		try {
			while(type1.next()){
				Player player=new Player(type1.getString("player_shirt"),type1.getString("player_position_category"),type1.getString("club_name"),type1.getString("id_country"),type1.getDouble("player_value"));
				player.setPlayer_points((double)10);
				playerList.add(player);	
			}		
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			logger.error(e.getMessage());
			//e.printStackTrace();
			return null;
		}	
		return playerList;
	}
	public static void main(String[] args) {
		PlayersBD a=new PlayersBD(new MySQLDriver());
		ArrayList<Player>b=a.getPlayers();
		System.out.println(b.get(0).toString());
	}
}

