package controllers;

import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;
import models.Incident;

public class IncidentsTablesController {
	public IncidentsTablesController() {
		super();
		//this.user = user;
		
	}
	
	public void initialize() {
		
	}

	@FXML private TableView<Incident> noGestionadas;
	@FXML private TableView<Incident> gestionadas;
	
	
	public void llenarTablas(ArrayList<Incident> incidents) {
		
		
		 ObservableList<Incident> data = noGestionadas.getItems();
		 ObservableList<Incident> data1 = gestionadas.getItems();
		 for(Incident incident:incidents) {
			 if(incident.getGestionada()) {
				data1.add(incident);
			 }
			 else {
				 data.add(incident);
			 }
		 }
		 
	}
}
