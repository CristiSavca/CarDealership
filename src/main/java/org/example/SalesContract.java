package org.example;

public class SalesContract extends Contract {
    private double processingFee;
    private boolean finance;

    public double getTax() {
        return 0.05;
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

    public SalesContract(String date, String name, String email, Vehicle vehicleSold, boolean finance) {
        super(date, name, email, vehicleSold);
        this.setDate(date);
        this.setName(name);
        this.setEmail(email);
        this.setVehicleSold(vehicleSold);
        this.finance = finance;

        if (vehicleSold.getPrice() < 10000){
            this.processingFee = 295;
        } else{
            this.processingFee = 495;
        }
    }

    @Override
    public double getTotalPrice() {
        return getVehicleSold().getPrice() + (getVehicleSold().getPrice() * getTax()) + getRecordingFee() + processingFee;
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

    @Override
    public String getPersistenceString() {
        Vehicle v = getVehicleSold();
        String finance = isFinance() ? "YES" : "NO";
        return String.format("SALE|%s|%s|%s|%d|%d|%s|%s|%s|%s|%d|%.2f|%.2f|%.2f|%.2f|%s|%.2f\n",
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
                getTax() * getVehicleSold().getPrice(),
                getProcessingFee(),
                getTotalPrice(),
                finance,
                getMonthlyPayment()) + "";

    }

    public double monthlyPaymentCalculator(double interest, int months) {
        double loanAmount = getVehicleSold().getPrice();
        // Monthly interest rate = yearly rate divided by 12
        double monthlyRate = interest / 12.0;
        // formula
        return (loanAmount*monthlyRate) / (1-Math.pow(1+monthlyRate, -months));
    }
}
