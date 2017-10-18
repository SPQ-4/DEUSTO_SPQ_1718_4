package SPQ_1718_4.Proyecto_Excepciones;

public class validarFecha extends Exception {
	/**
	 * la fecha tiene que estar en formato DD-MM-YYYY
	 */
	
	public validarFecha(){
		super("la fecha tiene que estar en formato DD-MM-YYYY");
	}
}
