package SPQ_1718_4.Proyecto_CrearTorneo;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class VentanaNuevoTorneo implements ActionListener{
	private JFrame ventana;
	private JTextField nombre;
	private JLabel lblmeteFecha;
	private JTextField fechaJornada;
	private JLabel lblNumeroParticipantes;
	private JTextField numeroParticipantes;
	private JLabel label4;// para que la ventana se ajuste bien
	private JLabel label;// para que la ventana se ajuste bien
	private JButton Continuar;
	public VentanaNuevoTorneo() {
		initialize();
	}
	private void initialize() {
		ventana = new JFrame();
		ventana.setSize(ventana.getToolkit().getScreenSize());
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		ventana.getContentPane().setLayout(new GridLayout(0,2));
		
		JLabel lblNewLabel = new JLabel("  Introduce el nombre del torneo");
		ventana.getContentPane().add(lblNewLabel);
		
		nombre = new JTextField();
		ventana.getContentPane().add(nombre);
		nombre.setColumns(10);
		
		lblmeteFecha = new JLabel("  Mete la Fecha de la Jornada");
		ventana.getContentPane().add(lblmeteFecha);
		
		fechaJornada = new JTextField();
		ventana.getContentPane().add(fechaJornada);
		fechaJornada.setColumns(10);
		
		lblNumeroParticipantes = new JLabel("  Numero de participantes");
		ventana.getContentPane().add(lblNumeroParticipantes);
		
		numeroParticipantes = new JTextField();
		ventana.getContentPane().add(numeroParticipantes);
		numeroParticipantes.setColumns(10);
		
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
		fechaJornada.setText(null);
		numeroParticipantes.setText(null);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		VentanaNuevoTorneo ventana= new VentanaNuevoTorneo();
		ventana.dibujarventana();
	}
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub
		
	}

}
