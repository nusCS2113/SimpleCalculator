package com.calc;

import com.calc.history.HistoryManager;
import com.calc.storage.FileStorage;
import com.calc.ui.CalculatorUI;

public class CalculatorMain {
    static HistoryManager historyManager;
    static FileStorage fileStorage = new FileStorage("calculator_history.txt");

    public static void main(String[] args) {
        run();
    }

    public static void run() {
        historyManager = HistoryManager.getInstance();
        CalculatorUI ui = new CalculatorUI(fileStorage);
        ui.runRepl();
    }
}
