package Visualizacion_DashBoard;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import Logica_DashBoard.ModeloTabla;

public class Ventana_DashBoard extends JFrame {
	public JTable tablaPrincipal;
	public JButton crearTorneo;
	public JButton administrarTorneo;
	public ModeloTabla modelo;
	public JScrollPane panelCentral;
	public JPanel panelArriba=new JPanel(new GridLayout());
	public Ventana_DashBoard() {
		super();
		modelo=new ModeloTabla();
		tablaPrincipal=new JTable(modelo);
		panelCentral=new JScrollPane(tablaPrincipal);
		
		crearTorneo=new JButton("Crear Torneo");
		administrarTorneo=new JButton("Administrar Torneo");
		
		this.panelArriba.add(crearTorneo);
		this.panelArriba.add(administrarTorneo);
		
		this.getContentPane().add(panelCentral, BorderLayout.CENTER);
		this.getContentPane().add(panelArriba, BorderLayout.NORTH);
		this.setSize(400, 400);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		tablaPrincipal.addMouseListener(new MouseAdapter() {
		    public void mousePressed(MouseEvent me) {
		        JTable table =(JTable) me.getSource();
		        int row = table.getSelectedRow();
		        if (me.getClickCount() == 2) {
		            // your valueChanged overridden method 
		        	cerrarVentana();
		        	VentanaVerTorneo ventana=new VentanaVerTorneo(((ModeloTabla) table.getModel()).getTorneo(row));
		        }
		    }
		});
		crearTorneo.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				if(e.getSource()==crearTorneo) {
					cerrarVentana();
					VentanaNuevoTorneo a=new VentanaNuevoTorneo();
				}
				
			}
		});
		
	}
	public void cerrarVentana() {
		this.setVisible(false);
	}
}
