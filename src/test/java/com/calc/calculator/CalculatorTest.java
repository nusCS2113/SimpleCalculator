package com.calc.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    // nameOfTheMethod_testCondition_expectedOutput
    @Test
    void sum_addTwoNumbers_expectSum() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.sum(2, 3));
    }

    @Test
    void difference_TwoPositiveIntegers_ReturnsCorrectDifference() {
        Calculator calc = new Calculator();

        assertEquals(5, calc.difference(10, 5));
    }

    @Test
    void difference_SubtractFromZero_ReturnsNegativeNumber() {
        Calculator calc = new Calculator();
        assertEquals(-5, calc.difference(0, 5));
    }

    @Test
    void difference_SubtractFromZero_ReturnsZero() {
        Calculator calc = new Calculator();
        assertEquals(0, calc.difference(0, 0));
    }

    @Test
    void difference_SubtractLarger_ReturnsNegativeNumber() {
        Calculator calc = new Calculator();
        assertEquals(-5, calc.difference(5, 10));
    }

    @Test
    void sum_addZeroToNumber_ExpectNumber() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.sum(0, 5));
    }
}

