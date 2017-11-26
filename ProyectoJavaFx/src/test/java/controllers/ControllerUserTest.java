package controllers;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import db.MySQLDriver;
import javafx.collections.FXCollections;
public class ControllerUserTest {
	private ControllerUser a; //Utilizar Mockito
	@Before
	public void initialize() {
		a=new ControllerUser();
	}
	@Test
	public void test1() {
		a.setDBDriver(new MySQLDriver());
		a.UsersData=FXCollections.observableArrayList();
		int i=a.setUsersToList();
		assertEquals(0,i);
	}
}
