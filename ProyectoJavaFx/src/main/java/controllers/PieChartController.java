package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;

import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;

public class PieChartController implements Initializable {

  @FXML
  public PieChart pieChart;

  /**
   * Método inicializador. Crea una instancia del controlador de torneos, del cual obtiene la
   * lista de torneos entre dos fechas y llama al método que carga esos datos en un gráfico
   * de tarta que muestra los ingresos por tipo de torneo en ese período de tiempo.
   */
  @Override
  public void initialize(URL location, ResourceBundle resources) {
    ContestController contestController = new ContestController();
    setChartInfo(loadData(contestController.getContestEntryStats(null, null)), "Revenue by contest type");
  }

  /**
   * Método que transforma datos de un mapa a una lista para ser añadidos a un gráfico de tarta.
   * Recibe un mapa con los ingresos generados por cada tipo de torneo, sobre el cual itera para
   * añadir esos datos a una lista que devuelve.
   */
  public ArrayList<PieChart.Data> loadData(HashMap<String, Double> data) {
    if (data == null) {
      return null;
    }
    ArrayList<PieChart.Data> dataList = new ArrayList<>();
    for (String key : data.keySet()) {
      dataList.add(new PieChart.Data(key, data.get(key)));
    }
    return dataList;
  }

  /**
   * Método que realiza la carga de datos en un gráfico de tarta (PieChart) y establece su título.
   * Recibe una lista con los ingresos generados por cada tipo de torneo
   */
  public void setChartInfo(ArrayList<PieChart.Data> dataList, String title) {
    this.pieChart.setData(FXCollections.observableArrayList(dataList));
    this.pieChart.setTitle(title);
  }
}