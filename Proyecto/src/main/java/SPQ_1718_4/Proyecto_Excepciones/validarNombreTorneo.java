package SPQ_1718_4.Proyecto_Excepciones;

public class validarNombreTorneo extends Exception {
	/**
	 * el nombre del torneo ya está cogido
	 */
	
	public validarNombreTorneo(){
		super("ese nombre de torneo ya está cogido");
	}
}
