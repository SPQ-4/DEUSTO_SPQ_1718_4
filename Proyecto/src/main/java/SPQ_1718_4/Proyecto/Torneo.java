package SPQ_1718_4.Proyecto;

import java.util.Date;
import java.util.ArrayList;

public class Torneo {

	private int idTorneo;
	private String nombre;
	private int precio;
	private int premio;
	private Date fecha;
	private ArrayList<Jugador> jugadores = new ArrayList<Jugador>();
	private String[]columnas= {"ID","NOMBRE","DATE"};
	public Torneo(int idTorneo, String nombre, Date fecha,int precio,int premio) {
		super();
		this.idTorneo = idTorneo;
		this.nombre = nombre;
		this.fecha = fecha;
		this.precio=precio;
		this.premio=premio;
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
	public String getNombreColumna(int i) {
		return columnas[i];
	}
	public int getNumeroColumnas() {
		return columnas.length;
	}
	//PAra devolver el atributo concreto
	public Object getAtributo(int i) {
		Object [] atributos= {this.idTorneo,this.nombre,this.fecha,this.precio,this.premio};
		return atributos[i];
	}
}
