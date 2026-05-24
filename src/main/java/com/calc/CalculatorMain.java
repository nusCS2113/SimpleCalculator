package com.calc;

import com.calc.history.HistoryManager;
import com.calc.storage.FileStorage;
import com.calc.ui.CalculatorUI;

public class CalculatorMain {
    static HistoryManager historyManager = new HistoryManager();
    static FileStorage fileStorage = new FileStorage("calculator_history.txt");

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        historyManager = new HistoryManager();
        CalculatorUI ui = new CalculatorUI(historyManager, fileStorage);
        ui.runRepl();
    }
}
