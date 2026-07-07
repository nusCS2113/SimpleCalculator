package com.calc.logic.command;

import com.calc.exceptions.InvalidCommandException;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
}
