package service;

import entity.Booking;

import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import static storage.DB.*;
import static storage.Util.*;

public class UserService {
    public void userMenu() {
        while (true) {
            System.out.print("""
                    1. Show cars
                    2. Rent car
                    3. History
                    
                    0. Exit
                    >>>\s""");
            switch (strSc.nextLine()) {
                case "0" -> {
                    currentUser = null;
                    return;
                }
                case "1" -> {
                    showCar();
                }
                case "2" -> {
                    rentCar();
                }
                case "3" -> {
                    history();
                }
                default -> System.out.println("Please try again.");
            }
        }
    }

    private void rentCar() {
        if(!showCar()) {
            return;
        }


    }

    private boolean showCar() {
        if (cars.isEmpty()) {
            System.out.println("No cars have been added to the system.");
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

    private void history() {
        Optional<Double> doubleOptional = histories.stream()
                .filter(history -> history.getUserId().equals(currentUser.getId()))
                .map(Booking::getTotalPrice)
                .reduce(Double::sum);

        if (doubleOptional.isEmpty()) {
            System.out.println("You didn't rent a car.");
            return;
        }
        double totalGeneralAmount = doubleOptional.get();

        AtomicInteger count = new AtomicInteger(0);
        AtomicInteger number = new AtomicInteger(0);
        cars.forEach(car -> histories.stream()
                .filter(
                        history -> history.getCarId().equals(car.getId())
                                && history.getUserId().equals(currentUser.getId())
                )
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
                                    count
                            );
                            count.set(0);
                        },
                        () -> count.set(0)
                )
        );
        System.out.println("----------------------------------------------------------");
        System.out.println("Total general amount: " + totalGeneralAmount);
    }
}
