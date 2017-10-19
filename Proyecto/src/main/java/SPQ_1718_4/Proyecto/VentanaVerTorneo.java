package SPQ_1718_4.Proyecto;

import java.awt.EventQueue;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.JScrollPane;
import javax.swing.JLabel;
import java.awt.GridLayout;

public class VentanaVerTorneo {

	private JFrame frame;
	private JTable table = new JTable();
	private DefaultTableModel modelo = new DefaultTableModel();
	private JTable table2 = new JTable();
	private DefaultTableModel modelo2 = new DefaultTableModel();
	private final JLabel lblNewLabel = new JLabel("Jugadores Registrados");
	private final JLabel lblNewLabel_1 = new JLabel("New label");
	private final JButton btnNewButton = new JButton("Inscribirme al torneo");

	/**
	 * Create the application.
	 */
	public VentanaVerTorneo(Torneo torneo) {
		rellenarDatosTorneo(torneo);
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JPanel panel = new JPanel();
		frame.getContentPane().add(panel, BorderLayout.CENTER);
		JPanel panel2 = new JPanel();
		frame.getContentPane().add(panel2, BorderLayout.SOUTH);
		panel.add(lblNewLabel_1);
		
		JScrollPane scrollPane = new JScrollPane(table);
		panel.add(scrollPane);
		panel2.setLayout(new GridLayout(3, 1, 0, 0));
		panel2.add(lblNewLabel);
		panel2.add(table2);
		
		panel2.add(btnNewButton);
		this.frame.setVisible(true);
	}
	
	public void rellenarDatosTorneo(Torneo torneo) {
		lblNewLabel_1.setText(torneo.getNombre());
		String titulos[] = new String[] {"Id Torneo", "Nombre", "Fecha", "País" };
		modelo.setColumnIdentifiers(titulos);
		modelo.addRow(crearLineaTorneo(torneo));
		table.setModel(modelo);
		String titulos2[] = new String[] {"Usuario","Nombre","Email"};
		modelo2.setColumnIdentifiers(titulos2);
		for(Jugador jug:torneo.getJugadores()) {
			modelo2.addRow(crearLineaJugador(jug));
		}
		table2.setModel(modelo2);

	}
	
	public Object[] crearLineaTorneo(Torneo torneo) {
		JButton boton = new JButton("Ver Alineación");
		Object[] linea = new Object[] {torneo.getIdTorneo(),torneo.getNombre(),torneo.getFecha().toString(),boton};
		return linea;
	}
	
	public Object[] crearLineaJugador(Jugador jugador) {
		Object[] linea = new Object[] {jugador.getUsuario(),jugador.getNombre(),jugador.getEmail()};
		return linea;
	}
	

}
