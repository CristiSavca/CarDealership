package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {

    private DealershipFileManager fileManager;
    Dealership dealership;
    private void init() {
        fileManager = new DealershipFileManager("inventory.csv");
        dealership = fileManager.getDealership();
    }

    public static Scanner scanner = new Scanner(System.in);

    public void printHeader(){
        System.out.println("VIN              YEAR        MAKE             MODEL            TYPE             COLOR       ODOMETER         PRICE");
        System.out.println("----------------------------------------------------------------------------------------------------------------------");
    }

    private void displayVehicles(List<Vehicle> vehicles) {
        printHeader();
        for (Vehicle vehicle : vehicles) {
            System.out.printf("%-15d  %-10d  %-15s  %-15s  %-15s  %-10s  %-15d  %.2f%n",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice());
        }
    }
    public void display() {
        init();
        boolean done = false;

        while (!done) {
            System.out.println("------ Dealership Menu ------");
            System.out.println("[1] Search By Price");
            System.out.println("[2] Search By Make/Model");
            System.out.println("[3] Search By Year");
            System.out.println("[4] Search By Color");
            System.out.println("[5] Search By Mileage");
            System.out.println("[6] Search By Type");
            System.out.println("[7] Show All Vehicles");
            System.out.println("[8] Add Vehicle");
            System.out.println("[9] Remove Vehicle");
            System.out.println("[0] Exit");
            System.out.print("Enter your choice: ");
            int choice;
            choice = scanner.nextInt();

            switch (choice) {
                case 1 -> processGetByPriceRequest();
                case 2 -> processGetByMakeModelRequest();
                case 3 -> processGetByYearRequest();
                case 4 -> processGetByColorRequest();
                case 5 -> processGetByMileageRequest();
                case 6 -> processGetByVehicleTypeRequest();
                case 7 -> processGetAllVehiclesRequest();
                case 8 -> processAddVehicleRequest();
                case 9 -> processRemoveVehicleRequest();
                case 0 -> {System.out.println("Exiting Dealership Menu..."); done = true;}
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }


    public void processGetByPriceRequest() {

        System.out.print("Enter minimum price: ");
        double minPrice = scanner.nextDouble();
        System.out.print("Enter maximum price: ");
        double maxPrice = scanner.nextDouble();

        System.out.println("Vehicles by Price:");
        displayVehicles(dealership.getVehiclesByPrice(minPrice, maxPrice));
        System.out.println();
    }

    public void processGetByMakeModelRequest() {
        System.out.print("Enter make: ");
        String make = scanner.next();
        System.out.print("Enter model: ");
        String model = scanner.next();

        System.out.println("Vehicles by Make and Model:");
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
        System.out.println();
    }


    public void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();

        System.out.println("Vehicles by Year:");
        displayVehicles(dealership.getVehiclesByYear(minYear, maxYear));
        System.out.println();
    }

    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.next();

        System.out.println("Vehicles by Color:");
        displayVehicles(dealership.getVehiclesByColor(color));
        System.out.println();
    }

    public void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        double minMileage = scanner.nextDouble();
        System.out.print("Enter maximum mileage: ");
        double maxMileage = scanner.nextDouble();

        System.out.println("Vehicles by Mileage:");
        displayVehicles(dealership.getVehiclesByMileage(minMileage, maxMileage));
        System.out.println();
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.next();

        System.out.println("Vehicles by Vehicle Type:");
        displayVehicles(dealership.getVehiclesByType(vehicleType));
        System.out.println();
    }


    public void processGetAllVehiclesRequest() {
        displayVehicles(dealership.getAllVehicles());
    }

    public void processAddVehicleRequest() {
        System.out.println("Enter the vehicle details:");
        System.out.print("VIN: ");
        int vin = scanner.nextInt();
        System.out.print("Year: ");
        int year = scanner.nextInt();
        System.out.print("Make: ");
        String make = scanner.next();
        System.out.print("Model: ");
        String model = scanner.next();
        System.out.print("Vehicle Type: ");
        String vehicleType = scanner.next();
        System.out.print("Color: ");
        String color = scanner.next();
        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        System.out.println("Vehicle added successfully.\n");

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);

    }
    public void processRemoveVehicleRequest() {
        Vehicle v = null;
        System.out.print("Enter vin of vehicle you would like to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine();
        for(Vehicle i : dealership.getAllVehicles()){
            if(i.getVin() == vin) {
                System.out.println("Vehicle Found!");
                v = i;
                break;
            }
        }
        dealership.removeVehicle(v);
        fileManager.saveDealership(dealership);
        System.out.println("Vehicle removed successfully!");
    }
}
