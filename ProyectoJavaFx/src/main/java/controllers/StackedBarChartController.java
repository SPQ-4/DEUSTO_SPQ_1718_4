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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadData(HashMap<String, Double> data) {
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
        this.stackedBarChart = new StackedBarChart(xAxis, yAxis);

        XYChart.Series classicData = new XYChart.Series();
        for (String key : data.keySet()) {
            classicData.setName(key);
            classicData.getData().add(new XYChart.Data(key, data.get(key)));
        }
        this.stackedBarChart.getData().add(classicData);
        this.stackedBarChart.setScaleY(2.0);
//        XYChart.Series h2hData = new XYChart.Series();
//        for (String key : data.keySet()) {
//            h2hData.setName(key);
//            h2hData.getData().add(new XYChart.Data(key, data.get(key)));
//        }
//
//        ArrayList<PieChart.Data> dataList = new ArrayList<>();
//        for (String key : data.keySet()) {
//            dataList.add(new PieChart.Data(key, data.get(key)));
//        }
//        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(dataList);
//        this.pieChart.setData(list);
    }
}