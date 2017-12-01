package controllers;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;

public class LoginControllerTest {

  private LoginController a;

    @Before
    public void initialize() { a = new LoginController(); }

    @Test
    public void testCheckCredentials() {
        assertEquals(false, a.checkCredentials("urbistondo.javier@gmail.com", "falsePassword"));
    }

}
