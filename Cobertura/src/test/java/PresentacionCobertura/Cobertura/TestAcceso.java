package PresentacionCobertura.Cobertura;

import org.junit.Test;
import junit.framework.TestCase;

public class TestAcceso extends TestCase {
	@Test
	public void testComprobar() {
		Calculo calculo=new Calculo();
		String resultado=calculo.comprobar("Paco","password");
		assertEquals("permitido",resultado);
		
		String resultado2=calculo.comprobar("Paco","pass");
		assertEquals("denegado",resultado2);
		
	}
}
