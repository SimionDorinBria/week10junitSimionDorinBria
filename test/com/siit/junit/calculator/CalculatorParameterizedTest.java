package com.siit.junit.calculator;

import com.siit.junit.applicationexception.ApplicationException;
import com.siit.junit.measurementunits.MeasurementUnits;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.time.Duration;
import java.time.Instant;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Parameterized.class)
public class CalculatorParameterizedTest {

    private Calculator calculator1;
    private String input;
    private MeasurementUnits preferedOutput;
    private double expected;
    public static HashMap<String, Duration> durationsCalculatorParameterizedTest = new HashMap<>();

    @Parameterized.Parameters
    public static List<Object> data() {
        return Arrays.asList(new Object[][]{
                {"10 cm + 1 m - 10 mm", MeasurementUnits.MM, 1090.0},
                {"60 mm - 60 mm", MeasurementUnits.MM, 0.0},
                {"10 cm + 10 cm + 80 cm", MeasurementUnits.CM, 100.0},
                {"10 dm - 10 dm - 100 dm", MeasurementUnits.DM, -100.0}
        });
    }

    public CalculatorParameterizedTest(String input, MeasurementUnits preferedOutput, double expected) {
        this.input = input;
        this.preferedOutput = preferedOutput;
        this.expected = expected;
        this.calculator1 = new Calculator(input, preferedOutput);
    }

// imi pune in fisierul map doar ultimul din CalculatorParameterizedTest (4 teste, 1 pozitie in map)

    public static void afterTest() throws IOException {
        try (
                FileOutputStream file =
                        new FileOutputStream("statistics\\calculatorParameterizedTest.map");) {
            ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(durationsCalculatorParameterizedTest);
        } catch (IOException i) {
            i.printStackTrace();
        }
    }

    @Test
    public void testAddAndSubstraction() throws ApplicationException, IOException {
        String methodName = new Throwable().getStackTrace()[0].getMethodName();

        Instant start = Instant.now();

        assertThat(calculator1.calculate(), is(expected));

        Instant stop = Instant.now();
        Duration duration = Duration.between(start, stop);

        durationsCalculatorParameterizedTest.put(methodName, duration);

        afterTest();
    }
}