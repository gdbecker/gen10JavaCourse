package gdb.milestone4functionalunittestexercises;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 * @date Thursday December 26, 2019
 * @author garrettbecker
 */

public class AlarmClockTest {
    
    AlarmClock obj = new AlarmClock();
    
    public AlarmClockTest() {
    }
    
    @BeforeAll
    public static void setUpClass() {
    }
    
    @AfterAll
    public static void tearDownClass() {
    }
    
    @BeforeEach
    public void setUp() {
    }
    
    @AfterEach
    public void tearDown() {
    }

    /**
     * Test of alarmClock method, of class AlarmClock.
     */
    @Test
    public void test1False() {
        assertEquals("7:00", obj.alarmClock(1, false));
    }
    
    @Test
    public void test5False() {
        assertEquals("7:00", obj.alarmClock(5, false));
    }
    
    @Test
    public void test0False() {
        assertEquals("10:00", obj.alarmClock(0, false));
    }
    
}
