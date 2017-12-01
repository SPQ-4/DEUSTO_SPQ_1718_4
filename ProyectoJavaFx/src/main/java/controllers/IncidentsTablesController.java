package controllers;


import java.util.ArrayList;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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
	@FXML private TableView<Incident> incidentView;
	@FXML private TextField answer;
	@FXML private Button button;
	
	
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
	
	public void selectIncident(Incident incident) {
		ObservableList<Incident> data = incidentView.getItems();
		data.add(incident);
		if(incident.getGestionada()) {
			button.setVisible(false);
			answer.setText(incident.getRespuesta());
			answer.setEditable(false);
			
		}
	}
	
	public void answer() {
		ObservableList<Incident> data = incidentView.getItems();
		data.get(0).setRespuesta(answer.getText());
		data.get(0).setGestionada(true);
		System.out.println(data.get(0).getRespuesta());
	}
}
