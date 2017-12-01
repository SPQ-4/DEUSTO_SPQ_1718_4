package models;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Incident {
	
	private StringProperty usuario, tipo, comentario, respuesta;
	private boolean gestionada;
	
	public Incident(String usuario, String tipo, String comentario) {
		this.usuario = new SimpleStringProperty(usuario);
		this.tipo = new SimpleStringProperty(tipo);
		this.comentario = new SimpleStringProperty(comentario);
		this.gestionada = false;
		
	}
	
	public String getUsuario() {
		return this.usuario.get();
	}
	
	public String getTipo() {
		return this.tipo.get();
	}
	
	public String getComentario() {
		return this.comentario.get();
	}
	
	public String getRespuesta() {
		return this.respuesta.get();
	}
	
	public void setRespuesta(String respuesta) {
		this.respuesta = new SimpleStringProperty(respuesta);
	}
	
	public boolean getGestionada() {
		return this.gestionada;
	}
	
	public void setGestionada(boolean gestionada) {
		this.gestionada = gestionada;
	}
	
	

}
