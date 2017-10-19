package SPQ_1718_4.Proyecto.Exceptions;

public class validarNombreTorneo extends Exception {
	/**
	 * el nombre del torneo ya está cogido
	 */
	
	public validarNombreTorneo(){
		super("ese nombre de torneo ya está cogido");
	}
}
