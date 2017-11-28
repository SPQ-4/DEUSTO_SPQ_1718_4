package models;

import java.time.LocalDate;
import db.NewContestBD;


public class NewContestMethods {

	private NewContestBD relationBD;
	
	public NewContestMethods(){
		relationBD= new NewContestBD();
	}
	
	/**
	 * En este método comprobamos que el nombre que hemos metido no existe ya en la BD, si ya existe
	 * se pone en rojo el background
	 * @param name nombre que le hemos dado al torneo
	 * @return devolvemos si está cogido o no
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
	 * @param dateOpen fecha de apertura del torneo
	 * @param dateClose fecha  de cierre del torneo
	 * @return devolvemos si la de cierre es posterior o no
	 */
	public boolean checkDates(LocalDate open, LocalDate close){
		if(open.isAfter(close)){
			return true;
		}
		return false;
	}
	
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
	 * Comprobamos que el numero de participantes maximo es mayor que el minimo
	 * @param max
	 * @param min
	 * @return
	 */
	public boolean checkMaxMin(int maxim, int minim){	
			if(maxim>minim){
				return false;
			}else{
			return true;
			}
	}
	/**
	 * este método llama a la BD para crear un torneo
	 */
}
