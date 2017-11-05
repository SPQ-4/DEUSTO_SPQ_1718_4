package controllers;
import java.time.LocalDate;
import java.util.Date;

import javafx.beans.property.*;

public class Usuario {
	private StringProperty nombre;
	private StringProperty email;
	private IntegerProperty edad;
	//private DateProperty fecha_registro;
	private SimpleDoubleProperty saldo;
	private int id;
	private SimpleDoubleProperty dineroIngresado;
	private ObjectProperty<Date> fecha_registro;
	
	
	
	public Usuario(String email, double saldo, double dineroIngresado,Date fecha_registro) {
		
		this.email=new SimpleStringProperty(email);
		this.saldo=new SimpleDoubleProperty(saldo);
		this.dineroIngresado= new SimpleDoubleProperty(dineroIngresado);
		this.fecha_registro= new SimpleObjectProperty<Date>(fecha_registro);
	}



	public Usuario(String nombre, int edad) {
	 System.out.println("Se ha cambiado");
       this.nombre = new SimpleStringProperty(nombre);
       this.edad = new SimpleIntegerProperty(edad);
}
	public Usuario(String nombre, String email) {
	       this.nombre = new SimpleStringProperty(nombre);
	       this.email = new SimpleStringProperty(email);
	}
	public String getEmail() {
		return email.get();
	}
	public void setEmail(StringProperty email) {
		this.email = email;
	}
	
	 public Double getSaldo() {
		return saldo.get();
	}
	public void setSaldo(double saldo) {
		this.saldo.set(saldo);
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}

	public void setEdad(IntegerProperty edad) {
		this.edad = edad;
	}

	 public String getNombre() {
		 
		 return this.nombre.get();
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

	public double getDineroIngresado() {
		return dineroIngresado.get();
	}

	public void setDineroIngresado(double dineroIngresado) {
		this.dineroIngresado.set(dineroIngresado);
	}
	
	public Date getFecha_registro() {
		return fecha_registro.get();
	}
	
	public void setFecha_registro(Date fecha_registro) {
		this.fecha_registro.set(fecha_registro);
	}
	 
	 
}
