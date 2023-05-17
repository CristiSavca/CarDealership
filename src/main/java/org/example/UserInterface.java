package org.example;

import java.util.Scanner;

public class UserInterface {
    public Scanner scanner = new Scanner(System.in);
    Dealership dealership;
    private void init() {
        DealershipFileManager DFM = new DealershipFileManager("inventory.csv");
        dealership = DFM.getDealership();
    }

    public void display() {
        init();
        boolean done = false;
        while(!done){
            System.out.println("""
                    Main Menu:
                    [1] - Search By Price
                    [2] - Search By Make/Model
                    [3] - Search By Year
                    [4] - Search By Color
                    [5] - Search By Mileage
                    [6] - Search By Type
                    [7] - Show All Vehicles
                    [8] - Add Vehicle
                    [9] - Remove Vehicle
                    [X] - Exit""");
            String input = scanner.nextLine();
            switch (input.toUpperCase()) { // run corresponding methods (screens) based on user input
                case "1" -> processGetByPriceRequests();
                case "2" -> processGetByMakeModelRequest();
                case "3" -> processGetYearRequests();
                case "4" -> processGetByColorRequest();
                case "5" -> processGetMileageRequest();
                case "6" -> processGetByVehicleTypeRequest();
                case "7" -> processGetAllVehiclesRequest();
                case "8" -> processAddVehicleRequest();
                case "9" -> processRemoveVehicleRequest();
                case "X" -> {System.out.println("Logging Off..."); done = true;}
                default -> System.out.println("Please enter a valid option");
            }
        }
    }

    public void processGetByPriceRequests() {

    }

    public void processGetByMakeModelRequest() {

    }

    public void processGetYearRequests() {

    }

    public void processGetByColorRequest() {

    }

    public void processGetMileageRequest() {

    }

    public void processGetByVehicleTypeRequest(){

    }

    public void processGetAllVehiclesRequest() {
        dealership.getAllVehicles();
    }

    public void processAddVehicleRequest() {

    }

    public void processRemoveVehicleRequest() {

    }
}
