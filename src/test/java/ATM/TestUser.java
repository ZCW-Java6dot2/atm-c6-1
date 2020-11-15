package ATM;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class TestUser {

    User user = new User("LiquidHero", "password", new Account[0]);
    ArrayList<Account> list = user.getAccounts();

    @Test
    public void closeAccountTest(){
        Account checking = new Checking(0.00);
        list.add(checking);
        user.closeAccount(checking);

        assertFalse(list.contains(checking));
    }

    @Test
    public void openAccountTest(){
        Account checking = new Checking(0.00);
        list.add(checking);
        user.openAccount(checking);

        assertTrue(list.contains(checking));
    }

    @Test
    public void GetNameTest(){
        String expected = "LiquidHero";
        String actual = user.getName();

        assertEquals(expected,actual);
    }

    @Test
    public void getPasswordTest(){
        String expected = "password";
        String actual = user.getPassword();

        assertEquals(expected,actual);
    }

    @Test
    public void TestGetAccount(){
        ArrayList<Account> expected = list;
        ArrayList<Account> actual = user.getAccounts();

        assertEquals(expected,actual);
    }
}
