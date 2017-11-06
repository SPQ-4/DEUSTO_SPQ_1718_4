package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.MySQLDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Circle;
 
public class ControllerGeneral implements Initializable{
	@FXML
	Label usersThisMonth;
	@FXML
	Label revenuesThisMonth;
	@FXML
	Label tournamentsByUsers;
	@FXML
	Label playedRate;
	int [] usersThisMonthThresholds=new int[2];
	int [] revenuesThisMonthThresholds=new int[2];
	int [] tournamentsByUsersThresholds=new int[2];
	int [] playedRateThresholds=new int[2];
	
	private MySQLDriver driverDB;
	@FXML
	private PieChart contestsRatio;
	@FXML
	private Pane monthUsersChart;
	
	private PieChart.Data slice1;
	private PieChart.Data slice2;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		obtainMonthKPI();
		paintMonthKPI();
		usersThisMonth.setText("label1");
		revenuesThisMonth.setText("Label2");
		tournamentsByUsers.setText("label3");
		playedRate.setText("label4");
		driverDB= new MySQLDriver();
		try {
			datosTorneoPorTipo();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		ArrayList<Integer> datos = new ArrayList();
		 int suma=0;
		 for(int i=0;i<30;i++) {
			 datos.add(i+3);
			 suma=suma + i +3;
		 }	 
		double media = suma/datos.size();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/monthUsersChart.fxml"));
		try {
			monthUsersChart.getChildren().add((Pane)fxmlLoader.load());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		UsersChartController controller = fxmlLoader.getController();
	       controller.cargarDatos(datos, media);
	}
	public void paintMonthKPI() {
		usersThisMonth.setShape(new Circle(10,10,10));
		
	}
	public void obtainMonthKPI() {
		
	}
	public void datosTorneoPorTipo() throws SQLException{
		//habrá que hacer una query por cada tipo de torneo, por ahora hay 2 tipos (en caso de haber más se podría hacer con un while)
		String query1="select count(case contest_type_id when 1 then 1 else NULL end ) as value from panenka_db.contests_contest "
				+ "where YEAR(close_date) = YEAR(CURDATE()) ;";
		ResultSet type1= driverDB.runQuery(query1);
		while(type1.next()){
			slice1 = new PieChart.Data("Public", Integer.parseInt(type1.getString("value")));
		}		
		
		String query2="select count(case contest_type_id when '2' then 1 else NULL end ) as value from panenka_db.contests_contest "
				+ "where YEAR(close_date) = YEAR(CURDATE()) ;"; 
		ResultSet type2= driverDB.runQuery(query2);
		while(type2.next()){
			slice2 = new PieChart.Data("Private", Integer.parseInt(type2.getString("value")));
		}
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(slice1,slice2);
		contestsRatio.setData(pieChartData);
	}
}


