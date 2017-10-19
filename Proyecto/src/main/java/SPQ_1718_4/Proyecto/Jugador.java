package SPQ_1718_4.Proyecto;

public class Jugador {

	private String nombre, usuario, email;
	
	
	public Jugador() {
		
	}


	public Jugador(String nombre, String usuario, String email) {
		super();
		this.nombre = nombre;
		this.usuario = usuario;
		this.email = email;
	}


	public String getNombre() {
		return nombre;
	}


	public void setNombre(String nombre) {
		this.nombre = nombre;
	}


	public String getUsuario() {
		return usuario;
	}


	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}
	
}
