package com.calc.logic.command;

import com.calc.exceptions.InvalidCommandException;
import com.calc.exceptions.InvalidCommandFormatException;
import com.calc.logic.parser.Parser;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class CommandTest {

    @Test
    void execute_add() throws InvalidCommandException {
        Command cmd = new Command("add", 2, 4);
        assertEquals("Result of: 2 + 4 = 6", cmd.execute());
    }

    @Test
    void execute_subtract() throws InvalidCommandException {
        Command cmd = new Command("subtract", 2, 4);
        assertEquals("Result of: 4 - 2 = 2", cmd.execute());
    }

    @Test
    void execute_percentage_returnsFormattedResult()
            throws InvalidCommandException, InvalidCommandFormatException {
        Command cmd = new Parser().parseCommand("percentage 25 of 200");
        assertEquals("Result of: 25% of 200 = 50", cmd.execute());
    }

    @Test
    void execute_percentageFractionalRate_returnsFormattedResult()
            throws InvalidCommandException, InvalidCommandFormatException {
        Command cmd = new Parser().parseCommand("percentage 12.5 of 10");
        assertEquals("Result of: 12.5% of 10 = 1.25", cmd.execute());
    }

    @Test
    void execute_percentageBelowZero_throwsInvalidCommand()
            throws InvalidCommandException, InvalidCommandFormatException {
        Command cmd = new Parser().parseCommand("percentage -1 of 200");
        InvalidCommandException exception = assertThrows(InvalidCommandException.class, cmd::execute);
        assertEquals("Rate must be between 0 and 100", exception.getMessage());
    }

    @Test
    void execute_percentageAboveHundred_throwsInvalidCommand()
            throws InvalidCommandException, InvalidCommandFormatException {
        Command cmd = new Parser().parseCommand("percentage 101 of 200");
        assertThrows(InvalidCommandException.class, cmd::execute);
    }
}
