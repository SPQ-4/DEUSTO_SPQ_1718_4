package Logica_DashBoard;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import SPQ_1718_4.Proyecto.Torneo;

public class ModeloTabla extends AbstractTableModel {
	private ArrayList<Torneo>listaTorneos;
	private ObtenerDatos datos;
	
	public ModeloTabla() {
		 datos=new ObtenerDatos();
		// this.listaTorneos=this.datos.obtenerTorneos();
		 this.listaTorneos=this.datos.obtenerTorneos();
		 //System.out.println(listaTorneos.get(0).getName());
	}
	public String getColumnName(int col) {
		return listaTorneos.get(0).getNombreColumna(col);
	}
	public ModeloTabla(ArrayList<Torneo>listaTorneos) {
		this.listaTorneos=listaTorneos;
	}
	public Torneo getTorneo(int i) {
		return listaTorneos.get(i);
	}
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return listaTorneos.get(0).getNumeroColumnas();
	}
	public int getRowCount() {
		// TODO Auto-generated method stub
		return listaTorneos.size();
	}
	public Object getValueAt(int row, int col) {
	    return listaTorneos.get(row).getAtributo(col);
	}
}
