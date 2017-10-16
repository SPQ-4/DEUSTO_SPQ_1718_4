package Main;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BasicConfigurator.configure();
		Logger log=Logger.getLogger(Main.class);
		log.debug("Funciona");
	}

}
