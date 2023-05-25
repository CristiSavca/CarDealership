package org.example;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserInterface {
    public static Scanner scanner = new Scanner(System.in);
    private DealershipFileManager fileManager;
    private ContractDataManager contractManager;
    ArrayList<Contract> contracts;
    Dealership dealership;
    private void init() {
        fileManager = new DealershipFileManager("inventory.csv");
        contractManager = new ContractDataManager("contracts.csv");
        dealership = fileManager.getDealership();
        contracts = contractManager.getContracts();
    }

    public void printHeader(){
        System.out.println("VIN              YEAR        MAKE             MODEL            TYPE             COLOR       ODOMETER         PRICE");
        System.out.println("-----------------------------------------------------" + dealership.getName().toUpperCase() + "--------------------------------------------------------");
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
            System.out.println("[10] Sell/Lease a Vehicle");
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
                case 10 -> processSellOrLeaseVehicle();
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
        String make = scanner.nextLine();
        System.out.print("Enter model: ");
        String model = scanner.nextLine();

        System.out.println("Vehicles by Make and Model:");
        displayVehicles(dealership.getVehiclesByMakeModel(make, model));
        System.out.println();
    }


    public void processGetByYearRequest() {
        System.out.print("Enter minimum year: ");
        int minYear = scanner.nextInt();
        System.out.print("Enter maximum year: ");
        int maxYear = scanner.nextInt();
        scanner.nextLine();

        System.out.println("Vehicles by Year:");
        displayVehicles(dealership.getVehiclesByYear(minYear, maxYear));
        System.out.println();
    }

    public void processGetByColorRequest() {
        System.out.print("Enter color: ");
        String color = scanner.nextLine();

        System.out.println("Vehicles by Color:");
        displayVehicles(dealership.getVehiclesByColor(color));
        System.out.println();
    }

    public void processGetByMileageRequest() {
        System.out.print("Enter minimum mileage: ");
        double minMileage = scanner.nextDouble();
        System.out.print("Enter maximum mileage: ");
        double maxMileage = scanner.nextDouble();
        scanner.nextLine();

        System.out.println("Vehicles by Mileage:");
        displayVehicles(dealership.getVehiclesByMileage(minMileage, maxMileage));
        System.out.println();
    }

    public void processGetByVehicleTypeRequest() {
        System.out.print("Enter vehicle type: ");
        String vehicleType = scanner.nextLine();

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
        scanner.nextLine();
        System.out.print("Make: ");
        String make = scanner.nextLine();
        System.out.print("Model: ");
        String model = scanner.nextLine();
        System.out.print("Vehicle Type: ");
        String vehicleType = scanner.nextLine();
        System.out.print("Color: ");
        String color = scanner.nextLine();
        System.out.print("Odometer: ");
        int odometer = scanner.nextInt();
        System.out.print("Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.println("Vehicle added successfully.\n");

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);
        dealership.addVehicle(vehicle);
        fileManager.saveDealership(dealership);

    }
    public void processRemoveVehicleRequest() {
        Vehicle v = null;
        System.out.print("Enter vin of vehicle you would like to remove: ");
        int vin = scanner.nextInt();
        scanner.nextLine(); // clear buffer for name input (need whole line)
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

    public void processSellOrLeaseVehicle() {
        String date = LocalDate.now().toString();
        scanner.nextLine();
        System.out.println("Enter your Personal Information:");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Enter VIN of car you want: ");
        int VIN = scanner.nextInt();
        Vehicle vehicle = null;
        scanner.nextLine();

        for (Vehicle v : dealership.getAllVehicles()) {
            if (VIN == v.getVin()){
                vehicle = v;
                break;
            }
        }

        System.out.print("Sell or Lease: ");
        String choice = scanner.nextLine().trim().toLowerCase();
        if (choice.equals("sell")) {
            System.out.print("Window Tint - $400 (yes/no): ");
            double Tint = scanner.nextLine().trim().equalsIgnoreCase("yes") ? 400 : 0;
            System.out.print("BBS Rim x4 - $3200 (yes/no): ");
            double Rims = scanner.nextLine().trim().equalsIgnoreCase("yes") ? 3200 : 0;
            System.out.print("Livery Wrap - $4000 (yes/no): ");
            double Livery = scanner.nextLine().trim().equalsIgnoreCase("yes") ? 4000 : 0;
            System.out.print("Turbo Kit - $2600 (yes/no): ");
            double Turbo = scanner.nextLine().trim().equalsIgnoreCase("yes") ? 2600 : 0;
            System.out.print("Wide Body Kit - $4000 (yes/no): ");
            double Body = scanner.nextLine().trim().equalsIgnoreCase("yes") ? 4000 : 0;

            System.out.print("Finance? (yes/no): ");
            boolean finance = scanner.nextLine().trim().equalsIgnoreCase("yes");

            Contract salesContract = new SalesContract(date, name, email, vehicle, finance, Tint, Rims, Livery, Turbo, Body);
            contractManager.saveContract(salesContract); // write contract to contracts file
            dealership.removeVehicle(vehicle); // update vehicle inventory
            fileManager.saveDealership(dealership);

        } else if (choice.equals("lease")) {
            Contract leaseContract = new LeaseContract(date, name, email, vehicle);
            contractManager.saveContract(leaseContract);
            dealership.removeVehicle(vehicle); // update vehicle inventory
            fileManager.saveDealership(dealership);

        } else {
            System.out.println("Please enter a proper choice");
        }
    }
}
