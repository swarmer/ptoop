package me.swarmer.ptoop.lab3.ui;


import java.util.*;


public class ConsoleMenu {
    private List<Runnable> menuItemHandlers = new ArrayList<>();
    private List<String> menuItemDescriptions = new ArrayList<>();
    private Scanner scanner;
    private String greeting;

    public ConsoleMenu(Scanner scanner, String greeting) {
        this.scanner = scanner;
        this.greeting = greeting;
    }

    public void addEntry(String description, Runnable runnable) {
        menuItemHandlers.add(runnable);
        menuItemDescriptions.add(description);
    }

    public void askOnce() {
        while (true) {
            try {
                System.out.println(greeting);

                for (int i = 0; i < menuItemDescriptions.size(); ++i) {
                    String description = menuItemDescriptions.get(i);
                    System.out.printf("%d. %s\n", i + 1, description);
                }

                System.out.print("Choice: ");
                String input = scanner.nextLine();
                int inputNumber = Integer.parseInt(input) - 1;

                Runnable runnable = menuItemHandlers.get(inputNumber);
                runnable.run();

                break;
            } catch (NumberFormatException | IndexOutOfBoundsException ex) {
                System.out.println("Invalid entry, try again");
            }
        }
    }

    public void askForever() {
        while (true) {
            askOnce();
            System.out.println();
        }
    }
}
