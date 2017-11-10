import static org.junit.Assert.*;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.junit.Before;
import org.junit.Test;

import controllers.ControllerGeneral;
import db.MySQLDriver;


public class BDTest {
	private MySQLDriver driverDB;
	private ControllerGeneral controller;
	@Before
	public void initialize(){
		controller=new ControllerGeneral();
	}
	@Test
	public void contestsTypestest() {
		int tipo1=0;
		int tipo2=0;
		try {
			tipo1= controller.getValueContestType(1).intValue();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		assertEquals(1,tipo1);
		try {
			tipo2= controller.getValueContestType(2).intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(2,tipo2);
	}
	@Test
	public void obtainMonthKPITest() {
		int playedRate=0;
		try {
			playedRate= controller.playedRate().intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1,playedRate);
		int revenues=0;
		try {
			playedRate= controller.revenuesByMonth().intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(0,revenues);
		int byUsers=0;
		try {
			byUsers= controller.byUsers().intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(3,byUsers);
		int usersMonth=0;
		try {
			usersMonth= controller.usersMonth().intValue();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		assertEquals(1,usersMonth);
	}
	
}
