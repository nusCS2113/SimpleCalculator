package com.calc.calculator;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CalculatorTest {

    @Test
    void sum_addTwoNumbers_expectSum() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.sum(2, 3));

    }

    @Test
    void sum_addZeroToNumber_ExpectNumber() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.sum(0, 5));
    }
}
