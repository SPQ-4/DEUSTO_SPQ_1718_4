package models;

import db.MySQLDriver;
import db.PlayersBD;

public class MainJavaFx {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		/**
		 * Main especifico para hacer la prueba de Java VM, ya que lo lanza sino siempre a través de JAVAFX
		 * Hay que descomentar de MarketPlace el método setValue para poder hacer las comparaciones.
		 */
		PlayersBD a=new PlayersBD(new MySQLDriver());
		MarketPlace market=new MarketPlace(a.getPlayers());
		for(int i=0;i<1000;i++) {
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			market.setValue();
			market.setValueNoOptimize();
		}
		
	}

}
