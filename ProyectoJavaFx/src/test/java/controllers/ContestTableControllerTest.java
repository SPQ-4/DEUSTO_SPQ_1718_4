package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import javafx.collections.FXCollections;
import org.junit.Before;
import org.junit.Test;

public class ContestTableControllerTest {

  private ContestTableController a;

  @Before
  public void initialize() {
      a = new ContestTableController();
      a.contests = FXCollections.observableArrayList();
  }

  @Test
  public void testGetContests() {
    a.getContests();
    assertNotEquals(0, a.contests.size());
  }

  @Test
  public void testSetController() {
    Controller testController = new Controller();
    a.setController(testController);
    assertEquals(testController, a.getController());
  }

}
