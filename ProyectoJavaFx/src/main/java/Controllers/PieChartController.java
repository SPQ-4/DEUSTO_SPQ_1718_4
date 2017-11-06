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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

    }

    public void loadData(HashMap<String, Double> data) {
        ArrayList<PieChart.Data> dataList = new ArrayList<>();
        for (String key : data.keySet()) {
            dataList.add(new PieChart.Data(key, data.get(key)));
        }
        ObservableList<PieChart.Data> list = FXCollections.observableArrayList(dataList);
        this.pieChart.setData(list);
    }
}
