package controllers;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import db.MySQLDriver;

public class ControllerUserTest {
	private MySQLDriver driverDB;
	private ControllerUser controller;
	@Before
	public void initialize(){
		controller=new ControllerUser();
	}
	@Test
	public void setUsers() {
		int i=controller.setUsersToList();
		assertEquals(0,i);
	}
}
