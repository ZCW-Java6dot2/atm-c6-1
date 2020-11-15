package ATM;

import org.junit.Assert;
import org.junit.Test;

import static org.junit.Assert.*;

public class TestAccount {
    Account account = new Account();

    @Test
    public void testDeposit() {
        account.balance = 0.0;
        account.Deposit(10.00);
        Double expectedAmt = 10.00;

        assertEquals(expectedAmt, account.balance);
    }

    @Test
    public void testWithdraw() {
        account.balance = 20.00;
        account.withdraw(15.00);
        Double expectedAmt = 5.00;

        assertEquals(expectedAmt, account.balance);
    }

    @Test
    public void testBalance() {
        account.balance = 20.00;
        Double expectedBalance = 20.00;
        assertEquals(expectedBalance, account.checkBalance());
    }

    @Test
    public void testIsEmpty(){
        account.balance = 0.00;
        assertTrue(account.checkIfEmpty());
    }

    @Test
    public void testIsEmpty2(){
        account.balance = 1.00;
        assertFalse(account.checkIfEmpty());
    }

}