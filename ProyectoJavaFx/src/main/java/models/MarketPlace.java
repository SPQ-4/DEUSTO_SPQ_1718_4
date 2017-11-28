package models;

import java.util.ArrayList;

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
	public void setMarketServiceExt(MarketServiceInterface marketService) {
		this.marketService=marketService;
	}
	public void setPlayersList(ArrayList<Player>playersList) {
		this.playersList=playersList;
	}
	public ArrayList<Player> getPlayersList() {
		return this.playersList;
	}
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
	
}
