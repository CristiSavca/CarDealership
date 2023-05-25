# CarDealership

Car Dealership allows owners/employees to manage their car inventory and create contracts based on selling/leasing cars.

## Home Screens

<img width="195" alt="image" src="https://github.com/CristiSavca/CarDealership/assets/58373811/e26cb913-3f33-464e-8ca2-087d2c4d80b7">

## Products Screen

<img width="728" alt="image" src="https://github.com/CristiSavca/CarDealership/assets/58373811/6abd41d5-fb8e-4ecc-a851-299361f15ebd">

## Calculator page

<img width="967" alt="image" src="https://github.com/CristiSavca/CarDealership/assets/58373811/31243e44-bbb8-46b3-b25e-f2255a4829f7">

This error occured because I did not realize that I had to recreate a car object for the cars in the contracts.csv file because they were removed from the inventory file and array once added to the contracts file.

## Interesting code

```
while ((input = bufReader.readLine()) != null) { // For every line in the csv file until no more lines:
                String[] details = input.split("\\|"); // split the line into pieces to extract and store info from each piece

                String date = details[1];
                String name = details[2];
                String email = details[3];
                int vin = Integer.parseInt(details[4]);
                boolean finance = details[15].equalsIgnoreCase("YES");

                int year = Integer.parseInt(details[5]);
                String make = details[6];
                String model = details[7];
                String vehicleType = details[8];
                String color = details[9];
                int odometer = Integer.parseInt(details[10]);
                double price = Double.parseDouble(details[11]);

                // Create a Vehicle object using the pieces we extracted to pass into contract constructor
                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                if (details[0].equals("SALE")) {
                    // Create a Contract object using the pieces we extracted and populate our ArrayList contracts
                    SalesContract contract = new SalesContract(date, name, email, vehicle, finance);
                    contracts.add(contract);
                }else {
                    // Create a Contract object using the pieces we extracted and populate our ArrayList contracts
                    LeaseContract contract = new LeaseContract(date, name, email, vehicle);
                    contracts.add(contract);
                }
            }
```

This code is interesting because it parses all the different types of contracts possible and makes objects out of them accordingly.
