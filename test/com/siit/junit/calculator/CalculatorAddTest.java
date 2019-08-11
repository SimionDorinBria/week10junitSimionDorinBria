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

public class CalculatorAddTest {
    private Calculator calculator1;
    private Calculator calculator2;
    public static HashMap<String, Duration> durationsCalculatorAddTest = new HashMap<>();
    private Instant start;
    private String methodName;

    @BeforeClass
    public static void beforeClass() {
    }

    @AfterClass
    public static void afterClass() throws IOException {
        try (
                FileOutputStream file =
                        new FileOutputStream("statistics\\calculatorAddTest.map");) {
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(durationsCalculatorAddTest);
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

        durationsCalculatorAddTest.put(methodName, duration);
    }

    @Test
    public void testAddWhenOperandsAndResultInMM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 mm + 100 mm + 100 mm", MeasurementUnits.MM);
        assertThat(calculator1.calculate(), is(210.0));
    }

    @Test
    public void testAddWhenOperandsInMMAndResultInCM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 mm + 100 mm + 100 mm", MeasurementUnits.CM);
        assertThat(calculator1.calculate(), is(21.0));
    }

    @Test
    public void testAddWhenOperandsInMMAndResultInDM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 mm + 100 mm + 100 mm", MeasurementUnits.DM);
        assertThat(calculator1.calculate(), is(2.1));
    }

    @Test
    public void testAddWhenOperandsInMMAndResultInM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 mm + 100 mm + 100 mm", MeasurementUnits.M);
        assertThat(calculator1.calculate(), is(0.21));
    }

    @Test
    public void testAddWhenOperandsInMMAndResultInKM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 mm + 100 mm + 100 mm", MeasurementUnits.KM);
        assertThat(calculator1.calculate(), is(0.00021));
    }

    @Test
    public void testAddWhenOperandsAndResultInCM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 cm + 100 cm + 100 cm", MeasurementUnits.CM);
        assertThat(calculator1.calculate(), is(210.0));
    }

    @Test
    public void testAddWhenOperandsAndResultInDM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 dm + 100 dm + 100 dm", MeasurementUnits.DM);
        assertThat(calculator1.calculate(), is(210.0));
    }

    @Test
    public void testAddWhenOperandsAndResultInM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 m + 100 m + 100 m", MeasurementUnits.M);
        assertThat(calculator1.calculate(), is(210.0));
    }

    @Test
    public void testAddWhenOperandsAndResultInKM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 km + 100 km + 100 km", MeasurementUnits.KM);
        assertThat(calculator1.calculate(), is(210.0));
    }
}