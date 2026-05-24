package com.calc.logic.command;

import com.calc.calculator.Calculator;
import com.calc.exceptions.InvalidCommandException;
import com.calc.history.HistoryManager;

public class Command {
    private String commandWord;
    private double operand1;
    private double operand2;
    private String record;
    private String inputCommandString;
    private double result;
    private final Calculator calculator = new Calculator();

    public Command(String commandWord, double operand1, double operand2) {
        this.commandWord = commandWord;
        this.operand1 = operand1;
        this.operand2 = operand2;
    }

    public Command(String commandWord) throws InvalidCommandException {
        if (!commandWord.toLowerCase().contains("history")) {
            throw new InvalidCommandException("Invalid command; expecting history command");
        }
        this.operand1 = 0;
        this.operand2 = 0;
        this.commandWord = commandWord;
    }

    public String execute() throws InvalidCommandException {

        if (commandWord.toLowerCase().contains("history")) {
            record = getHistory();
            return record;
        }
        performCalculation(commandWord, operand1, operand2);
        return record;
    }

    private void performCalculation(String operation, double a, double b)
            throws InvalidCommandException {

        try {
            switch (operation) {
            case "add" -> {
                result = calculator.sum(a, b);
            }
            case "subtract" -> {
                result = calculator.difference(b, a);
            }
            case "multiply" -> {
                result = calculator.product(a, b);
            }
            case "divide" -> {
                result = calculator.fraction(a, b);
            }
            default -> {
                throw new InvalidCommandException("Unknown command: " + operation);
            }
            }
            this.record = encodeResult(operation, a, b, result);
        } catch (ArithmeticException e) {
            System.out.println("\nError: " + e.getMessage());
        }
    }

    private static String encodeResult(String operation, double aInput, double bInput, double result)
            throws InvalidCommandException {
        String record = "Result of: ";
        String recordResult = result == Math.rint(result) ?
                String.valueOf((int) result) : String.format("%.2f", result);
        String a = aInput == Math.rint(aInput) ?
                String.valueOf((int) aInput) : String.valueOf(aInput);
        String b = bInput == Math.rint(bInput) ?
                String.valueOf((int) bInput) : String.valueOf(bInput);
        switch (operation) {
        case "add" -> {
            record += a + " + " + b + " = " + recordResult;
        }
        case "subtract" -> {
            record += b + " - " + a + " = " + recordResult;
        }
        case "multiply" -> {
            record += a + " * " + b + " = " + recordResult;
        }
        case "divide" -> {
            record += a + " / " + b + " = " + recordResult;
        }
        default -> {
            throw new InvalidCommandException("Unknown command: " + operation);
        }
        }
        return record;
    }

    private String getHistory() {
        HistoryManager historyManager = HistoryManager.getInstance();

        String history = "This session history: \n";
        if(historyManager.getHistory().size() == 0) {
            history = "History is empty\n";
            return history;
        }
        for (String historyEntry : historyManager.getHistory()) {
            history += historyEntry + "\n";
        }
        return history;
    }
}
