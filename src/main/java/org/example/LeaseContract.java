package org.example;

import java.io.FileWriter;
import java.io.IOException;

public class LeaseContract extends Contract {

    public double getLeaseFee() {
        return 0.07;
    }

    public double getEndValue() {
        return getVehicleSold().getPrice() * 0.5;
    }

    public LeaseContract(String date, String name, String email, Vehicle vehicleSold) {
        super(date, name, email, vehicleSold);
        this.setDate(date);
        this.setName(name);
        this.setEmail(email);
        this.setVehicleSold(vehicleSold);
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + (getVehicleSold().getPrice() * getLeaseFee());
    }

    @Override
    public double getMonthlyPayment() {
        return monthlyPaymentCalculator(0.04, 36);
    }

    @Override
    public String getPersistenceString() {
            Vehicle v = getVehicleSold();
            return String.format("LEASE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%.2f\n",
                    getDate(),
                    getName(),
                    getEmail(),
                    v.getVin(),
                    v.getYear(),
                    v.getMake(),
                    v.getModel(),
                    v.getVehicleType(),
                    v.getColor(),
                    v.getOdometer(),
                    v.getPrice(),
                    getEndValue(),
                    getLeaseFee(),
                    getTotalPrice(),
                    getMonthlyPayment());
    }

    public double monthlyPaymentCalculator(double interest, int months) {
        double loanAmount = getVehicleSold().getPrice();
        // Monthly interest rate = yearly rate divided by 12
        double monthlyRate = interest / 12.0;
        // formula
        return (loanAmount*monthlyRate) / (1-Math.pow(1+monthlyRate, -months));
    }
}