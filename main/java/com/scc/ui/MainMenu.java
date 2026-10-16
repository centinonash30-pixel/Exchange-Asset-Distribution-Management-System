package ui;

import dao.UserDao;
import model.User;

import java.util.Optional;
import java.util.Scanner;

public class MainMenu {

    private final Scanner scanner = new Scanner(System.in);
    private final UserDao userDao = new UserDao();

    public void run() {
        boolean running = true;
        while (running) {
            System.out.println("\n=== Exchange Asset & Distribution Management System ===");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            switch (scanner.nextLine().trim()) {
                case "1" -> register();
                case "2" -> login();
                case "3" -> {
                    System.out.println("Goodbye!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }
        }
    }

    private void register() {
        System.out.println("\n--- Register ---");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        if (username.isEmpty() || password.isEmpty()) {
            System.out.println("Username and password cannot be empty.");
            return;
        }

        System.out.println("Role: 1. Administrator  2. Inventory Staff  3. Employee/Requester");
        System.out.print("Choose: ");
        String role = switch (scanner.nextLine().trim()) {
            case "1" -> "ADMIN";
            case "2" -> "INVENTORY_STAFF";
            case "3" -> "EMPLOYEE";
            default -> null;
        };
        if (role == null) {
            System.out.println("Invalid role.");
            return;
        }

        if (userDao.register(username, password, role)) {
            System.out.println("Account created! Status: PENDING.");
        } else {
            System.out.println("Username already taken.");
        }
    }

    private void login() {
        System.out.println("\n--- Login ---");
        System.out.print("Username: ");
        String username = scanner.nextLine().trim();
        System.out.print("Password: ");
        String password = scanner.nextLine();

        Optional<User> user = userDao.login(username, password);
        if (user.isPresent()) {
            new DashboardMenu(scanner).show(user.get());
        } else {
            System.out.println("Invalid username or password.");
        }
    }
}