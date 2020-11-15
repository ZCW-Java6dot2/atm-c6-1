package ATM;

import org.junit.Assert;
import org.junit.Test;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.Assert.*;

public class TestAtmProcessor {
    AtmProcessor c6Atm = new AtmProcessor();

    @Test
    public void testReadIntInput() {

        String input = "2";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Integer expectedInput = Integer.parseInt(input);

        assertEquals(expectedInput, c6Atm.readIntInput());
    }


    @Test
    public void testNewUsername() {
        String userName = "ZekaiHErooooo";

        assertFalse(c6Atm.newUsername(userName));
    }

    @Test
    public void testNewUsername2() {
        String userName = "BuzzLight";

        assertTrue(c6Atm.newUsername(userName));
    }

    @Test
    public void testValidateUserPass() {
        String userName = "Will";
        String password = "password";

        assertTrue(c6Atm.validateUserPass(userName,password));
    }

    @Test
    public void testWashMoney() {

        String input = "2.0";
        InputStream in = new ByteArrayInputStream(input.getBytes());
        System.setIn(in);
        Double actualWash = c6Atm.washMoney();
        Double expectedWash = 2.0;


        assertEquals(expectedWash, actualWash);
    }


}

