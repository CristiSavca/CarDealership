package org.example;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);
    public static void main(String[] args) {
        UserInterface ui = new UserInterface();
        AdminUI aui = new AdminUI();

        boolean done = false;
        while (!done) {
            System.out.println("------ Login Menu ------");
            System.out.println("[1] Employee Login");
            System.out.println("[2] Administrator Login");
            System.out.println("[0] Exit");
            System.out.print("Enter your choice: ");
            int choice;
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> ui.display();
                case 2 -> aui.display();
                case 0 -> {System.out.println("Shutting Down..."); done = true;}
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}