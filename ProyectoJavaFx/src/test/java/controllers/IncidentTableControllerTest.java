package controllers;

import org.junit.Before;
import org.junit.Test;

import javafx.collections.ObservableList;
import javafx.scene.control.TableView;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.*;

import java.util.ArrayList;

import models.Incident;
import models.IncidentManager;

public class IncidentTableControllerTest {
private IncidentsTablesController controller;
ArrayList<Incident> incidents;
IncidentManager im;
Incident i1;
Incident i2;
Incident i3;
Incident i4;
	@Before public void initialize() {
		controller = new IncidentsTablesController();
		incidents = new ArrayList<Incident>();
		i1 = new Incident("juangara", "queja", "No puedo fichar");
		i2 = new Incident("paula", "mal funcionamiento", "Me sale error 404");
		i3 = new Incident("asier", "queja", "el dinero de mi cuenta no es correcto");
		i4 = new Incident("javi", "queja", "el dinero de mi cuenta no es correcto");
		i4.setRespuesta("Dinero reestablecido en cuenta");
		i4.setGestionada(true);
		incidents.add(i1);
		incidents.add(i2);
		incidents.add(i3);
		incidents.add(i4);
		im = mock(IncidentManager.class);
		when(im.getIncidents()).thenReturn(incidents);
		when(im.getAnswerFromUser()).thenReturn("Tu dinero ha vuelto a tu cuenta");
	}
	
	@Test public void testllenarTablasGestionadas() {
		controller.llenarTablas(im.getIncidents());
		assertEquals(1,controller.data1.size());
	}
	
	@Test public void testllenarTablasNoGestionadas() {
		controller.llenarTablas(im.getIncidents());
		assertEquals(3,controller.data.size());
	}
	
	@Test public void testAnswerContent() {
		controller.answer(i1, im.getAnswerFromUser());
		assertEquals("Tu dinero ha vuelto a tu cuenta", i1.getRespuesta());
	}
	
	@Test public void testAnswerState() {
		controller.answer(i1, im.getAnswerFromUser());
		assertEquals(true, i1.getGestionada());
	}


}
