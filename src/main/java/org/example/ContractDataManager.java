package org.example;

import java.io.*;

public class ContractDataManager {

    private final String fileName;

    public ContractDataManager(String fileName) {
        this.fileName = fileName;
    }

//    public Contract getContracts() {
//        Contract contracts = null;
//        try {
//            FileReader fileReader = new FileReader(fileName);
//            BufferedReader bufReader = new BufferedReader(fileReader);
//            String input;
//
//            // Read the rest of the csv lines into the inventory of the dealership we just made
//            while ((input = bufReader.readLine()) != null) { // For every line in the csv file until no more lines:
//                String[] details = input.split("\\|"); // split the line into pieces to extract and store info from each piece
//                int vin = Integer.parseInt(details[0]);
//                int year = Integer.parseInt(details[1]);
//                String make = details[2];
//                String model = details[3];
//                String vehicleType = details[4];
//                String color = details[5];
//                int odometer = Integer.parseInt(details[6]);
//                double price = Double.parseDouble(details[7]);
//
//                // Create a Vehicle object using the pieces we extracted and populate our ArrayList of vehicle objects inside our Dealership object
//                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
////                contracts.addContract(contract);
//            }
//        } catch (IOException e) {
//            System.err.println("Error reading inventory file: ");
//        }
//
//        return contracts;
//    }

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
