package Logica_DashBoard;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;

import SPQ_1718_4.Proyecto.db.MySQLDriver;

public class ObtenerDatos {
	private MySQLDriver driver;
	public ObtenerDatos() {
		//driver=new MySQLDriver();
	}
	public ArrayList<Torneo> obtenerTorneos() {
		// TODO Auto-generated method stub
		ArrayList<Torneo>listaTorneos=new ArrayList<Torneo>();
		ResultSet rs=driver.runQuery("SELECT * FROM panenka.contests");
		try {
			while(rs.next()){
				//NO PARECE QUE HAYA UN ATRIBUTO PREMIO COMO TAL
				//EL FEE EN PRINCIPIO HAY QUE HACER OTRA CONSULTA PARA SACARLO
				//ESTADO NO SE QUÉ ES, HABRÁ QUE MIRAR A QUE SE CORRESPONDE
				Torneo a=new Torneo(rs.getString(1),100,rs.getDate(6),rs.getInt(9),50,0);
				listaTorneos.add(a);
			}
		return listaTorneos;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	public ArrayList<Torneo>pruebaObtenerTorneos(){
		/*String[]name= {"Asier","Javi","Juan","Paula"};
		ArrayList<Torneo>lista=new ArrayList<Torneo>();
		Date date=new Date(117,10,9);
		int[]estado= {0,1};
		int[]aleatorios= {10,20,30,50,100,102,340,495};
		Random b=new Random();
		for(int i=0;i<4;i++) {
			Torneo a=new Torneo(name[i],aleatorios[b.nextInt(8)],date,aleatorios[b.nextInt(8)],aleatorios[b.nextInt(8)],estado[b.nextInt(2)]);
			lista.add(a);
		}
		return lista;*/
		return null;
	}
}
