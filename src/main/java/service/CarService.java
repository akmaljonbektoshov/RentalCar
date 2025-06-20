package service;

import static storage.Util.*;

public class CarService {
    public void service() {
        while (true) {
            System.out.print("""
                    1. Edit color
                    2. Edit daily rental price
                    3. Edit license plate
                    
                    0. Exit
                    >>>\s""");
            switch (strSc.nextLine()) {
                case "0" -> {
                    currentCar = null;
                    return;
                }
                case "1" -> {
                    currentCar.setColor(enterString("Enter a new color: "));
                    System.out.printf("Color changed to %s.%n", currentCar.getColor());
                }
                case "2" -> {
                    currentCar.setRentalPricePerDay(enterDouble("Enter a new daily rental price: "));
                    System.out.printf("Daily rental price changed to %s dollar.%n", currentCar.getRentalPricePerDay());
                }
                case "3" -> {
                    editLicensePlate();
                }
                default -> System.out.println("Please try again.");
            }
        }
    }

    private void editLicensePlate() {
        currentCar.setLicensePlate(
                createLicensePlate(enterString("Enter car license plate: ").toUpperCase())
        );
        System.out.printf("License plate changed to %s.%n", currentCar.getLicensePlate());
    }
}
