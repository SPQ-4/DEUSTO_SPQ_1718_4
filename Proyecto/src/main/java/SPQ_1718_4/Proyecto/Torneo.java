package SPQ_1718_4.Proyecto;

import java.util.Date;
import java.util.ArrayList;

public class Torneo {

	private int idTorneo;
	private String nombre, pais;
	private Date fecha;
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	
	public Torneo() {
		
	}
	
	

	public Torneo(int idTorneo, String nombre, String pais, Date fecha) {
		super();
		this.idTorneo = idTorneo;
		this.nombre = nombre;
		this.pais = pais;
		this.fecha = fecha;
	}

	public void addJugador(Jugador jugador) {
		jugadores.add(jugador);
	}
	public int getIdTorneo() {
		return idTorneo;
	}

	public void setIdTorneo(int idTorneo) {
		this.idTorneo = idTorneo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getPais() {
		return pais;
	}

	public void setPais(String pais) {
		this.pais = pais;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public ArrayList<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(ArrayList<Jugador> jugadores) {
		this.jugadores = jugadores;
	}
	
}
