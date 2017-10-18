package Visualizacion_DashBoard;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JTable;
import Logica_DashBoard.ModeloTabla;

public class Ventana_DashBoard extends JFrame {
	public JTable tablaPrincipal;
	public JButton crearTorneo;
	public JButton administrarTorneo;
	public ModeloTabla modelo;
	public Ventana_DashBoard() {
		super();
		modelo=new ModeloTabla();
		JTable tabla=new JTable(modelo);
	}
}
