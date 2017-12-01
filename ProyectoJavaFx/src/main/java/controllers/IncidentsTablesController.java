package controllers;


import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Incident;

public class IncidentsTablesController {
	@FXML protected TableView<Incident> noGestionadas;
	@FXML protected TableView<Incident> gestionadas;
	@FXML private TableView<Incident> incidentView;
	@FXML private TextField answer;
	@FXML private Button button;
	ObservableList<Incident> data = FXCollections.observableArrayList();
	 ObservableList<Incident> data1 = FXCollections.observableArrayList();
	public IncidentsTablesController() {
		super();
		//this.user = user;
		
	}
	
	public void initialize() {
		
	}
	
	public void initializeData() {
		data = noGestionadas.getItems();
		data1 = gestionadas.getItems();
	}


	
	
	public void llenarTablas(ArrayList<Incident> incidents) {
//		 ObservableList<Incident> data = noGestionadas;
//		 ObservableList<Incident> data1 = gestionadas;
		
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
	
	public void answerIncident() {
		answer(incidentView.getItems().get(0),answer.getText());
	}
	
	public Incident answer(Incident incident, String answer) {
		incident.setRespuesta(answer);
		incident.setGestionada(true);
		return incident;
	}
}
