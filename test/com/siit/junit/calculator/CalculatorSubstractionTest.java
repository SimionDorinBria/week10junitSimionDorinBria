package com.siit.junit.calculator;

import com.siit.junit.applicationexception.ApplicationException;
import com.siit.junit.measurementunits.MeasurementUnits;
import org.junit.*;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

public class CalculatorSubstractionTest {
    private Calculator calculator1;
    private Calculator calculator2;
    private static HashMap<String, Duration> durationsCalculatorSubstractionTest = new HashMap<>();
    private Instant start;
    private String methodName;

    @BeforeClass
    public static void beforeClass() {
    }

    @AfterClass
    public static void afterClass() throws IOException {
        try (
                FileOutputStream file =
                        new FileOutputStream("statistics\\calculatorSubstractionTest.map");) {
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(durationsCalculatorSubstractionTest);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Before
    public void setUp() throws ApplicationException {
        start = Instant.now();
    }

    @After
    public void tearDown() throws ApplicationException {
        Instant stop = Instant.now();
        Duration duration = Duration.between(start, stop);

        durationsCalculatorSubstractionTest.put(methodName, duration);
    }

    @Test
    public void testSubstractionWhenOperandsAndResultInMM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 mm - 100 mm - 100 mm", MeasurementUnits.MM);
        assertThat(calculator1.calculate(), is(-190.0));
    }

    @Test
    public void testSubstractionWhenOperandsAndResultInCM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 cm - 100 cm - 100 cm", MeasurementUnits.CM);
        assertThat(calculator1.calculate(), is(-190.0));
    }

    @Test
    public void testSubstractionWhenOperandsAndResultInDM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 dm - 100 dm - 100 dm", MeasurementUnits.DM);
        assertThat(calculator1.calculate(), is(-190.0));
    }

    @Test
    public void testSubstractionWhenOperandsAndResultInM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 m - 100 m - 100 m", MeasurementUnits.M);
        assertThat(calculator1.calculate(), is(-190.0));
    }

    @Test
    public void testSubstractionWhenOperandsAndResultInKM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 km - 100 km - 100 km", MeasurementUnits.KM);
        assertThat(calculator1.calculate(), is(-190.0));
    }
}