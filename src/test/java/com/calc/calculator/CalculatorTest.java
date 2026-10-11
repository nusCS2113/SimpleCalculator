package com.calc.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CalculatorTest {

    // nameOfTheMethod_testCondition_expectedOutput
    @Test
    void sum_addTwoNumbers_expectSum() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.sum(2, 3));
    }

    @Test
    void difference_twoPositiveIntegers_returnsCorrectDifference() {
        Calculator calc = new Calculator();

        assertEquals(5, calc.difference(10, 5));
    }

    @Test
    void difference_subtractFromZero_returnsNegativeNumber() {
        Calculator calc = new Calculator();
        assertEquals(-5, calc.difference(0, 5));
    }

    @Test
    void difference_subtractFromZero_returnsZero() {
        Calculator calc = new Calculator();
        assertEquals(0, calc.difference(0, 0));
    }

    @Test
    void difference_subtractLarger_returnsNegativeNumber() {
        Calculator calc = new Calculator();
        assertEquals(-5, calc.difference(5, 10));
    }

    @Test
    void sum_addZeroToNumber_expectNumber() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.sum(0, 5));
    }

    @Test
    void percentage_validRate_returnsPercentage() {
        Calculator calculator = new Calculator();
        assertEquals(100, calculator.percentage(200, 50));
    }

    @Test
    void percentage_belowZero_throwsException() {
        Calculator calculator = new Calculator();
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class,
                () -> calculator.percentage(200, -1));
        assertEquals("Rate must be between 0 and 100", exception.getMessage());
    }

    @Test
    void percentage_aboveHundred_throwsException() {
        Calculator calculator = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> calculator.percentage(200, 101));
    }

    @Test
    void percentage_boundaryRates_returnsExpectedAmount() {
        Calculator calculator = new Calculator();
        assertEquals(0, calculator.percentage(200, 0));
        assertEquals(200, calculator.percentage(200, 100));
    }

    @Test
    void percentage_fractionalRate_returnsPercentage() {
        Calculator calculator = new Calculator();
        assertEquals(25, calculator.percentage(200, 12.5), 1e-9);
    }

    @Test
    void percentage_negativeAmount_returnsNegativePercentage() {
        Calculator calculator = new Calculator();
        assertEquals(-50, calculator.percentage(-200, 25));
    }

    @Test
    void percentage_largeAmount_avoidsIntermediateOverflow() {
        Calculator calculator = new Calculator();
        assertEquals(Double.MAX_VALUE, calculator.percentage(Double.MAX_VALUE, 100));
    }

    @Test
    void percentage_nonFiniteRate_throwsException() {
        Calculator calculator = new Calculator();
        assertThrows(IllegalArgumentException.class, () -> calculator.percentage(200, Double.NaN));
        assertThrows(IllegalArgumentException.class, () -> calculator.percentage(200, Double.NEGATIVE_INFINITY));
        assertThrows(IllegalArgumentException.class, () -> calculator.percentage(200, Double.POSITIVE_INFINITY));
    }
}

