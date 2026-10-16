package ui;

import model.User;

import java.util.Scanner;

public class DashboardMenu {

    private final Scanner scanner;

    public DashboardMenu(Scanner scanner) {
        this.scanner = scanner;
    }

    public void show(User user) {
        System.out.println("\nWelcome, " + user.username() + "! (" + user.roles() + ")");
        boolean loggedIn = true;
        while (loggedIn) {
            System.out.println("\n--- Dashboard ---");
            System.out.println("1. View my profile");
            System.out.println("2. Logout");
            System.out.print("Choose: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> {
                    System.out.println("ID:       " + user.id());
                    System.out.println("Username: " + user.username());
                    System.out.println("Role:     " + user.roles());
                    System.out.println("Status:   " + user.status());
                }
                case "2" -> {
                    System.out.println("Logged out. Returning to main menu...");
                    loggedIn = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }
}