package controllers;
import static org.junit.Assert.assertEquals;

import java.io.IOException;
import java.net.URL;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import db.MySQLDriver;
import javafx.collections.FXCollections;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import models.Main2;
import models.Usuario;
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
	@Test
	public void test2() {
		Usuario a=new Usuario("Asier", 1000);
		a.setNombre("Pablo");
		assertEquals("Pablo",a.getNombre());
	}
	
}
