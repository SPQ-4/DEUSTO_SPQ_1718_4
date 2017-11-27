package models;

import java.util.ArrayList;

public class MarketPlace {
	private ArrayList<Player>playersList;
	private String marketName;
	private double marketTotalValue;
	private double points;
	private double [] pointValue= {0.5,0.4,0.3,0.2};
	public MarketPlace(ArrayList<Player>playersList) {
		setPlayersList(playersList);
	}
	public void setPlayersList(ArrayList<Player>playersList) {
		this.playersList=playersList;
	}
	public void setMarketValuePlayers() {
		playersList.get(0).setPlayer_nationality("FUNCIONA");
		setMarketTotalPoints();
		setMarketTotalValue();
		setPoints();
	}
	public void setMarketTotalPoints() {
		double parcial=0;
		for(int i=0;i<this.playersList.size();i++) {
			parcial=parcial+this.playersList.get(i).getPlayer_points();			
		}
		this.points=parcial;
	}
	public void setMarketTotalValue() {
		double parcial=0;
		for(int i=0;i<this.playersList.size();i++) {
			switch(this.playersList.get(i).getPlayer_position()) {
			case "G":parcial=parcial+this.playersList.get(i).getPlayer_points()*pointValue[0];break;
			case "D":parcial=parcial+this.playersList.get(i).getPlayer_points()*pointValue[1];break;
			case "M":parcial=parcial+this.playersList.get(i).getPlayer_points()*pointValue[2];break;
			case "F":parcial=parcial+this.playersList.get(i).getPlayer_points()*pointValue[3];break;
			default:;
			}
		}
		marketTotalValue=parcial;
	}
	public void setPoints() {
		for(int i=0;i<this.playersList.size();i++) {
			playersList.get(i).setPlayer_value(playersList.get(i).getPlayer_points()/this.points*marketTotalValue);
		}
	}
	public void getPoints() {
		
	}
	
}
