package models;

import java.time.LocalDate;
import db.NewContestBD;


public class NewContestMethods {

	private NewContestBD relationBD;
	
	public NewContestMethods(){
		relationBD= new NewContestBD();
	}
	
	/**
	 * En este método comprobamos que el nombre que hemos metido no existe para eso llamamos al 
	 * metodo correspondiente de la BD
	 * @param name nombre que le hemos dado al torneo
	 * @return devolvemos true si está cogido, false si está libre
	 */
	public boolean testContestName(String name){
		if(relationBD.testName(name)){
				return true;
			}else{
				return false;
			}
	}
	/**
	 * comprobamos que la fecha de open es anterior a la fecha de close. no nos hace falta comprobar
	 * que sea una fecha porque al meterlo por datepicker solo te deja elegir una fecha real
	 * @param open fecha de apertura del torneo
	 * @param close fecha  de cierre del torneo
	 * @return devolvemos true si las fechas son incorrectas, false si está bien
	 */
	public boolean checkDates(LocalDate open, LocalDate close){
		if(open.isAfter(close)){
			return true;
		}
		return false;
	}
	/**
	 * para saber si el string que metemos es un numero o no
	 * @param number string con el supuesto numero
	 * @return devolvemos true cuando no es un numero, y false si es un numero
	 */
	public boolean checkNumber(String number){
		boolean a=false;
		int num=0;
		try{
		num=Integer.parseInt(number);
		} catch (NumberFormatException nfe) {
			a=true;
		}
	    return a;
	}
	
	/**
	 * Comprobamos que el numero  maximo es mayor que el minimo
	 * @param max
	 * @param min
	 * @return devuelve true si minimo es menor que maximo
	 */
	public boolean checkMaxMin(int maxim, int minim){	
			if(maxim>minim){
				return false;
			}else{
			return true;
			}
	}
}
