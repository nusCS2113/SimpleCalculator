package com.calc.history;

import java.util.ArrayList;
import java.util.List;

public class HistoryManager {
    private static HistoryManager historyManager;
    private final List<String> history = new ArrayList<>();

    private HistoryManager() {
        System.out.println("HistoryManager is a singleton");
    }

    public void addRecord(String record) {
        history.add(record);
    }

    public List<String> getHistory() {
        return history;
    }

    public static HistoryManager getInstance() {
        if (historyManager == null) {
            historyManager = new HistoryManager();
        }
        return historyManager;
    }
}
