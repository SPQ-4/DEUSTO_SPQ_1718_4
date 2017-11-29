package controllers;
import static org.junit.Assert.*;

import java.time.LocalDate;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import models.NewContestMethods;

public class NewContestMethodsTest {
	private NewContestMethods metodos;
	@Before
	public void initialize(){
		metodos=new NewContestMethods();
	}
	@Test
	public void contestsNametest() {
		boolean prueba;
		prueba= metodos.testContestName("Prueba");
		assertEquals(true,prueba);
		prueba= metodos.testContestName("Prueba");
		assertEquals(false,prueba);
	}
	@Test
	public void Datetest() {
		boolean prueba;
		
		LocalDate open = LocalDate.of(2017,11,11);
		LocalDate close = LocalDate.of(2017,11,10);	
		prueba= metodos.checkDates(open, close);
		assertEquals(true,prueba);
		
		open = LocalDate.of(2017,11,11);
		close = LocalDate.of(2017,11,15);		
		prueba= metodos.checkDates(open, close);
		prueba= metodos.testContestName("Prueba");
		assertEquals(false,prueba);
	}
	public void Numbertest() {
		boolean prueba;
		
		prueba= metodos.checkNumber("s");
		assertEquals(true,prueba);
		
		prueba= metodos.checkNumber("3");
		prueba= metodos.testContestName("Prueba");
		assertEquals(false,prueba);
	}
	public void MaxMintest() {
		boolean prueba;
		
		prueba= metodos.checkMaxMin(1, 2);
		assertEquals(true,prueba);
		
		prueba= metodos.checkMaxMin(2, 1);
		prueba= metodos.testContestName("Prueba");
		assertEquals(false,prueba);
	}
}
