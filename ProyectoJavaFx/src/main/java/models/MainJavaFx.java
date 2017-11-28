package models;

import db.MySQLDriver;
import db.PlayersBD;

public class MainJavaFx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for(int i=0;i<1000;i++) {
			PlayersBD a=new PlayersBD(new MySQLDriver());
			MarketPlace market=new MarketPlace(a.getPlayers());
			market.setMarketValuePlayers();
		}
		
	}

}
