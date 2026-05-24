package com.calc.logic.command;

public class Command {
    private String commandWord;
    private Number operand1;
    private Number operand2;
    private String inputCommandString;

    public Command(String commandWord, Number operand1, Number operand2) {
        this.commandWord = commandWord;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public void execute() {
        performCalculation(commandWord, operand1.intValue(), operand2.intValue());
    }

    private void performCalculation(String operation, Number a, Number b) {
        String record;

        try {
            switch (operation) {
            case "sum" -> {
                int result = calculator.sum(a, b);
                record = a + " + " + b + " = " + result;
            }
            case "difference" -> {
                int result = calculator.difference(a, b);
                record = a + " - " + b + " = " + result;
            }
            case "product" -> {
                int result = calculator.product(a, b);
                record = a + " * " + b + " = " + result;
            }
            case "fraction" -> {
                String result = calculator.fraction(a, b);
                record = a + " / " + b + " = " + result;
            }
            default -> {
                pause("Unknown operation. Press Enter to continue...");
                return;
            }
            }
        } catch (ArithmeticException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

        }
    }
}


