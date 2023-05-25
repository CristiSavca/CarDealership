package org.example;

public class LeaseContract extends Contract {

    public double getLeaseFee() {
        return 1.07;
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
        return getVehicleSold().getPrice() * getLeaseFee();
    }

    @Override
    public double getMonthlyPayment() {
        return monthlyPaymentCalculator(0.04, 36);
    }

    public double monthlyPaymentCalculator(double interest, int months) {
        double loanAmount = getVehicleSold().getPrice();
        // Monthly interest rate = yearly rate divided by 12
        double monthlyRate = interest / 12.0;
        // formula
        return (loanAmount*monthlyRate) / (1-Math.pow(1+monthlyRate, -months));
    }
}