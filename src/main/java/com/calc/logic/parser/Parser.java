package com.calc.logic.parser;

import com.calc.exceptions.InvalidCommandException;
import com.calc.exceptions.InvalidCommandFormatException;
import com.calc.logic.command.Command;
import org.apache.commons.lang3.tuple.ImmutablePair;

/**
 * Command parser for the calculator application. This class is responsible for
 * parsing user input commands. Identifying the operation and operands, and
 * constructing the appropriate command object for execution.
 * <p>
 * Commands are of the form:
 * - "add 1 and 2"
 * - "subtract 5 from 10"
 * - "multiply 3 and 4"
 * - "divide 10 by 2"
 * <p>
 * Identifies invalid commands and provides feedback to the user, such as
 * "Invalid command. Please try again."
 */
public class Parser {
    public Command parseCommand(String command)
            throws InvalidCommandException, InvalidCommandFormatException {
        String[] tokens = getTokens(command);

        if (tokens[0].toLowerCase().contains("history")) {
            Command cmd = new Command("viewHistory");
            return cmd;
        }

        if (tokens.length < 4) {
            throw new InvalidCommandFormatException("Invalid command format");
        }

        String commandName = tokens[0];
        double operand1 = parseNumber(tokens[1]);
        double operand2 = parseNumber(tokens[3]);

        Command cmd = new Command(commandName, operand1, operand2);
        return cmd;
    }

    public Command parseCommandComplex(String command)
            throws InvalidCommandFormatException, InvalidCommandException {

        String[] tokens = getTokens(command);
        if (tokens.length < 4) {
            throw new InvalidCommandFormatException("Invalid command format.");
        }

        String operation = tokens[0];
        ImmutablePair<Number, Boolean> operand1 = parseNumberComplex(tokens[1]);
        ImmutablePair<Number, Boolean> operand2 = parseNumberComplex(tokens[3]);
        boolean isIntegerOperation = true;

        Integer intOperand1;
        Integer intOperand2;
        Double doubleOperand1;
        Double doubleOperand2;

        // determine if we are working with integers or floating point numbers
        if (!operand1.right || !operand2.right) {
            isIntegerOperation = false;
        }
        Command cmd;
        if (isIntegerOperation) {
            intOperand1 = (Integer) operand1.left;
            intOperand2 = (Integer) operand2.left;
            cmd = new Command(operation, intOperand1, intOperand2);
        } else {
            doubleOperand1 = (Double) operand1.left;
            doubleOperand2 = (Double) operand2.left;
            cmd = new Command(operation, doubleOperand1, doubleOperand2);
        }
        return cmd;
    }

    private static String[] getTokens(String command) {
        command = command.trim().replaceAll("\\s+", " ");
        String[] tokens = command.toLowerCase().split(" ");
        return tokens;
    }

    private boolean isInteger(String number) throws InvalidCommandFormatException {
        try {
            if (Double.parseDouble(number) == Math.floor(Double.parseDouble(number))) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter a valid number.");
            throw new InvalidCommandFormatException(
                    "Invalid number format. Please enter a valid number.");
        }
    }

    private double parseNumber(String number) throws InvalidCommandFormatException {
        try {
            return Double.parseDouble(number);
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Invalid number format. Please enter a valid number.");
        }
    }

    private ImmutablePair<Number, Boolean> parseNumberComplex(String number)
            throws InvalidCommandFormatException {
        try {
            if (isInteger(number)) {
                ImmutablePair<Number, Boolean> result = new ImmutablePair<>(Integer.parseInt(number), true);
                return result;
            } else {
                ImmutablePair<Number, Boolean> result = new ImmutablePair<>(Double.parseDouble(number), false);
                return result;
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException(
                    "Invalid number format. Please enter a valid integer.");
        }
    }
}
