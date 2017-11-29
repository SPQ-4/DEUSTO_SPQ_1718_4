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

import javafx.collections.ObservableList;
import models.Player;

public class NewContestBDTest {
	private static  NewContestBD bd;
		@BeforeClass
		public  static void initialize() {
			bd=new NewContestBD();
		}

		@Test
		public void testNametest(){
			boolean prueba=false;
			prueba= bd.testName("Prueba");
			assertEquals(true,prueba);
	
			prueba= bd.testName("wedh");
			assertEquals(false,prueba);
		}
		@Test
		public void listTypetest(){
			ObservableList<String> listType= bd.listType();
			assertEquals(2,listType.size());
			assertNotEquals(1,listType.size());
		}
		@Test
		public void listModetest(){
			ObservableList<String> listType= bd.listMode();
			assertEquals(2,listType.size());
			assertNotEquals(1,listType.size());
		}
		@Test
		public void listMatchdaytest(){
			ObservableList<String> listType= bd.listMatchday();
			assertEquals(3,listType.size());
			assertNotEquals(2,listType.size());
		}
		@Test
		public void idTypetest(){
			int a= bd.getContestType_id("Public");
			assertEquals(1,a);
			assertNotEquals(2,a);
		}
		@Test
		public void idModetest(){
			int a= bd.getGameMode_id("Classic");
			assertEquals(1,a);
			assertNotEquals(2,a);
		}
		@Test
		public void idMatchdaytest(){
			int a= bd.getMatchday_id("Matchday1");
			assertEquals(1,a);
			assertNotEquals(2,a);
		}
}
