package controllers;

import java.util.Arrays;
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
        ContestController contestController = new ContestController();
        HashMap<String, HashMap<String, Double>> dbResults = contestController.getLastWeekContestStats();
        loadData(dbResults);
    }

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
                System.out.println(key + " (" + secondKey + "): " + data.get(key).get(secondKey));
                classicData.getData().add(new XYChart.Data<String, Number>(secondKey, data.get(key).get(secondKey)));
            }
            this.stackedBarChart.getData().add(classicData);
        }

    }
}