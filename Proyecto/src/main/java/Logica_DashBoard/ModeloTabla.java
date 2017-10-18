package Logica_DashBoard;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;

public class ModeloTabla extends AbstractTableModel {
	private ArrayList<Torneo>listaTorneos;
	private ObtenerDatos datos;
	public ModeloTabla() {
		 datos=new ObtenerDatos();
		 //this.listaTorneos=this.datos.obtenerTorneos();
		 this.listaTorneos=this.datos.pruebaObtenerTorneos();
	}
	public ModeloTabla(ArrayList<Torneo>listaTorneos) {
		this.listaTorneos=listaTorneos;
	}
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	public int getRowCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	public Object getValueAt(int col, int row) {
		// TODO Auto-generated method stub
		 if (col==0) return row+1;
	     else return listaTorneos.get(row).getAtributo(col);
	}
}
