package storage;

import entity.Car;
import entity.User;

import java.util.Scanner;

import static storage.DB.cars;

public class Util {
    public static final Scanner sc = new Scanner(System.in);
    public static final Scanner strSc = new Scanner(System.in);

    public static User currentUser;
    public static Car currentCar;


    public static String enterString(String text) {
        System.out.print(text);
        return strSc.nextLine();
    }

    public static Double enterDouble(String text) {
        System.out.print(text);
        return sc.nextDouble();
    }

    public static Integer enterInteger(String text) {
        System.out.print(text);
        return sc.nextInt();
    }

    public static String createLicensePlate(String licensePlate) {
        while (isValidLicensePlate(licensePlate) || isThereLicensePlate(licensePlate)) {
            if (isValidLicensePlate(licensePlate)) {
                System.out.println("For example, the license plate of the car is 01X177AA or 10788BBO");
                licensePlate = enterString("Enter car license plate: ").toUpperCase();
            } else if (isThereLicensePlate(licensePlate)) {
                System.out.println("This car number already exists.");
                licensePlate = enterString("Enter car license plate: ").toUpperCase();
            }
        }
        return licensePlate;
    }

    public static boolean isValidLicensePlate(String licensePlate) {
        String pattern = "^(\\d{2}[A-Z]\\d{3}[A-Z]{2}|\\d{5}[A-Z]{3})$";
        return licensePlate == null || !licensePlate.toUpperCase().matches(pattern);
    }

    public static boolean isThereLicensePlate(String licensePlate) {
        return cars.stream()
                .anyMatch(car -> car.getLicensePlate().equals(licensePlate));
    }
}
