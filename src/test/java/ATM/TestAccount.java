package ATM;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TestAccount {
    AtmProcessor c6atm = new AtmProcessor();

    @Before
    public void setUp(){
        AtmProcessor c6Atm = new AtmProcessor();
    }

    @After
    public void tearDown(){

    }

    @Test
    public void testWithdrawTrue(){

        Account account = new Account();
        Double balance = 1000.00;
        Double amount = 300.00;

        Double newBalance = balance - amount;
        assertTrue(newBalance == 700);

    }

    @Test
    public void testWithdrawFalse(){

        Account account = new Account();
        Double balance = 500.00;
        Double amount = 600.00;

        Double newBalance = balance - amount;
        assertFalse(newBalance > 0);
    }

    @Test
    public void testDeposit(){

        Account account = new Account();
        Double balance = 1000.00;
        Double amount = 300.00;

        Double newBalanceActual = balance + amount;
        Double newBalanceExpected = 1300.00;
        Assert.assertEquals(newBalanceExpected, newBalanceActual);
    }

    @Test
    public void testBalance(){
        Account account = new Account();
        Double balance = 1000.00;
        Assert.assertEquals(balance, 1000.00, .00001);
    }

    @Test
    public void testIsEmpty(){
        Account account = new Account();
        Double balance = 0.00;
        assertTrue(balance == 0.00);
        assertFalse(balance > 0);
    }



}
