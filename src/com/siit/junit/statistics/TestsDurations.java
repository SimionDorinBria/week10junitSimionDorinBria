package com.siit.junit.statistics;

import com.siit.junit.applicationexception.ApplicationException;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

public class TestsDurations {


    private Map<String, Duration> allTestsDurations;

    public TestsDurations() {
        Map<String, Duration> allTestsDurations = new HashMap<>();
    }

    public static HashMap<String, Duration> allTestsDurations() throws ApplicationException {
        Map<String, Duration> allTestsDurations = new HashMap<>();
        Map<String, Duration> calculatorTyposTest = new HashMap<>();
        Map<String, Duration> calculatorParameterizedTest = new HashMap<>();
        Map<String, Duration> calculatorSubstractionTest = new HashMap<>();
        Map<String, Duration> calculatorAddTest = new HashMap<>();
        Map<String, Duration> calculatorAddAndSubstractionTest = new HashMap<>();


        try (
                FileInputStream file = new FileInputStream("statistics\\calculatorTyposTest.map");
                ObjectInputStream in = new ObjectInputStream(file);) {
            calculatorTyposTest = (HashMap) in.readObject();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

        try (
                FileInputStream file = new FileInputStream("statistics\\calculatorParameterizedTest.map");
                ObjectInputStream in = new ObjectInputStream(file);) {
            calculatorParameterizedTest = (HashMap) in.readObject();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

        try (
                FileInputStream file = new FileInputStream("statistics\\calculatorSubstractionTest.map");
                ObjectInputStream in = new ObjectInputStream(file);) {
            calculatorSubstractionTest = (HashMap) in.readObject();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

        try (
                FileInputStream file = new FileInputStream("statistics\\calculatorAddTest.map");
                ObjectInputStream in = new ObjectInputStream(file);) {
            calculatorAddTest = (HashMap) in.readObject();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

        try (
                FileInputStream file = new FileInputStream("statistics\\calculatorAddAndSubstractionTest.map");
                ObjectInputStream in = new ObjectInputStream(file);) {
            calculatorAddAndSubstractionTest = (HashMap) in.readObject();
        } catch (IOException | ClassNotFoundException i) {
            i.printStackTrace();
        }

        allTestsDurations.putAll(calculatorAddAndSubstractionTest);
        allTestsDurations.putAll(calculatorAddTest);
        allTestsDurations.putAll(calculatorParameterizedTest);
        allTestsDurations.putAll(calculatorSubstractionTest);
        allTestsDurations.putAll(calculatorTyposTest);

        return (HashMap<String, Duration>) allTestsDurations;
    }

    public Duration theBiggestDuration(Map<String, Duration> map) {
        Duration max = Duration.ZERO;
        for (Map.Entry<String, Duration> pair : map.entrySet()) {
            if (pair.getValue().compareTo(max) > 0) {
                max = pair.getValue();
            }
        }
        return max;
    }

    public Duration theSmallestDuration(Map<String, Duration> map) {
        Duration min = theBiggestDuration(map);
        for (Map.Entry<String, Duration> pair : map.entrySet()) {
            if (pair.getValue().compareTo(min) < 0) {
                min = pair.getValue();
            }
        }
        return min;
    }

    public Duration averageDuration(Map<String, Duration> map) {
        Duration average = Duration.ZERO;
        Duration total = Duration.ZERO;
        for (Map.Entry<String, Duration> pair : map.entrySet()) {
            total = total.plus(pair.getValue());
        }
        if (total.isZero()) {
            return average;
        } else {
            return total.dividedBy(map.size());
        }
    }
}