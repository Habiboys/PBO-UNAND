package com.nouval.pbo;
import java.util.Random;
import java.util.Scanner;

public class App {

    public static void main(String[] args) {
        // Data login
        String username = "admin";
        String password = "admin";

        // Generate random captcha
        String captcha = generateCaptcha();

        // Input username and password
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String inputUsername = scanner.nextLine();

        System.out.print("Enter password: ");
        String inputPassword = scanner.nextLine();

        // Input captcha
        System.out.println("Captcha: " + captcha);
        System.out.print("Enter captcha (case insensitive): ");
        String inputCaptcha = scanner.nextLine();

        // Check login and captcha
        if (inputUsername.equals(username) && inputPassword.equals(password) && inputCaptcha.equalsIgnoreCase(captcha)) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Login failed. Please check your username, password, and captcha.");
        }
        scanner.close();
    }

    // Method to generate random captcha
    private static String generateCaptcha() {
        String characters = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder captcha = new StringBuilder();
        Random random = new Random();

        for (int i = 0; i < 6; i++) {
            char randomChar = characters.charAt(random.nextInt(characters.length()));
            captcha.append(randomChar);
        }

        return captcha.toString();
    }
}
