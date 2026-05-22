package com.calc.logic.parser;

import java.util.HashMap;
import com.calc.exceptions.InvalidCommandException;
import com.calc.exceptions.InvalidCommandFormatException;
import static com.calc.ui.CalculatorUI;
import com.calc.logic.Command;

/**
 * Command parser for the calculator application. This class is responsible for
 * parsing user input commands. Identifying the operation and operands, and
 * constructing the appropriate command object for execution.
 * 
 * Commands are of the form:
 * - "add 1 and 2"
 * - "subtract 5 from 10"
 * - "multiply 3 and 4"
 * - "divide 10 by 2"
 * 
 * Identifies invalid commands and provides feedback to the user, such as
 * "Invalid command. Please try again." 
 */
public class Parser {
    public Command parseCommand(String command) throws InvalidCommandFormatException {
        
        HashMap<Number, Boolean> operand1 = new HashMap<>();
        HashMap<Number, Boolean> operand2 = new HashMap<>();

        command = command.trim().replaceAll("\\s+", " ");
        String[] tokens = command.toLowerCase().split(" ");
        if (tokens.length < 4) {
            throw new InvalidCommandFormatException("Invalid command format.");
        }

        String operation = tokens[0];
        operand1 = parseNumber(tokens[1]);
        operand2 = parseNumber(tokens[3]);


        Command cmd;

        try {
            switch (operation) {
            case "add" -> {
                cmd = new Command(operation, operand1, operand2);
            }
            case "subtract" -> {
                cmd = new Command(operation, operand1, operand2);
            }
            case "multiply" -> {
                cmd = new Command(operation, operand1, operand2);
            }
            case "divide" -> {
                cmd = new Command(operation, operand1, operand2);
            }
            default -> {
                throw new InvalidCommandException("Unknown operation. Please use add, subtract, multiply, or divide.");
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Invalid numbers. Please ensure you are entering valid integers.");
        }
        return cmd;
    }

    private boolean isInteger(String number) {
        try {
            if(Double.parseDouble(number) == Math.floor(Double.parseDouble(number))) {
                return true;
            }
            return false;
        } catch (NumberFormatException e) {
            System.out.println("Invalid number format. Please enter a valid number.");
            throw new InvalidCommandFormatException("Invalid number format. Please enter a valid number.");
        }
    }

    private HashMap<Number, Boolean> parseNumber(String number) throws InvalidCommandFormatException {
        try {
            if (isInteger(number)) {
                HashMap<Number, Boolean> result = new HashMap<>();
                result.put(Integer.parseInt(number), true);
                return result;
            } else {
                HashMap<Number, Boolean> result = new HashMap<>();
                result.put(Double.parseDouble(number), true);
                return result;
            }
        } catch (NumberFormatException e) {
            throw new InvalidCommandFormatException("Invalid number format. Please enter a valid integer.");
        }
}