package SPQ_1718_4.Proyecto_Excepciones;

public class validarMinMax extends Exception {
	/**
	 * el numero minimo es mayor que el numero máximo
	 */
	
	public validarMinMax(){
		super("El numero minimo es más pequeño que el maximo");
	}
}
