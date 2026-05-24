package com.calc.calculator;

public class Calculator {

    public double sum(double a, double b) {
        return a + b;
    }

    public double difference(double a, double b) {
        return a - b;
    }

    public double product(double a, double b) {
        return a * b;
    }

    public double fraction(double numerator, double denominator) throws ArithmeticException {
        if (denominator == 0) {
            System.out.println("Denom can't be zero");
            throw new ArithmeticException("Denom can't be zero");
        }
        return numerator / denominator;
    }
}
