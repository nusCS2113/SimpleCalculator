package com.calc.history;

import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private final List<String> history = new ArrayList<>();
    private static HistoryManager historyManager;

    public void addRecord(String record) {
        history.add(record);
    }

    public List<String> getHistory() {
        return history;
    }

    private HistoryManager() {
        System.out.println("HistoryManager is a singleton");
    }

    public static HistoryManager getInstance() {
        if (historyManager == null) {
            historyManager = new HistoryManager();
        }
        return historyManager;
    }
}
