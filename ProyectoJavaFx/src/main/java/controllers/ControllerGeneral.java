package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.MySQLDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.chart.PieChart;

import java.awt.Paint;
import java.awt.PaintContext;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.AffineTransform;
import java.awt.geom.Rectangle2D;
import java.awt.image.ColorModel;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
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
	@FXML
	Circle usersThisMonthCircle;
	@FXML
	Circle revenuesThisMonthCircle;
	@FXML
	Circle tournamentsByUsersCircle;
	@FXML
	Circle playedRateCircle;
	double [] usersThisMonthThresholds= {10,20};
	double [] revenuesThisMonthThresholds= {500,1000};
	double [] tournamentsByUsersThresholds= {4,10};
	double [] playedRateThresholds= {0.5,0.8};
	
	private MySQLDriver driverDB;
	@FXML
	private PieChart contestsRatio;
	@FXML
	private Pane monthUsersChart;
	
	private PieChart.Data slice1;
	private PieChart.Data slice2;
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		driverDB= new MySQLDriver();
		try {
			obtainMonthKPI();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		paintMonthKPI();
		
//		try {
//			datosTorneoPorTipo();
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		ArrayList<Integer> datos = new ArrayList();
//		 int suma=0;
//		 for(int i=0;i<30;i++) {
//			 datos.add(i+3);
//			 suma=suma + i +3;
//		 }	 
//		double media = suma/datos.size();
//		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("../views/monthUsersChart.fxml"));
//		try {
//			monthUsersChart.getChildren().add((Pane)fxmlLoader.load());
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		UsersChartController controller = fxmlLoader.getController();
//	       controller.cargarDatos(datos, media);
	}
	public void paintMonthKPI() {
		double playedRateD=Double.parseDouble(playedRate.getText());
		double revenuesThisMonthD=Double.parseDouble(revenuesThisMonth.getText());
		double tournamentsByUsersD=Double.parseDouble(tournamentsByUsers.getText());
		double usersThisMonthD=Double.parseDouble(usersThisMonth.getText());
		if(playedRateD<playedRateThresholds[0]) {
			this.playedRateCircle.setFill(Color.RED);
		}else if(playedRateD<playedRateThresholds[1]) {
			this.playedRateCircle.setFill(Color.YELLOW);
		}else {
			this.playedRateCircle.setFill(Color.GREEN);
		}
		
		if(revenuesThisMonthD<revenuesThisMonthThresholds[0]) {
			this.revenuesThisMonthCircle.setFill(Color.RED);
		}else if(revenuesThisMonthD<revenuesThisMonthThresholds[1]) {
			this.revenuesThisMonthCircle.setFill(Color.YELLOW);
		}else {
			this.revenuesThisMonthCircle.setFill(Color.GREEN);
		}
		
		if(tournamentsByUsersD<tournamentsByUsersThresholds[0]) {
			this.tournamentsByUsersCircle.setFill(Color.RED);
		}else if(tournamentsByUsersD<tournamentsByUsersThresholds[1]) {
			this.tournamentsByUsersCircle.setFill(Color.YELLOW);
		}else {
			this.tournamentsByUsersCircle.setFill(Color.GREEN);
		}
		
		if(usersThisMonthD<usersThisMonthThresholds[0]) {
			this.usersThisMonthCircle.setFill(Color.RED);
		}else if(usersThisMonthD<usersThisMonthThresholds[1]) {
			this.usersThisMonthCircle.setFill(Color.YELLOW);
		}else {
			this.usersThisMonthCircle.setFill(Color.GREEN);
		}
		
		
	}
	public void obtainMonthKPI() throws SQLException {
		//OBTENER DE LA BASE DE DATOS
		String playedTournaments="select count(*) from panenka_db.contests_contest where MONTH(close_date) = MONTH(CURDATE()) AND MONTH(created_date) = MONTH(CURDATE()) ;";
		String openTournaments="select count(*) from panenka_db.contests_contest where MONTH(created_date) =MONTH(CURDATE())";
		ResultSet type1=driverDB.runQuery(playedTournaments);
		ResultSet type2=driverDB.runQuery(openTournaments);
		Double playedRateParcial=new Double(0);
		while(type1.next()){
			 playedRateParcial=(double) (type1.getInt(1));
		}	
		while(type2.next()){
			 playedRateParcial=playedRateParcial/(double)(type2.getInt(1));
		}	
		
		this.playedRate.setText(playedRateParcial.toString());
		this.revenuesThisMonth.setText("1");
		this.tournamentsByUsers.setText("20");
		this.usersThisMonth.setText("10");
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


