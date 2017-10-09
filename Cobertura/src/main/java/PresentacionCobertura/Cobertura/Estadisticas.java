package PresentacionCobertura.Cobertura;

import java.util.ArrayList;

public class Estadisticas {
	private ArrayList<Integer>listaPuntuaciones;
	public Estadisticas() {
		listaPuntuaciones=new ArrayList<Integer>();
	}
	public void importarPuntuaciones(ArrayList<Integer>datos) {
		listaPuntuaciones=datos;
	}
	public ArrayList<Integer>getListaPuntuaciones(){
		return listaPuntuaciones;
	}
	public int puntuacionTotal() {
		int total=0;
		for(int i=0;i<listaPuntuaciones.size();i++) {
			total=total+listaPuntuaciones.get(i);
		}
		return total;
	}
}
