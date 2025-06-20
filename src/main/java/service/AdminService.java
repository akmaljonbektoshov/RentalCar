package service;

import entity.Booking;
import entity.Car;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static storage.DB.*;
import static storage.Util.*;

public class AdminService {
    public void adminMenu() {
        while (true) {
            System.out.print("""
                    1. Add car
                    2. Show cars
                    3. Manage car
                    4. Show report
                    
                    0. Exit
                    >>>\s""");
            switch (strSc.nextLine()) {
                case "0" -> {
                    currentUser = null;
                    return;
                }
                case "1" -> {
                    addCar();
                }
                case "2" -> {
                    showCars();
                }
                case "3" -> {
                    manageCar();
                }
                case "4" -> {
                    showReport();
                }
                default -> System.out.println("Please try again.");
            }
        }
    }

    private void showReport() {
        if (histories.isEmpty()) {
            System.out.println("The car have not been rented yet.");
            return;
        }

        AtomicInteger count = new AtomicInteger(0);
        AtomicInteger number = new AtomicInteger(1);

        cars.forEach(car ->
                histories.stream()
                        .filter(history -> car.getId().equals(history.getCarId()))
                        .peek(history -> count.getAndIncrement())
                        .map(Booking::getTotalPrice)
                        .reduce(Double::sum)
                        .ifPresentOrElse(
                                totalAmount -> {
                                    System.out.printf(
                                            "%s. %s %s %s %s.%n",
                                            number.getAndIncrement(),
                                            car.getLicensePlate(),
                                            car.getModel(),
                                            count,
                                            totalAmount
                                    );
                                    count.set(0);
                                },
                                () -> System.out.printf(
                                        "%s. %s %s %s %s.%n",
                                        number.getAndIncrement(),
                                        car.getLicensePlate(),
                                        car.getModel(),
                                        count,
                                        0
                                )
                        )
        );
        Optional<Double> doubleOptional = histories.stream()
                .map(Booking::getTotalPrice)
                .reduce(Double::sum);
        double amount = 0;
        if (doubleOptional.isPresent()) {
            amount = doubleOptional.get();
        }
        System.out.println("----------------------------------------------------------");
        System.out.println("Total general amount: " + amount);
    }

    private void manageCar() {
        if (!showCars()) {
            return;
        }

        String licensePlate = enterString("Enter car license plate: ").toUpperCase();
        cars.stream()
                .filter(car -> car.getLicensePlate().equals(licensePlate))
                .findFirst()
                .ifPresentOrElse(
                        car -> {
                            currentCar = car;
                            new CarService().service();
                        },
                        () -> System.out.println("This license plate number is not available.")
                );
    }

    private boolean showCars() {
        if (cars.isEmpty()) {
            System.out.println("There are currently no cars in the system.");
            return false;
        }

        AtomicInteger count = new AtomicInteger(1);
        cars.forEach(
                car -> System.out.printf(
                        "%s. %s.\n",
                        count.getAndIncrement(),
                        car
                )
        );
        return true;
    }

    private void addCar() {
        String brand = enterString("Enter car brand: ");
        String model = enterString("Enter car model: ");
        Integer numberOfSeats = enterInteger("Enter the number of seats: ");
        Integer yearOfManufacture = enterInteger("Enter year of manufacture: ");
        String color = enterString("Enter car color: ");
        Double rentalPricePerDay = enterDouble("Enter rental price per day: ");
        String licensePlate = createLicensePlate(enterString("Enter car license plate: ").toUpperCase());

        Car car = Car.builder()
                .model(model)
                .brand(brand)
                .numberOfSeats(numberOfSeats)
                .yearOfManufacture(yearOfManufacture)
                .color(color)
                .rentalPricePerDay(rentalPricePerDay)
                .licensePlate(licensePlate)
                .build();
        cars.add(car);
        System.out.println("Successfully added.");
    }
}
