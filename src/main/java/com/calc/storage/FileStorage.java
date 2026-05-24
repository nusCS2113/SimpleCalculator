package com.calc.storage;

import java.io.FileWriter;
import java.io.IOException;

public class FileStorage {
    private final String fileName;

    public FileStorage(String fileName) {
        this.fileName = fileName;
    }

    public void saveRecord(String record) {
        try (FileWriter writer = new FileWriter(fileName, false)) {
            writer.write(record + System.lineSeparator());
        } catch (IOException e) {
            System.out.println("Could not save record: " + e.getMessage());
        }
    }
}
