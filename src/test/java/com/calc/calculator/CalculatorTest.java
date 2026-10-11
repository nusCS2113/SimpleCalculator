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

    @Test
    void product_twoPositiveNumbers_returnsProduct() {
        Calculator calculator = new Calculator();
        assertEquals(12, calculator.product(3, 4));
    }

    @Test
    void product_oppositeSigns_returnsNegativeProduct() {
        Calculator calculator = new Calculator();
        assertEquals(-12, calculator.product(-3, 4));
        assertEquals(-12, calculator.product(3, -4));
    }

    @Test
    void product_twoNegativeNumbers_returnsPositiveProduct() {
        Calculator calculator = new Calculator();
        assertEquals(12, calculator.product(-3, -4));
    }

    @Test
    void product_zeroOperand_returnsZero() {
        Calculator calculator = new Calculator();
        assertEquals(0, calculator.product(0, 5));
        assertEquals(0, calculator.product(5, 0));
        assertEquals(0, calculator.product(0, 0));
    }

    @Test
    void product_decimalOperands_returnsProduct() {
        Calculator calculator = new Calculator();
        assertEquals(0.02, calculator.product(0.1, 0.2), 1e-9);
    }

    @Test
    void fraction_twoPositiveNumbers_returnsQuotient() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.fraction(10, 2));
    }

    @Test
    void fraction_negativeNumerator_returnsNegativeQuotient() {
        Calculator calculator = new Calculator();
        assertEquals(-5, calculator.fraction(-10, 2));
    }

    @Test
    void fraction_negativeDenominator_returnsNegativeQuotient() {
        Calculator calculator = new Calculator();
        assertEquals(-5, calculator.fraction(10, -2));
    }

    @Test
    void fraction_twoNegativeNumbers_returnsPositiveQuotient() {
        Calculator calculator = new Calculator();
        assertEquals(5, calculator.fraction(-10, -2));
    }

    @Test
    void fraction_zeroNumerator_returnsZero() {
        Calculator calculator = new Calculator();
        assertEquals(0, calculator.fraction(0, 5));
    }

    @Test
    void fraction_nonDivisibleIntegers_returnsFractionalQuotient() {
        Calculator calculator = new Calculator();
        assertEquals(0.4, calculator.fraction(2, 5), 1e-9);
    }

    @Test
    void fraction_decimalOperands_returnsQuotient() {
        Calculator calculator = new Calculator();
        assertEquals(3, calculator.fraction(0.3, 0.1), 1e-9);
    }

    @Test
    void fraction_zeroDenominator_throwsArithmeticException() {
        Calculator calculator = new Calculator();
        ArithmeticException exception = assertThrows(ArithmeticException.class,
                () -> calculator.fraction(5, 0));
        assertEquals("Denom can't be zero", exception.getMessage());
        assertThrows(ArithmeticException.class, () -> calculator.fraction(5, -0.0));
        assertThrows(ArithmeticException.class, () -> calculator.fraction(0, 0));
    }
}

