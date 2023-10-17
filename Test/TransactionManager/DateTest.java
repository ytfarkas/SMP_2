package TransactionManager;

import org.junit.Test;

import static org.junit.Assert.*;

public class DateTest {

    @Test
    public void valid1() {
        Date valid1 = new Date(2, 6, 2000);
        assertTrue(valid1.isValid());


    }
@Test
        public void valid2() {
        Date valid2 = new Date(8, 27, 2001);
        assertTrue(valid2.isValid());
    }

    @Test
    public void valid3() {
        Date valid3 = new Date(7, 19, 2020);
        assertTrue(valid3.isValid());


    }
    @Test
    public void valid4() {
        Date valid4 = new Date(2, 29, 2008);
        assertTrue(valid4.isValid());
    }
    @Test
    public void valid5() {
        Date valid5 = new Date(3, 29, 2011);
        assertTrue(valid5.isValid());
    }

    @Test
    public void invalid1() {
        Date invalid1 = new Date(2, 29, 2009);
        assertFalse(invalid1.isValid());
    }
    @Test
    public void invalid2() {
        Date invalid2 = new Date(12, 4, 2024);
        assertFalse(invalid2.isValid());
    }
}