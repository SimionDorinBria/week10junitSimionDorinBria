package com.siit.junit.calculator;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)
@Suite.SuiteClasses({CalculatorAddAndSubstractionTest.class,
        CalculatorAddTest.class,
        CalculatorSubstractionTest.class,
        CalculatorTyposTest.class,
        CalculatorParameterizedTest.class})

public class CalculatorTestSuite {
}