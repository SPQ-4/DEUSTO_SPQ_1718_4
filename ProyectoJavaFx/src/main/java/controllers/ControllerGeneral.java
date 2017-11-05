package controllers;

import java.sql.ResultSet;
import java.sql.SQLException;

import db.MySQLDriver;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.text.Text;


public class ControllerGeneral {
	
	private MySQLDriver driverDB;
	@FXML
	private PieChart contestsRatio;
	
	private PieChart.Data slice1;
	private PieChart.Data slice2;
	
	
	@FXML
	public void initialize() throws SQLException {

		driverDB= new MySQLDriver();
		datosTorneoPorTipo();
		
	}
	public void datosTorneoPorTipo() throws SQLException{
		//habrá que hacer una query por cada tipo de torneo, por ahora hay 2 tipos (en caso de haber más se podría hacer con un while)
		String query1="select count(case id_contest_type when 'contest_type_1' then 1 else NULL end ) as value from panenka.contests "
				+ "where YEAR(open_date) = YEAR(CURDATE()) ;";
		ResultSet type1= driverDB.runQuery(query1);
		while(type1.next()){
			slice1 = new PieChart.Data("Head-to-Head", Integer.parseInt(type1.getString("value")));
		}		
		
		String query2="select count(case id_contest_type when 'contest_type_2' then 1 else NULL end ) as value from panenka.contests "
				+ "where YEAR(open_date) = YEAR(CURDATE()) ;"; 
		ResultSet type2= driverDB.runQuery(query2);
		while(type2.next()){
			slice2 = new PieChart.Data("Multiuser", Integer.parseInt(type2.getString("value")));
		}
		ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(slice1,slice2);
		contestsRatio.setData(pieChartData);
	}
}


