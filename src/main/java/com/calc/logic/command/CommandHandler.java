package com.calc.logic.command;

import com.calc.exceptions.InvalidCommandException;
import com.calc.exceptions.InvalidCommandFormatException;
import com.calc.logic.parser.Parser;

public class CommandHandler {
    private final Parser parser = new Parser();
    private Command command;

    public void handleCommand(String input)
            throws InvalidCommandFormatException, InvalidCommandException {
        try {
            command = parser.parseCommand(input);
            command.execute();
        } catch (InvalidCommandFormatException e) {
            throw new InvalidCommandFormatException(e.getMessage());
        } catch (InvalidCommandException e) {
            throw new InvalidCommandException(e.getMessage());
        }
    }
}