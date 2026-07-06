package com.calc.history;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class HistoryManagerTest {

    @Test
    void addRecord_SingleRecord_SizeOne() {
        HistoryManager historyManager = HistoryManager.getInstance();
        historyManager.addRecord("1");

        assertEquals(historyManager.getHistory().size(), 1);
    }

    @Test
    void getHistory() {
        HistoryManager historyManager = HistoryManager.getInstance();
        historyManager.addRecord("2");

        assertEquals(historyManager.getHistory().size(), 2);
    }
}