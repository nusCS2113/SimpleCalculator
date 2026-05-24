package com.calc.calculator;

public class Fraction {
    private double numerator;
    private double denominator;
    private double result;

    public Fraction(double numerator, double denominator) {
        if (denominator == 0) {
            throw new ArithmeticException("Denominator cannot be zero.");
        }

        if (numerator == Math.rint(numerator) || denominator == Math.rint(numerator)) {
            int n = (int) numerator;
            int d = (int) denominator;

            int gcd = gcd(Math.abs(n), Math.abs(d));

            int simplifiedNumerator = n / gcd;
            int simplifiedDenominator = d / gcd;

            if (simplifiedDenominator < 0) {
                simplifiedNumerator *= -1;
                simplifiedDenominator *= -1;
            }

            this.numerator = simplifiedNumerator;
            this.denominator = simplifiedDenominator;
        } else {
            this.numerator = numerator;
            this.denominator = denominator;
        }
        this.result = numerator / denominator;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    @Override
    public String toString() {
        if (denominator == 1) {
            return String.valueOf(numerator);
        }
        if (result == Math.rint(result)) {
            return String.valueOf(result);
        }

        return numerator + "/" + denominator;
    }
}
