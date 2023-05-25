package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AdminUI {
    public static Scanner scanner = new Scanner(System.in);
    private ArrayList<Contract> contracts;
    private void init(){
       contracts = new ContractDataManager("contracts.csv").getContracts();
    }

    public void printHeader(){
        System.out.println("                                                     ALL CONTRACTS                                                    ");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
    }

    private void displayContracts(ArrayList<Contract> contracts) {
        printHeader();
        for (Contract contract : contracts) {
            System.out.println(contract.getPersistenceString());
        }
    }

    public void display() {
        init();
        boolean done = false;
        boolean correctPass = false;

        while (!correctPass){
            System.out.print("Enter Password: ");
            String pass = scanner.next();
            if (pass.equals("2424")){
                correctPass = true;
            } else{
                System.out.println("Wrong Password");
                correctPass = true;
                done = true;
            }
        }

        while (!done) {
            System.out.println("------ Administrator Menu ------");
            System.out.println("[1] Display All Contracts");
            System.out.println("[2] Add Contract");
            System.out.println("[3] Delete Contract");
            System.out.println("[0] Exit");
            System.out.print("Enter your choice: ");
            int choice;
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> displayContracts(contracts);
//                case 2 -> ;
//                case 3 -> ;
                case 0 -> {System.out.println("Logging Off..."); done = true;}
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
}
