package com.siit.junit.calculator;

import com.siit.junit.applicationexception.ApplicationException;
import com.siit.junit.measurementunits.MeasurementUnits;


public class Calculator {
    private String input;
    private MeasurementUnits preferedOutput;

    public Calculator(String input, MeasurementUnits preferedOutput) {
        this.input = input;
        this.preferedOutput = preferedOutput;
    }

    public String getInput() {
        return input;
    }

    public void setInput(String input) {
        this.input = input;
    }

    public MeasurementUnits getPreferedOutput() {
        return preferedOutput;
    }

    public void setPreferedOutput(MeasurementUnits preferedOutput) {
        this.preferedOutput = preferedOutput;
    }

    public String allToUpperCaseInInput(String input) throws ApplicationException {
        if (!input.contains("++") && !input.contains("--") && !input.contains("+-") &&
                !input.contains("-+") && !input.contains("mmm")) {
            return input.toUpperCase();
        } else {
            throw new ApplicationException("Wrong units / or two consecutive signs");
        }
    }

    public String deleteSpacesInInput(String input) throws ApplicationException {
        return input.replace(" ", "");
    }

    public String replaceMinusWithTextMinusAndPlusWithTextPlusInInput(String input) throws ApplicationException {
        input = input.replace("+", "HereYouWillBeSplited+");
        input = input.replace("-", "HereYouWillBeSplited-");
        return input;
    }

    public String replaceMeasurementUnitsWithLowestUnitInInput(String input) throws ApplicationException {
        input = input.replace("MM", "");
        input = input.replace("CM", "0");
        input = input.replace("DM", "00");
        input = input.replace("KM", "000000");
        input = input.replace("M", "000");
        return input;
    }

    public double calculate() throws ApplicationException {
        String processed = replaceMeasurementUnitsWithLowestUnitInInput(
                replaceMinusWithTextMinusAndPlusWithTextPlusInInput(deleteSpacesInInput(
                        allToUpperCaseInInput(this.getInput()))));

        String[] stringArrayFromInput = processed.split("HereYouWillBeSplited");
        double result = 0;

        for (String s : stringArrayFromInput) {
            if (s.matches("((-*)\\d+)|((\\+*)\\d+)")) {
                result += Integer.parseInt(s);
            } else throw new ApplicationException("Not a number");
        }

        switch (this.preferedOutput) {
            case MM:
                return result;
            case CM:
                return result / 10;
            case DM:
                return result / 100;
            case M:
                return result / 1000;
            case KM:
                return result / 1000000;
            default:
                throw new ApplicationException("error");
        }
    }
}