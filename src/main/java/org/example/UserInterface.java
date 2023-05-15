package org.example;

public class UserInterface {
    Dealership dealership;
    private void init() {
        DealershipFileManager DFM = new DealershipFileManager("inventory.csv");
        dealership = DFM.getDealership();
    }

    public void display() {
        init();
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

    }

    public void processAddVehicleRequest() {

    }

    public void processRemoveVehicleRequest() {

    }
}
