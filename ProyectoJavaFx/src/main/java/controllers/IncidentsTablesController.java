package controllers;


import java.util.ArrayList;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import models.Incident;

/** Controller que gestiona todo lo referente a la visualización de las incidencias*/

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


	
	/** este método se encarga de clasificar un array de incidencias en dos, gestionadas y no gestionadas
	 * después de clasificarlos los carga en los observables que cargarán la información en pantalla
	 * @param incidents incidencias recogidas de la base de datos
	 */
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
	
	/**
	 * se selecciona la incidencia que se quiere mostrar y en función de si ha sido gestionada o no deja que el administrador registre una respuesta
	 * @param incident incidencia que ha sido seleccionada por el usuario
	 */
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
	
	/**
	 * recoger la respuesta que ha dado el administrador a una incidencia
	 * @param incident incidencia en la que se va a recoger la respuesta
	 * @param answer respuesta del administrador
	 * @return devuelve la incidencia actualizada
	 */
	public Incident answer(Incident incident, String answer) {
		incident.setRespuesta(answer);
		incident.setGestionada(true);
		return incident;
	}
}
