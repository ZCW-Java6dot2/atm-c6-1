package ATM;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAccount {
    Account account = new Account(10.00);

    @Test
    public void testDeposit() {
        Double amt = 10.00;
        Double expectedAmt = account.checkBalance()+amt;

        account.Deposit(amt);
        Double actualAmt = account.checkBalance();

        assertEquals(expectedAmt, actualAmt);
    }

    @Test
    public void testWithdraw() {
        Double amt = 5.00;
        Double expectedAmt = account.checkBalance()-amt;

        account.withdraw(amt);
        Double actualAmt = account.checkBalance();

        assertEquals(expectedAmt, actualAmt);
    }

    @Test
    public void testBalance() {
        Double expectedBalance = 10.00;
        Double actualBalance = account.checkBalance();
        assertEquals(expectedBalance, actualBalance);
    }

    @Test
    public void testIsEmpty(){
        Double amt = 10.00;
        account.withdraw(10.00);
        assertTrue(account.checkIfEmpty());
    }

    @Test
    public void testIsEmpty2(){
        assertFalse(account.checkIfEmpty());
    }

    @Test
    public void printTransTest1(){
        Double amt = 10.00;
        account.withdraw(10.00);
        account.Deposit(1.00);

        Integer expectedX = 2;
        Integer actualX = account.transHistory.size();

        assertEquals(expectedX,actualX);
    }

    @Test
    public void printTransTest2(){
        Double amt = 10.00;
        account.withdraw(10.00);
        account.Deposit(1.00);

        Integer expectedX = 3;
        Integer actualX = account.transHistory.size();

        assertNotEquals(expectedX,actualX);
    }


}