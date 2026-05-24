package com.calc.logic.command;

import com.calc.exceptions.InvalidCommandException;
import com.calc.exceptions.InvalidCommandFormatException;
import com.calc.history.HistoryManager;
import com.calc.logic.parser.Parser;
import com.calc.storage.FileStorage;

public class CommandHandler {
    private final Parser parser = new Parser();
    private Command command;
    private HistoryManager historyManager;
    private FileStorage fileStorage;

    public CommandHandler(FileStorage fileStorage) {
        this.historyManager = HistoryManager.getInstance();
        this.fileStorage = fileStorage;
    }

    public String handleCommand(String input)
            throws InvalidCommandFormatException, InvalidCommandException {
        String result;
        try {
            command = parser.parseCommand(input);
            result = command.execute();
            if (result.toLowerCase().contains("history")) {
                historyManager.addRecord("viewHistory");
            } else {
                historyManager.addRecord(result);
            }
            fileStorage.saveRecord(result);
        } catch (InvalidCommandFormatException e) {
            throw new InvalidCommandFormatException(e.getMessage());
        } catch (InvalidCommandException e) {
            throw new InvalidCommandException(e.getMessage());
        }
        return result;
    }
}
