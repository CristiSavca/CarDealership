package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DealershipFileManager {
    private final String fileName;

    public DealershipFileManager(String fileName) {
        this.fileName = fileName;
    }

    public Dealership getDealership(){
        Dealership dealership = null;
        try {
            FileReader fileReader = new FileReader(fileName);
            BufferedReader bufReader = new BufferedReader(fileReader);
            String input;

            // Read first line of csv into Dealership constructor
            input = bufReader.readLine();
            String[] details = input.split("\\|"); // split the line into pieces to extract and store info from each piece
            String name = details[0];
            String address = details[1];
            String phone = details[2];
            dealership = new Dealership(name, address, phone);

            // Read the rest of the csv lines into the inventory of the dealership we just made
            while ((input = bufReader.readLine()) != null) { // For every line in the csv file until no more lines:
                details = input.split("\\|");
                int vin = Integer.parseInt(details[0]);
                int year = Integer.parseInt(details[1]);
                String make = details[2];
                String model = details[3];
                String vehicleType = details[4];
                String color = details[5];
                int odometer = Integer.parseInt(details[6]);
                double price = Double.parseDouble(details[7]);

                // Create a Vehicle object using the pieces we extracted and populate our ArrayList of vehicle objects inside our Dealership object
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
                dealership.addVehicle(vehicle);
            }
        } catch (IOException e) {
            System.out.println("File Not Found!");
            System.exit(0);
        }
        return dealership;
    }

    public void saveDealership(){

    }
}
