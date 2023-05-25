package org.example;

public class SalesContract extends Contract {
    private double processingFee;
    private boolean finance;

    public double getTax() {
        return 1.05;
    }

    public double getRecordingFee() {
        return 100;
    }

    public double getProcessingFee() {
        return processingFee;
    }

    public void setProcessingFee(double processingFee) {
        this.processingFee = processingFee;
    }

    public boolean isFinance() {
        return finance;
    }

    public void setFinance(boolean finance) {
        this.finance = finance;
    }

    public SalesContract(String date, String name, String email, Vehicle vehicleSold) {
        super(date, name, email, vehicleSold);
        this.setDate(date);
        this.setName(name);
        this.setEmail(email);
        this.setVehicleSold(vehicleSold);

        if (vehicleSold.getPrice() < 10000){
            this.processingFee = 295;
        } else{
            this.processingFee = 495;
        }
    }

    @Override
    public double getTotalPrice() {
        return (getVehicleSold().getPrice() * getTax()) + getRecordingFee() + processingFee;
    }

    @Override
    public double getMonthlyPayment() {
        if (finance){
            if (getVehicleSold().getPrice() >= 10000){
                return monthlyPaymentCalculator(0.0425, 48);
            } else{
                return monthlyPaymentCalculator(0.0525, 24);
            }
        } else return 0;
    }

    public double monthlyPaymentCalculator(double interest, int months) {
        double loanAmount = getVehicleSold().getPrice();
        // Monthly interest rate = yearly rate divided by 12
        double monthlyRate = interest / 12.0;
        // formula
        return (loanAmount*monthlyRate) / (1-Math.pow(1+monthlyRate, -months));
    }
}
