package db;
import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.util.ArrayList;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import models.Player;

public class PlayersDBTest {
	@Rule public ContiPerfRule i=new ContiPerfRule();
	static PlayersBD mysql;
		@BeforeClass
		public static void initialize() {
			mysql=new PlayersBD(new MySQLDriver());
		}
//		@Test 
//		@PerfTest(invocations=100,threads=20)
//		@Required(max=20000,average=3000)
//		public void prueba() throws InterruptedException{
//			mysql.getPlayers();
//		}
		@Test(expected= IndexOutOfBoundsException.class)
		public void prueba2() throws InterruptedException{
			ArrayList<Player>players=mysql.getPlayers();
			assertEquals("Iñaki Williams",players.get(0).getPlayerShirt());
			players.get(players.size()+1);
		}
		
}
