# CarDealership

Car Dealership allows owners/employees to manage their car inventory and create contracts based on selling/leasing cars.

## Home Screens

<img width="130" alt="image" src="https://github.com/CristiSavca/CarDealership/assets/58373811/a40d28e4-5050-4529-985e-5a8abcecd7c2">

<img width="139" alt="image" src="https://github.com/CristiSavca/CarDealership/assets/58373811/b1eaf567-0af9-4aef-a440-228e45535835">

## Products Screen

<img width="479" alt="image" src="https://github.com/CristiSavca/CarDealership/assets/58373811/7c0f162e-3959-4966-a38e-c297acc96d19">

## Bonus Challenges

<img width="802" alt="image" src="https://github.com/CristiSavca/CarDealership/assets/58373811/2a8b268f-9e07-44fd-a8fd-bb0d8ea483ac">

<img width="152" alt="image" src="https://github.com/CristiSavca/CarDealership/assets/58373811/14c2f7f7-6f20-4ac3-bac3-0ad18702381f">

## Calculator Page

<img width="967" alt="Screenshot 2023-05-25 043532" src="https://github.com/CristiSavca/CarDealership/assets/58373811/6323b4e7-e4cd-4dfa-ab75-7c78b91c0a21">

This error occured because I did not realize that I had to recreate the vehicle object for the vehicles in the contracts.csv file when when constructing the contract objects and adding them to an ArrayList of contracts, because the same vehicles were removed from the inventory file and array when the vehicles were sold or leased earlier, so I could not get their objects from anywhere.

## Interesting Code

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

This code is interesting because it parses all the different types of contracts possible and makes objects out of them accordingly: It checks if a Sale contract is financed or not, along with differentiating between Sale and Lease contracts.
