package io.codeforall.fanstatics;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.bootcamp.scanners.string.StringInputScanner;

import java.util.LinkedList;

public class Sandbox {
    public static void main(String[] args) {

        boolean connected = true;
        LinkedList<UserCredentials> bankData = new LinkedList<>();

        Prompt prompt = new Prompt(System.in, System.out);

        while (connected) {

            String[] loginOptions = {"Login", "Create account", "Exit"};
            MenuInputScanner menu = new MenuInputScanner(loginOptions);
            menu.setMessage("Welcome to the bank! Please choose an option:");

            int answerIndex = prompt.getUserInput(menu);

            switch (answerIndex) {
                case 1:
                    // Login
                    StringInputScanner usernamePrompt = new StringInputScanner();
                    usernamePrompt.setMessage("Enter your username:");

                    StringInputScanner passwordPrompt = new StringInputScanner();
                    passwordPrompt.setMessage("Enter your password:");

                    String enteredUsername = prompt.getUserInput(usernamePrompt);
                    String enteredPassword = prompt.getUserInput(passwordPrompt);

                    boolean loginSuccessful = false;
                    for (UserCredentials user : bankData) {
                        if (user.getUsername().equals(enteredUsername) && user.getPassword().equals(enteredPassword)) {
                            System.out.println("Welcome " + enteredUsername);
                            loginSuccessful = true;
                            break;
                        }
                    }

                    if (!loginSuccessful) {
                        System.out.println("Username or password incorrect");
                    }
                    break;

                case 2:
                    // Create account
                    StringInputScanner createUsernamePrompt = new StringInputScanner();
                    createUsernamePrompt.setMessage("Create a username:");

                    StringInputScanner createPasswordPrompt = new StringInputScanner();
                    createPasswordPrompt.setMessage("Create a strong password:");

                    String newUsername = prompt.getUserInput(createUsernamePrompt);
                    String newPassword = prompt.getUserInput(createPasswordPrompt);

                    bankData.add(new UserCredentials(newUsername, newPassword));
                    System.out.println("Account created successfully for " + newUsername);
                    break;

                case 3:
                    // Exit
                    System.out.println("Goodbye!");
                    connected = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }

            String[] afterLoginOptions = {"Login", "Create account", "Exit"};
            MenuInputScanner afterLoginMenu = new MenuInputScanner(afterLoginOptions);
            afterLoginMenu.setMessage("Welcome to the bank! Please choose an option:");

            int afterLoginIndex = prompt.getUserInput(menu);

            switch (afterLoginIndex) {
                case 1:
                    // Login
                    StringInputScanner usernamePrompt = new StringInputScanner();
                    usernamePrompt.setMessage("Enter your username:");

                    StringInputScanner passwordPrompt = new StringInputScanner();
                    passwordPrompt.setMessage("Enter your password:");

                    String enteredUsername = prompt.getUserInput(usernamePrompt);
                    String enteredPassword = prompt.getUserInput(passwordPrompt);

                    boolean loginSuccessful = false;
                    for (UserCredentials user : bankData) {
                        if (user.getUsername().equals(enteredUsername) && user.getPassword().equals(enteredPassword)) {
                            System.out.println("Welcome " + enteredUsername);
                            loginSuccessful = true;
                            break;
                        }
                    }

                    if (!loginSuccessful) {
                        System.out.println("Username or password incorrect");
                    }
                    break;

                case 2:
                    // Create account
                    StringInputScanner createUsernamePrompt = new StringInputScanner();
                    createUsernamePrompt.setMessage("Create a username:");

                    StringInputScanner createPasswordPrompt = new StringInputScanner();
                    createPasswordPrompt.setMessage("Create a strong password:");

                    String newUsername = prompt.getUserInput(createUsernamePrompt);
                    String newPassword = prompt.getUserInput(createPasswordPrompt);

                    bankData.add(new UserCredentials(newUsername, newPassword));
                    System.out.println("Account created successfully for " + newUsername);
                    break;

                case 3:
                    // Exit
                    System.out.println("Goodbye!");
                    connected = false;
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
