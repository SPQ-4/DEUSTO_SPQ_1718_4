package models;

import java.util.ArrayList;

public class MarketServiceExt implements MarketServiceInterface {
	private double [] pointValue= {0.5,0.4,0.3,0.2};
	public MarketServiceExt() {
		System.out.println("Servicio Externo");
	}
	@Override
	public double setMarketTotalPoints(ArrayList<Player> playersList) {
		double parcial=0;
		for(int i=0;i<playersList.size();i++) {
			parcial=parcial+playersList.get(i).getPlayer_points();			
		}
		return parcial;
	}

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
