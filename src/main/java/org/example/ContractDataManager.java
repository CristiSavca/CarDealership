package org.example;

import java.io.*;
import java.util.ArrayList;

public class ContractDataManager {
    private final String fileName;
    public ContractDataManager(String fileName) {
        this.fileName = fileName;
    }
    Dealership dealership;
    private void init() {
        DealershipFileManager fileManager = new DealershipFileManager("inventory.csv");
        dealership = fileManager.getDealership();
    }

    public ArrayList<Contract> getContracts() {
        init();
        ArrayList<Contract> contracts = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String input;

            while ((input = bufReader.readLine()) != null) { // For every line in the csv file until no more lines:
                String[] details = input.split("\\|"); // split the line into pieces to extract and store info from each piece

                String date = details[1];
                String name = details[2];
                String email = details[3];
                int vin = Integer.parseInt(details[4]);
                boolean finance = details[15].equalsIgnoreCase("YES");

                int year = Integer.parseInt(details[5]);
                String make = details[6];
                String model = details[7];
                String vehicleType = details[8];
                String color = details[9];
                int odometer = Integer.parseInt(details[10]);
                double price = Double.parseDouble(details[11]);

                // Create a Vehicle object using the pieces we extracted to pass into contract constructor
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                if (details[0].equals("SALE")) {
                    // Create a Contract object using the pieces we extracted and populate our ArrayList contracts
                    SalesContract contract = new SalesContract(date, name, email, vehicle, finance);
                    contracts.add(contract);
                }else {
                    // Create a Contract object using the pieces we extracted and populate our ArrayList contracts
                    LeaseContract contract = new LeaseContract(date, name, email, vehicle);
                    contracts.add(contract);
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading inventory file: ");
        }

        return contracts;
    }

    public void saveContract(Contract contract) {
        try {
            FileWriter fileWriter = new FileWriter(fileName, true);
            BufferedWriter bufWriter = new BufferedWriter(fileWriter);

            bufWriter.append(contract.getPersistenceString());
            bufWriter.close();

            System.out.println("Contract saved to file: " + fileName);
        } catch (IOException e) {
            System.err.println("Error saving contract");
        }
    }
}
