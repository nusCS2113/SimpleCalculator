package com.calc.ui;

import com.calc.calculator.Calculator;
import com.calc.exceptions.InvalidCommandException;
import com.calc.exceptions.InvalidCommandFormatException;
import com.calc.history.HistoryManager;
import com.calc.logic.command.CommandHandler;
import com.calc.storage.FileStorage;

import java.util.Scanner;

public class CalculatorUI {
    private final Scanner scanner = new Scanner(System.in);
    private final Calculator calculator = new Calculator();
    private final HistoryManager historyManager = new HistoryManager();
    private final FileStorage fileStorage = new FileStorage("calculator_history.txt");

    public void help() {
        System.out.printf("%s%n", "Available commands:");
        System.out.printf("%s%n", "1. Add - Adds two numbers: format add 1 and 2%n");
        System.out.println("2. Subtract - Subtracts second number from first: format subtract 5 from 10");
        System.out.println("3. Multiply - Multiplies two numbers: format multiply 3 and 4");
        System.out.println("4. Divide - Divides first number by second: format divide 10 by 2");
        System.out.println("5. View History - Displays calculation history");
        System.out.println("6. Help - Displays help message");
        System.out.println("7. Exit - Closes the calculator");
    }

    public void start() {
        boolean running = true;

        CommandHandler commandHandler = new CommandHandler();

        while (running) {
            clearScreen();
            showHeader();

            String commandString = readInputFromTerminal();

            if (commandString.equals("help")) {
                help();
                continue;
            }

            if (commandString.equalsIgnoreCase("exit")) {
                System.out.println("Exiting...");
                running = false;
            }

            try {
                commandHandler.handleCommand(commandString);
            } catch (InvalidCommandException e) {
                System.out.println("Invalid command encountered: " + e.getMessage());
                running = false;
            } catch (InvalidCommandFormatException e) {
                System.out.println("Invalid command format: " + e.getMessage());
                running = false;
            }
        }

        System.out.println("Calculator closed.");
    }

    private void performCalculation(String operation) {
        clearScreen();
        showHeader();

        System.out.print("Enter first number: ");
        int a = readInt();

        System.out.print("Enter second number: ");
        int b = readInt();

        String record;

        try {
            switch (operation) {
            case "sum" -> {
                int result = calculator.sum(a, b);
                record = a + " + " + b + " = " + result;
            }
            case "difference" -> {
                int result = calculator.difference(a, b);
                record = a + " - " + b + " = " + result;
            }
            case "product" -> {
                int result = calculator.product(a, b);
                record = a + " * " + b + " = " + result;
            }
            case "fraction" -> {
                String result = calculator.fraction(a, b);
                record = a + " / " + b + " = " + result;
            }
            default -> {
                pause("Unknown operation. Press Enter to continue...");
                return;
            }
            }

            System.out.println("\nResult: " + record);

            historyManager.addRecord(record);
            fileStorage.saveRecord(record);

        } catch (ArithmeticException e) {
            System.out.println("\nError: " + e.getMessage());
        }

        pause("\nPress Enter to continue...");
    }

    private void showHistory() {
        clearScreen();
        showHeader();

        System.out.println("Calculation History");
        System.out.println("-------------------");

        if (historyManager.getHistory().isEmpty()) {
            System.out.println("No calculations yet.");
        } else {
            for (String record : historyManager.getHistory()) {
                System.out.println(record);
            }
        }

        pause("\nPress Enter to continue...");
    }

    private void showHeader() {
        System.out.println("==============================");
        System.out.println("       SIMPLE CALCULATOR       ");
        System.out.println("==============================\n");
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private int readInt() {
        while (!scanner.hasNextInt()) {
            System.out.print("Invalid input. Enter an integer: ");
            scanner.next();
        }
        return scanner.nextInt();
    }

    //ignore blank lines and read next line
    private String readInputFromTerminal() {
        while (scanner.hasNextLine() && scanner.nextLine().isBlank()) {
            // ignore blank lines
        }
        scanner.nextLine();
        return scanner.nextLine();
    }

    private void pause(String message) {
        System.out.print(message);
        scanner.nextLine();
        scanner.nextLine();
    }
}
