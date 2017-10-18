package SPQ_1718_4.Proyecto_CrearTorneo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class VentanaNuevoTorneo implements ActionListener{
	//ventana en la que se va a colocar el formulario
	private JFrame ventana;
	private JTextField nombre;
	private JLabel lblTipo;
	private JComboBox tipo;
	//por un lado está la fecha del torneo y otra la jornada
	//ahora la fecha se mete a mano
	private JLabel lblmeteFecha;
	private JTextField fechaTorneo;
	private JLabel lblJornada;
	private JComboBox jornada;
	//por como está hecha la base de datos haden falta los máximos y mínimos
	private JLabel lblNumeroParticipantesMin;
	private JTextField numeroParticipantesMin;
	private JLabel lblNumeroParticipantesMax;
	private JTextField numeroParticipantesMax;
	//se sacarán de la base de datos para elegir
	private JLabel lblPremio;
	private JComboBox premio;
	private JLabel lblCoste;
	private JComboBox coste;
	private JLabel label4;// para que la ventana se ajuste bien
	private JLabel label;// para que la ventana se ajuste bien
	private JButton Continuar;
	public VentanaNuevoTorneo() {
		initialize();
	}
	private void initialize() {
		//inicializamos la ventana, le metemos un panel y que se pueda cerrar
		ventana = new JFrame();
		ventana.setSize(ventana.getToolkit().getScreenSize());
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(new GridLayout(0,2));
		
		//vamos metiendo cada uno de los elementos del formulario		
		JLabel lblNewLabel = new JLabel("  Introduce el nombre del torneo");
		ventana.getContentPane().add(lblNewLabel);
		
		nombre = new JTextField();
		ventana.getContentPane().add(nombre);
		nombre.setColumns(10);
		
		lblTipo = new JLabel("  Selecciona el tipo de torneo");
		ventana.getContentPane().add(lblTipo);
		
		tipo = new JComboBox();
		ventana.getContentPane().add(tipo);
		
		
		lblmeteFecha = new JLabel("  Mete la Fecha del torneo");
		ventana.getContentPane().add(lblmeteFecha);
		
		fechaTorneo = new JTextField();
		ventana.getContentPane().add(fechaTorneo);
		fechaTorneo.setColumns(10);
		
		lblJornada= new JLabel("  Mete la Jornada de la que entran partidos");
		ventana.getContentPane().add(lblJornada);
		
		jornada = new JComboBox();
		ventana.getContentPane().add(jornada);
		
		lblNumeroParticipantesMin = new JLabel("  Numero de participantes Minimo");
		ventana.getContentPane().add(lblNumeroParticipantesMin);
		
		numeroParticipantesMin = new JTextField();
		ventana.getContentPane().add(numeroParticipantesMin);
		numeroParticipantesMin.setColumns(10);
		
		lblNumeroParticipantesMax = new JLabel("  Numero de participantes Maximo");
		ventana.getContentPane().add(lblNumeroParticipantesMax);
		
		numeroParticipantesMax = new JTextField();
		ventana.getContentPane().add(numeroParticipantesMax);
		numeroParticipantesMax.setColumns(10);
		
		lblPremio= new JLabel("  Selecciona el premio a repartir");
		ventana.getContentPane().add(lblPremio);
		
		premio = new JComboBox();
		ventana.getContentPane().add(premio);
		
		lblCoste= new JLabel("  Selecciona el precio de entrada al torneo");
		ventana.getContentPane().add(lblCoste);
		
		coste = new JComboBox();
		ventana.getContentPane().add(coste);		
		
		Continuar = new JButton("Continuar");
		ventana.getContentPane().add(Continuar);
		Continuar.addActionListener(this);
		
		label4 = new JLabel("");
		ventana.getContentPane().add(label4);
		
		label = new JLabel("");
		ventana.getContentPane().add(label);
	}
	public void dibujarventana(){
		ventana.setVisible(true);
		
	}
	public void desdibujarventana(){
		ventana.setVisible(false);
	}
	public void BorrarCosas(){
		nombre.setText(null);
		fechaTorneo.setText(null);
		numeroParticipantesMin.setText(null);
		numeroParticipantesMax.setText(null);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaNuevoTorneo ventana= new VentanaNuevoTorneo();
		ventana.dibujarventana();
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Continuar){
			try {
				Integer.parseInt(numeroParticipantesMin.getText());
				Integer.parseInt(numeroParticipantesMax.getText());
			} catch (NumberFormatException e1) {
				numeroParticipantesMin.setText(null);
				numeroParticipantesMax.setText(null);
				JOptionPane.showMessageDialog(ventana,"El numero de participantes es un numero","MAL",JOptionPane.NO_OPTION);
			} 	
		}
	}
}
