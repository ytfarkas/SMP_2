package TransactionManager;

import org.junit.Test;

import static org.junit.Assert.*;

public class AccountDatabaseTest {

    @Test
    public void testTrue() {
        AccountDatabase ac = new AccountDatabase();
        Date date = new Date(8, 27, 2001);
        Profile profile = new Profile("John", "Jones", date);
        Account account = new Checking(profile, 2000);
        ac.open(account);
        assertTrue(ac.close(account));
    }

    @Test
    public void testFalse() {
        AccountDatabase ac = new AccountDatabase();
        Date date = new Date(8, 27, 2001);
        Profile profile = new Profile("John", "Jones", date);
        Account account = new Checking(profile, 2000);
        assertFalse(ac.close(account));
    }
}