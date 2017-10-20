package SPQ_1718_4.Proyecto.CrearTorneo;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import SPQ_1718_4.Proyecto.Exceptions.validarFecha;
import SPQ_1718_4.Proyecto.Exceptions.validarMinMax;
import SPQ_1718_4.Proyecto.Exceptions.validarNombreTorneo;
import SPQ_1718_4.Proyecto.db.MySQLDriver;

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
	private JButton Crear;
	private String user;
	private MySQLDriver driverDB;
	private ArrayList <String> tipoTorneo;
	private ArrayList <String> queJornada;
	private ArrayList <String> tipoPremio;
	private ArrayList <String> tipoPrecio;	
	
	public VentanaNuevoTorneo(String usuario) {
		initialize();
		user=usuario;
	}
	private void initialize() {
		driverDB= new MySQLDriver();
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
		
		rellenarArray();
		
		tipo = new JComboBox(tipoTorneo.toArray());
		ventana.getContentPane().add(tipo);
		
		lblmeteFecha = new JLabel("  Mete la Fecha del torneo");
		ventana.getContentPane().add(lblmeteFecha);
		
		fechaTorneo = new JTextField();
		ventana.getContentPane().add(fechaTorneo);
		fechaTorneo.setColumns(10);
		
		lblJornada= new JLabel("  Mete la Jornada de la que entran partidos");
		ventana.getContentPane().add(lblJornada);
		
		jornada = new JComboBox(queJornada.toArray());
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
		
		premio = new JComboBox(tipoPremio.toArray());
		ventana.getContentPane().add(premio);
		
		lblCoste= new JLabel("  Selecciona el precio de entrada al torneo");
		ventana.getContentPane().add(lblCoste);
		
		coste = new JComboBox(tipoPrecio.toArray());
		ventana.getContentPane().add(coste);		
		
		Crear = new JButton("Crear");
		ventana.getContentPane().add(Crear);
		Crear.addActionListener(this);
		
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
	public void clearTodo(){
		nombre.setText(null);
		fechaTorneo.setText(null);
		numeroParticipantesMin.setText(null);
		numeroParticipantesMax.setText(null);
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==Crear){
			try {
				comprobarNombre();
				int min= Integer.parseInt(numeroParticipantesMin.getText());
				int max= Integer.parseInt(numeroParticipantesMax.getText());
				comprobarMaxMin(min, max);
				comprobarFecha();
				String insert=crearQuery(min,max);
				driverDB.runUpdate(insert);
				clearTodo();
				desdibujarventana();
			} catch (NumberFormatException e1) {
				numeroParticipantesMin.setText(null);
				numeroParticipantesMax.setText(null);
				JOptionPane.showMessageDialog(ventana,"El numero de participantes es un numero","MAL",JOptionPane.NO_OPTION);
			} catch (validarMinMax e1) {
				// TODO Auto-generated catch block
				JOptionPane.showMessageDialog(ventana,e1.getMessage(),"Imposible",JOptionPane.NO_OPTION);
				numeroParticipantesMin.setBackground(Color.RED);
				numeroParticipantesMax.setBackground(Color.RED);
			} catch (validarFecha e2) {
				JOptionPane.showMessageDialog(ventana,e2.getMessage(),"Imposible",JOptionPane.NO_OPTION);
				fechaTorneo.setBackground(Color.RED);
			} catch (validarNombreTorneo e3) {
				JOptionPane.showMessageDialog(ventana,e3.getMessage(),"Imposible",JOptionPane.NO_OPTION);
				nombre.setBackground(Color.RED);
			} 	
		}
	}
	public void comprobarMaxMin(int minimo, int maximo)throws validarMinMax{
		if (minimo>maximo){
			throw new validarMinMax();
		}
	}
	public void comprobarFecha()throws validarFecha{
		if (!fechaTorneo.getText().matches("^([0-2][0-9]||3[0-1])-(0[0-9]||1[0-2])-[0-9][0-9][0-9][0-9]$")){
			throw new validarFecha();
		}else{
			fechaTorneo.setBackground(Color.WHITE);
		}
	}	
	
	public void comprobarNombre()throws validarNombreTorneo{
		String query="Select id_contest from panenka.contests where id_contest LIKE '"+nombre.getText()+"';";
		ResultSet result= driverDB.runQuery(query);
		try {
			if (result.next()){
				throw new validarNombreTorneo();
			}else{
				nombre.setBackground(Color.WHITE);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String crearQuery(int min, int max){
		//hay elementos que no se piden por consola porque en esta versión no se van a utilizar
		String query="insert into panenka.contests(id_contest,is_created_by_admin,open_date,minimum_participants,"
			    	+ "maximum_participants,close_date, stat_date,end_date,id_matchweek, id_contest_type, "
			    	+ "id_entry_fee, id_prize_structure,id_user) values ('"+nombre.getText()+"',0,"+
			    	"STR_TO_DATE('"+fechaTorneo.getText()+"','%d-%m-%Y'),"+min+","+max+",'2018-12-12',"
			    		+ "'2018-12-10','2018-12-12','"+
			    	jornada.getSelectedItem()+"','"+ tipo.getSelectedItem()+"','"+coste.getSelectedItem()+
			    	"','"+premio.getSelectedItem()+"','"+user+"');";
		return query;
	}
	public void rellenarArray(){
		//rellenamos el array del tipo de torneo, por ahora con el id, mañana que enseñe el nombre
		String query1="Select id_contest_type from panenka.contest_types;";
		try{
		ResultSet tipos= driverDB.runQuery(query1);
		tipoTorneo=new ArrayList<String>();
		while(tipos.next()){
			tipoTorneo.add(tipos.getString("id_contest_type"));
		}
		
		String query2="Select id_matchweek from panenka.matchweek;";
		ResultSet jornadas= driverDB.runQuery(query2);
		queJornada=new ArrayList<String>();
		while(jornadas.next()){
			queJornada.add(jornadas.getString("id_matchweek"));
		}
		
		String query3="Select id_prize_structure from panenka.prize_structures;";
		ResultSet premios= driverDB.runQuery(query3);
		tipoPremio=new ArrayList<String>();
		while(premios.next()){
			tipoPremio.add(premios.getString("id_prize_structure"));
		}
		
		String query4="Select id_entry_fee from panenka.entry_fees;";
		ResultSet precios= driverDB.runQuery(query4);
		tipoPrecio=new ArrayList<String>();
		while(precios.next()){
			tipoPrecio.add(precios.getString("id_entry_fee"));
		}
		}catch(Exception e1) {
            e1.printStackTrace();
        }
	}
}


