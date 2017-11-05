package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
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
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		obtainMonthKPI();
		paintMonthKPI();
		usersThisMonth.setText("label1");
		revenuesThisMonth.setText("Label2");
		tournamentsByUsers.setText("label3");
		playedRate.setText("label4");
	}
	public void paintMonthKPI() {
		usersThisMonth.setShape(new Circle(10,10,10));
		
	}
	public void obtainMonthKPI() {
		
	}
}
