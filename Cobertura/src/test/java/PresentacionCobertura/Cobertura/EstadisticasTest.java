package PresentacionCobertura.Cobertura;

import static org.junit.Assert.*;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class EstadisticasTest{
	Estadisticas objeto;
	@Before
	public void initialize() {
		objeto=new Estadisticas();
		objeto.importarPuntuaciones(new ArrayList<Integer>());
		for(int i=1;i<6;i++) {
			objeto.getListaPuntuaciones().add(i);
		}
	}
	@Test
	public void test() {
		assertEquals(15,objeto.puntuacionTotal());
		//assertEquals("denegado",resultado2);
	}

}
