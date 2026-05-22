package com.calc.logic.command;

import com.calc.exceptions.InvalidCommandFormatException;
import com.calc.logic.parser.Parser;

public class CommandHandler {
    private final Parser parser = new Parser();
    private Command command;

    public void handleCommand(String input) {
        try {
            command = parser.parseCommand(input);
            command.execute();
        } catch (InvalidCommandFormatException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}