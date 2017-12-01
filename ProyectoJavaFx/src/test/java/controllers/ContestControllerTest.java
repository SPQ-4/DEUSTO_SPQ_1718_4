package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import db.MySQLDriver;
import java.util.HashMap;
import models.Contest;
import org.junit.Before;
import org.junit.Test;

public class ContestControllerTest {

  private ContestController a;

    @Before
    public void initialize() { a = new ContestController(); }

    @Test
    public void testGetContestEntryStats1() {
        HashMap<String, Double> data = a.getContestEntryStats("1970/01/01 02:00:00", "1970/01/01 00:00:00");
        HashMap<String, Double> result = new HashMap<>();
        result.put("Classic", 0.0);
        result.put("Head-to-head", 0.0);
        assertEquals(data, result);
    }

    @Test
    public void testGetContestEntryStats2() {
      HashMap<String, Double> data = a.getContestEntryStats(null, null);
      assertNotEquals(data, null);
    }

}
