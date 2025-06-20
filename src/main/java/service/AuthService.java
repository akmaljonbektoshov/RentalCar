package service;

import entity.User;

import static entity.Role.*;
import static storage.DB.*;
import static storage.Util.*;

public class AuthService {
    public void service() {
        while (true) {
            System.out.print("""
                    1. Sing in
                    2. Sign up
                    
                    0. Exit
                    >>>\s""");

            switch (strSc.nextLine()) {
                case "0" -> {
                    return;
                }
                case "1" -> {
                    signIn();
                }
                case "2" -> {
                    signUp();
                }
                default -> System.out.println("Please try again.");

            }
        }
    }

    private void signIn() {
        String email = enterString("Enter email: ");
        String password = enterString("Enter password: ");

        users.stream()
                .filter(user -> user.getEmail().equals(email))
                .findFirst()
                .ifPresentOrElse(
                        user -> {
                            if (user.getPassword().equals(password)) {
                                currentUser = user;
                                switch (user.getRole()) {
                                    case USER -> new UserService().userMenu();
                                    case ADMIN -> new AdminService().adminMenu();
                                    default -> System.out.println("An error occurred.");
                                }
                            } else {
                                System.out.println("Incorrect password.");
                            }
                        },
                        () -> System.out.println("Email address not found.")
                );
    }

    private void signUp() {
        User user = new User();

        user.setName(enterString("Enter name: "));
        user.setEmail(enterString("Enter email: "));
        user.setPassword(enterString("Enter password: "));
        user.setRole(USER);

        if (users.add(user)) {
            System.out.println("Registration successful.");
            return;
        }
        System.out.println("Email address already exists.");
    }
}
