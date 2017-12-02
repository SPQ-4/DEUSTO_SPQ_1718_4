package controllers;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.StackedBarChart;
import javafx.scene.chart.XYChart;

import java.net.URL;
import java.util.HashMap;
import java.util.ResourceBundle;

public class StackedBarChartController implements Initializable {

    @FXML
    public StackedBarChart stackedBarChart;

    /**
     * Método inicializador. Crea una instancia del controlador de torneos, del cual obtiene la
     * lista de torneos de la ultima semana y llama al método que carga esos datos en un gráfico
     * de barras que muestra los ingresos por días de la semana de la última semana.
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        ContestController contestController = new ContestController();
        loadData(contestController.getLastWeekContestStats());
    }

    /**
     * Método que realiza la carga de datos en un gráfico de barras (StackedChart). Recibe un mapa
     * con de mapas, sobre el cual itera para añadir sus datos al gráfico.
     */
    public void loadData(HashMap<String, HashMap<String, Double>> data) {
        ObservableList<String> weekdays = FXCollections.observableArrayList(
                "Monday",
                "Tuesday",
                "Wednesday",
                "Thursday",
                "Friday",
                "Saturday",
                "Sunday"
        );
        CategoryAxis xAxis = new CategoryAxis();
        xAxis.setCategories(weekdays);
        xAxis.setLabel("Weekdays");
        NumberAxis yAxis = new NumberAxis();
        yAxis.setLabel("Revenue");
        this.stackedBarChart.setTitle("Revenue by weekday");
        for (String key : data.keySet()) {
            XYChart.Series classicData = new XYChart.Series<String, Number>();
            classicData.setName(key);
            for (String secondKey : data.get(key).keySet()) {
                classicData.getData().add(new XYChart.Data<String, Number>(secondKey, data.get(key).get(secondKey)));
            }
            this.stackedBarChart.getData().add(classicData);
        }

    }
}