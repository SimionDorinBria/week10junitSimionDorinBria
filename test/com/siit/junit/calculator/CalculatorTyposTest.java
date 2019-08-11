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
import java.util.Map;

public class CalculatorTyposTest {
    private Calculator calculator1;
    private Calculator calculator2;
    public static Map<String, Duration> durationsCalculatorTyposTest = new HashMap<>();
    private Instant start;
    private String methodName;

    public static Map<String, Duration> getDurationsCalculatorTyposTest() {
        return durationsCalculatorTyposTest;
    }

    public static void setDurationsCalculatorTyposTest(Map<String, Duration> durationsCalculatorTyposTest) {
        CalculatorTyposTest.durationsCalculatorTyposTest = durationsCalculatorTyposTest;
    }

    @BeforeClass
    public static void beforeClass() {
    }

    @AfterClass
    public static void afterClass() throws IOException {
        try (
                FileOutputStream file =
                        new FileOutputStream("statistics\\calculatorTyposTest.map");) {
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(durationsCalculatorTyposTest);
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

        durationsCalculatorTyposTest.put(methodName, duration);
    }

    @Test(expected = ApplicationException.class)
    public void testInputContainsACharacterDifferentFrom0_9_Or_MCDK() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 cm + 1 m - 10 mmq", MeasurementUnits.MM);
        calculator1.calculate();
    }

    @Test(expected = ApplicationException.class)
    public void testInputContainsTwoConsecutiveAddSigns() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 mm ++ 100 mm - 100 mm", MeasurementUnits.MM);
        calculator1.calculate();
    }

    @Test(expected = ApplicationException.class)
    public void testInputContainsTwoConsecutiveSubstractionSigns() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 mm -- 100 mm - 100 mm", MeasurementUnits.MM);
        calculator1.calculate();
    }

    @Test(expected = ApplicationException.class)
    public void testInputContainsDifferentUnitsFromMM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 mmm - 100 mm - 100 mm", MeasurementUnits.MM);
        calculator1.calculate();
    }

    @Test(expected = ApplicationException.class)
    public void testInputContainsDifferentUnitsFromCM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 ccm - 100 mm - 100 mm", MeasurementUnits.MM);
        calculator1.calculate();
    }

    @Test(expected = ApplicationException.class)
    public void testInputContainsDifferentUnitsFromDM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 cdm - 100 mm - 100 mm", MeasurementUnits.MM);
        calculator1.calculate();
    }

    @Test(expected = ApplicationException.class)
    public void testInputContainsDifferentUnitsFromM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 zm - 100 mm - 100 mm", MeasurementUnits.MM);
        calculator1.calculate();
    }

    @Test(expected = ApplicationException.class)
    public void testInputContainsDifferentUnitsFromKM() throws ApplicationException {
        methodName = new Throwable().getStackTrace()[0].getMethodName();

        calculator1 = new Calculator("10 kma - 100 mm - 100 mm", MeasurementUnits.MM);
        calculator1.calculate();
    }
}