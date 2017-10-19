package Logica_DashBoard;

import java.util.Date;

public class Torneo {
	private String name;
	private int premio;
	private Date date;
	private int capacidad;
	private int precio;
	private int estado;
	public Torneo(String name,int premio,Date date,int capacidad,int precio,int estado) {
		this.name=name;
		this.premio=premio;
		this.date=date;
		this.capacidad=capacidad;
		this.precio=precio;
		this.estado=estado;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getPremio() {
		return premio;
	}
	public void setPremio(int premio) {
		this.premio = premio;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public int getCapacidad() {
		return capacidad;
	}
	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}
	public int getPrecio() {
		return precio;
	}
	public void setPrecio(int precio) {
		this.precio = precio;
	}
	public int getEstado() {
		return estado;
	}
	public void setEstado(int estado) {
		this.estado = estado;
	}
	public Object getAtributo(int col) {
		// TODO Auto-generated method stub
		Object[] lista= {this.name,this.premio,this.date,this.capacidad,this.precio,this.estado};
		return lista[col];
	}
	
	
}
