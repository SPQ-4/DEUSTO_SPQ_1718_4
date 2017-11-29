package models;

import java.util.ArrayList;
/**
 * Clase para implementar el servicio externo que calcula puntos y valor de mercado
 * @author Grupo4
 *
 */
public class MarketServiceExt implements MarketServiceInterface {
	private double [] pointValue= {0.5,0.4,0.3,0.2};
	public MarketServiceExt() {
		System.out.println("Servicio Externo");
	}
	/**
	 * Método para calcular los puntos totales de un mercado.
	 * @param playersList recibe la lista de jugadores que forman un mercado
	 * @return devuelve el total de puntos del mercado
	 */
	@Override
	public double setMarketTotalPoints(ArrayList<Player> playersList) {
		double parcial=0;
		for(int i=0;i<playersList.size();i++) {
			parcial=parcial+playersList.get(i).getPlayer_points();			
		}
		return parcial;
	}
	/**
	 * Método para calcular el valor de un mercado. Pondera el valor en función de la posición y los puntos que se haya
	 * obtenido jugando en esa posición
	 * @param playersList recibe la lista de jugadores que forman un mercado
	 * @return devuelve el valor monetario del mercado
	 */
	@Override
	public double setMarketTotalValue(ArrayList<Player> playersList) {
		double parcial=0;
		for(int i=0;i<playersList.size();i++) {
			switch(playersList.get(i).getPlayer_position()) {
			case "G":parcial=parcial+playersList.get(i).getPlayer_points()*pointValue[0];break;
			case "D":parcial=parcial+playersList.get(i).getPlayer_points()*pointValue[1];break;
			case "M":parcial=parcial+playersList.get(i).getPlayer_points()*pointValue[2];break;
			case "F":parcial=parcial+playersList.get(i).getPlayer_points()*pointValue[3];break;
			default:;
			}
		}
		return parcial;
	}

}
