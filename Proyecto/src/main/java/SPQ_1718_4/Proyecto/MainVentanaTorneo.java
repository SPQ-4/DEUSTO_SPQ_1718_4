package SPQ_1718_4.Proyecto;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class MainVentanaTorneo {

	public static void main(String[] args) throws ParseException {
		// TODO Auto-generated method stub
		SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd-MM-yyyy");
		Date fecha = DATE_FORMAT.parse("18-10-2017");
		Jugador jugador1 = new Jugador("Juan", "juangara", "juan.garayalde@hotmail.com");
		Jugador jugador2 = new Jugador("Asier", "asier", "asier@hotmail.com");
		Jugador jugador3 = new Jugador("Paula", "paulaue", "paula@hotmail.com");
		
		Torneo torneo = new Torneo(2, "Torneo Prueba", "España", fecha);
		torneo.addJugador(jugador1);
		torneo.addJugador(jugador2);
		torneo.addJugador(jugador3);
		VentanaVerTorneo ventanaTorneo = new VentanaVerTorneo(torneo);
		
	}

}
