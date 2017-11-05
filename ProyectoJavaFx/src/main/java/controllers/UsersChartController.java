package controllers;

import java.util.ArrayList;

import javafx.fxml.FXML;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.XYChart;
import javafx.scene.chart.XYChart.Data;

public class UsersChartController {
	
	@FXML LineChart lineChart;
	
	public void cargarDatos(ArrayList<Integer> usuariosDia, double media) {
		
		XYChart.Series series = new XYChart.Series(); 
		series.setName("Usuarios por día");
		
		XYChart.Series serieMedia = new XYChart.Series(); 
		serieMedia.setName("Media del mes");
		for(int i=0;i<usuariosDia.size();i++) {
			int usuarios = usuariosDia.get(i);
			String dia = Integer.toString(i+1);
			XYChart.Data data = new XYChart.Data(dia, usuarios);
			XYChart.Data dataMedia = new XYChart.Data(dia, media);
			series.getData().add(data);
			serieMedia.getData().add(dataMedia);
		}
		lineChart.getData().add(series);
		lineChart.getData().add(serieMedia);
	}
}
