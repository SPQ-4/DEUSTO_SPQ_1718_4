package controllers;
import javafx.beans.property.StringProperty;
import javafx.beans.property.*;

public class Usuario {
	private StringProperty nombre;
	private IntegerProperty edad;
	 public Usuario(String nombre, int edad) {
		 System.out.println("Se ha cambiado");
	        this.nombre = new SimpleStringProperty(nombre);
	        this.edad = new SimpleIntegerProperty(edad);
	 }
	 public StringProperty getNombre() {
		 
		 return this.nombre;
	 }
	 public IntegerProperty getEdad() {
		 return this.edad;
	 }
	 public void setNombre(String nombre) {
		 this.nombre.set(nombre);
	 }
	 public void setEdad(int edad) {
		 this.edad.set(edad);
	 }
}
