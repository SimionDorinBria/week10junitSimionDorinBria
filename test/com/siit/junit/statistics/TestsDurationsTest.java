package com.siit.junit.statistics;

import com.siit.junit.applicationexception.ApplicationException;
import org.junit.*;

import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class TestsDurationsTest {
    public TestsDurations testsDurations = new TestsDurations();
    public Map<String, Duration> map = new HashMap<String, Duration>();

    @BeforeClass
    public static void beforeClass() {
    }

    @AfterClass
    public static void afterClass() {
    }

    @Before
    public void setUp() {
        map.put("a", Duration.ZERO);
        map.put("b", Duration.ZERO.plusMillis(5));
        map.put("c", Duration.ZERO.plusMillis(10));
    }

    @After
    public void tearDown() {
    }

    @Test
    public void testTheBiggestDuration() throws ApplicationException {
        assertThat(testsDurations.theBiggestDuration(map), is(Duration.ZERO.plusMillis(10)));
    }

    @Test
    public void testTheSmallestDuration() throws ApplicationException {
        assertThat(testsDurations.theSmallestDuration(map), is(Duration.ZERO));
    }

    @Test
    public void testAverageDuration() throws ApplicationException {
        assertThat(testsDurations.averageDuration(map), is(Duration.ZERO.plusMillis(5)));
    }
}