package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.MySQLDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
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

import org.apache.log4j.Logger;

import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.GridPane;
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
	@FXML
	private Pane monthKPI;
	@FXML
	private Label caption;
	
	private PieChart.Data slice1;
	private PieChart.Data slice2;
	static Logger logger = Logger.getLogger(ControllerGeneral.class);
	
	public ControllerGeneral(){
		driverDB= new MySQLDriver();
	}
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		logger.info("inicializando controller general");
		driverDB= new MySQLDriver();	
		try {
			obtainMonthKPI();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			logger.warn("falla el método obtainMonthKPI(), problema de SQL");
			e1.printStackTrace();
		}
		paintMonthKPI();
		
		try {
			contestsTypes();
		} catch (SQLException a) {
			logger.warn("falla el método datosTorneoPorTipo(), problema de SQL");
			a.printStackTrace();
		}
		ArrayList<Integer> datos = new ArrayList();
		 int suma=0;
		 for(int i=0;i<30;i++) {
			 datos.add(i+3);
			 suma=suma + i +3;
		 }	 
		double media = suma/datos.size();
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/views/monthUsersChart.fxml"));
		try {
			monthUsersChart.getChildren().add((Pane)fxmlLoader.load());
		} catch (IOException e) {
			logger.fatal("problemas al cargar el fxml");
			e.printStackTrace();
		}
		UsersChartController controllerA = fxmlLoader.getController();
	    controllerA.cargarDatos(datos, media);
	   
	}
	public Double playedRate() throws SQLException{
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
		return playedRateParcial;
	}
	public Double revenuesByMonth() throws SQLException{
		String revenues="select sum(\"fee\") from panenka_db.contests_contest,panenka_db.contests_entry where MONTH(created_date) =MONTH(CURDATE()) AND panenka_db.contests_entry.id_contest_id=panenka_db.contests_contest.id";
		ResultSet type3=driverDB.runQuery(revenues);
		Double revenuesThisMonthParcial=new Double(0);
		if(type3!=null) {
			while(type3.next()) {
				revenuesThisMonthParcial=(double)type3.getInt(1);
			}
		}
		return revenuesThisMonthParcial;
	}
	public Double byUsers() throws SQLException{
		String by_admin="SELECT count(*) FROM panenka_db.contests_contest where created_by_admin=0";
		ResultSet type4=driverDB.runQuery(by_admin);
		Double tournamentsByUsersParcial=new Double(0);
		while(type4.next()) {
			tournamentsByUsersParcial=(double)type4.getInt(1);
		}
		return tournamentsByUsersParcial;
	}
	public Double usersMonth() throws SQLException{
		String usersMonth="SELECT count(DISTINCT \"user_id\") FROM panenka_db.users_login where MONTH(login_date)=MONTH(CURDATE())";
		ResultSet type5=driverDB.runQuery(usersMonth);
		Double usersThisMonthParcial=new Double(0);
		while(type5.next()) {
			usersThisMonthParcial=(double)type5.getInt(1);
		}
		return usersThisMonthParcial;
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
		//String playedTournaments="select count(*) from panenka_db.contests_contest where MONTH(close_date) = MONTH(CURDATE()) AND MONTH(created_date) = MONTH(CURDATE()) ;";
		//String openTournaments="select count(*) from panenka_db.contests_contest where MONTH(created_date) =MONTH(CURDATE())";
		this.playedRate.setText(playedRate().toString());
		this.revenuesThisMonth.setText(revenuesByMonth().toString());
		this.tournamentsByUsers.setText(byUsers().toString());
		this.usersThisMonth.setText(usersMonth().toString());
	}
	public Double getValueContestType(int a) throws NumberFormatException, SQLException{
		double value=0;
		String query1="select count(case contest_type_id when'"+a+"' then 1 else NULL end ) as value from panenka_db.contests_contest "
				+ "where YEAR(close_date) = YEAR(CURDATE()) ;";
		ResultSet type1= driverDB.runQuery(query1);
		while(type1.next()){
			value = Integer.parseInt(type1.getString("value"));
		}		
		return value;
	}
	public void contestsTypes() throws SQLException{
		//habrá que hacer una query por cada tipo de torneo, por ahora hay 2 tipos (en caso de haber más se podría hacer con un while)
		
			slice1 = new PieChart.Data("Public", getValueContestType(1));		
			slice2 = new PieChart.Data("Private", getValueContestType(2));
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(slice1,slice2);
		contestsRatio.setData(pieChartData);
		caption.setTextFill(Color.BLACK);
        caption.setStyle("-fx-font: 24 arial;");
		for (final PieChart.Data data : contestsRatio.getData()) {
			data.getNode().addEventHandler(MouseEvent.MOUSE_PRESSED,
			        new EventHandler<MouseEvent>() {
			            @Override public void handle(MouseEvent e) {
		                	caption.setLayoutX(e.getSceneX());
		                    caption.setLayoutY(e.getSceneY());
		                    caption.setText(Double.toString(data.getPieValue()));
		                  }
			        });
		}
	}
}


