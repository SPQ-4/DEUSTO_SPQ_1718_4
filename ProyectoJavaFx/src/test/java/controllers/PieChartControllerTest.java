package controllers;

import static org.junit.Assert.assertEquals;

import java.util.HashMap;
import javafx.scene.chart.PieChart;
import org.junit.Before;
import org.junit.Test;

public class PieChartControllerTest {
  private PieChartController a;
  private HashMap<String, Double> data;

  @Before
  public void initialize() {
      a = new PieChartController();
      data = new HashMap<>();
      data.put("Test", 100.0);
  }

  @Test
  public void testLoadData1() {
      assertEquals(100.0, a.loadData(data).get(0).getPieValue(), 0.00000000);
  }

  @Test
  public void testLoadData2() {
    assertEquals(null, a.loadData(null));
  }

}
