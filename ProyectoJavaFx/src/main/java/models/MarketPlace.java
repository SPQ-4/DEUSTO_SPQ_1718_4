package models;

import java.util.ArrayList;
/**
 * Clase para gestionar un mercado en concreto, que esté formado por un conjunto de jugadores
 * y que utiliza un servicio externo para calcular el valor y los puntos del mercado
 * @author Grupo4
 *
 */
public class MarketPlace{
	private ArrayList<Player>playersList;
	private String marketName;
	private double marketTotalValue;
	private double points;
	MarketServiceInterface marketService;
	/**
	 * Constructor MarketPlace
	 * @param playersList lista de jugadores que se van a tomar de referencia para puntuar
	 */
	public MarketPlace(ArrayList<Player>playersList) {
		marketService=new MarketServiceExt();
		setPlayersList(playersList);
	}
	/**
	 * Método para especificar un servicio de market para que lo use. 
	 * @param marketService objeto que tiene que implementar la interfaz MarketServiceInterface
	 */
	public void setMarketServiceExt(MarketServiceInterface marketService) {
		this.marketService=marketService;
	}
	public void setPlayersList(ArrayList<Player>playersList) {
		this.playersList=playersList;
	}
	public ArrayList<Player> getPlayersList() {
		return this.playersList;
	}
	/**
	 * Método que cakcula el valor de mercado de los jugadores de un mercado en concreto basándose
	 * en los puntos totales y en el valor que el mercado debería tener en función de esos puntos
	 */
	public void setMarketValuePlayers() {
		setMarketTotalPoints();
		setMarketTotalValue();
		this.playersList=setValue();
	}
	public void setMarketTotalPoints() {
		this.points=marketService.setMarketTotalPoints(this.playersList);
	}
	public void setMarketTotalValue() {
		this.marketTotalValue=marketService.setMarketTotalValue(this.playersList);
	}
	
	public ArrayList<Player> setValue() {
//		Para enseñar el JAVAVM porque sino setValue no lo detecta
//		try {
//			Thread.sleep(100);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		for(int i=0;i<playersList.size();i++) {
			playersList.get(i).setPlayer_value(playersList.get(i).getPlayer_points()/points*marketTotalValue);
		}
		return playersList;
	}
	public ArrayList<Player> setPoints(ArrayList<Player>playersList) {
		return playersList;
	}
	public double getMarketValue() {
		// TODO Auto-generated method stub
		return this.marketTotalValue;
	}
	public double getPoints() {
		// TODO Auto-generated method stub
		return this.points;
	}
	
	public ArrayList<Player> setValueNoOptimize() {
		// TODO Auto-generated method stub
		for(int i=0;i<playersList.size();i++) {
			setMarketTotalPoints();
			setMarketTotalValue();
			playersList.get(i).setPlayer_value(playersList.get(i).getPlayer_points()/points*marketTotalValue);
		}
		return playersList;
	}
	
}
