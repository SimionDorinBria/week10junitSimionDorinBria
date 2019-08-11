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

public class CalculatorAddAndSubstractionTest {
    private Calculator calculator1;
    private Calculator calculator2;
    public static HashMap<String, Duration> durationsCalculatorAddAndSubstractionTest = new HashMap<>();
    private Instant start;
    private String methodName;

    @BeforeClass
    public static void beforeClass() {
    }

    @AfterClass
    public static void afterClass() throws IOException {
        try (
                FileOutputStream file =
                        new FileOutputStream("statistics\\calculatorAddAndSubstractionTest.map");) {
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(durationsCalculatorAddAndSubstractionTest);
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

        durationsCalculatorAddAndSubstractionTest.put(methodName, duration);
    }

    @Test
    public void testAddAndSubstractionWhenOperandsInMMAndCMAndMAndResultInMM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 cm + 1 m - 10 mm", MeasurementUnits.MM);
        assertThat(calculator1.calculate(), is(1090.0));
    }

    @Test
    public void testAddAndSubstractionWhenOperandsAndResultInMM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 mm + 100 mm - 100 mm", MeasurementUnits.MM);
        assertThat(calculator1.calculate(), is(10.0));
    }

    @Test
    public void testAddAndSubstractionWhenOperandsAndResultInCM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 cm + 100 cm - 100 cm", MeasurementUnits.CM);
        assertThat(calculator1.calculate(), is(10.0));
    }

    @Test
    public void testAddAndSubstractionWhenOperandsAndResultInDM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 dm + 100 dm - 100 dm", MeasurementUnits.DM);
        assertThat(calculator1.calculate(), is(10.0));
    }

    @Test
    public void testAddAndSubstractionWhenOperandsAndResultInM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 m + 100 m - 100 m", MeasurementUnits.M);
        assertThat(calculator1.calculate(), is(10.0));
    }

    @Test
    public void testAddAndSubstractionWhenOperandsAndResultInKM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 km + 100 km - 100 km", MeasurementUnits.KM);
        assertThat(calculator1.calculate(), is(10.0));
    }
}