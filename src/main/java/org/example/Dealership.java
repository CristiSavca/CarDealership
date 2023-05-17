package org.example;
import java.util.List;
import java.util.ArrayList;

public class Dealership {
    private String name;
    private String address;
    private String phone;

    private final ArrayList<Vehicle> inventory = new ArrayList<>();

    public Dealership(String name, String address, String phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public List<Vehicle> getVehiclesByPrice(int min, int max){
        return null;
    }

    public List<Vehicle> getVehiclesByMakeModel(String make,String model){
        return null;
    }

    public List<Vehicle> getVehiclesByYear(int min, int max){
        return null;
    }

    public List<Vehicle> getVehiclesByColor(String color){
        return null;
    }

    public List<Vehicle> getVehiclesByMileage(int min, int max){
        return null;
    }

    public List<Vehicle> getVehiclesByType(String vehicleType){
        return null;
    }

    public List<Vehicle> getAllVehicles(){
        for (Vehicle v : inventory){
            System.out.println(
                    v.getVin() + " " + v.getYear() + " " + v.getMake() + " " + v.getModel() + " " + v.getVehicleType() + " " + v.getColor() + " " + v.getOdometer() + " " + v.getPrice()
            );
        }
        return null;
    }

    public void addVehicle(Vehicle vehicle){
        inventory.add(vehicle);
    }

    public void removeVehicle(Vehicle vehicle){

    }
}
