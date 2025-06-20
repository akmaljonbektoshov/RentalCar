package storage;

import entity.Booking;
import entity.Car;
import entity.User;

import java.util.*;

public class DB {
    public static Set<User> users = new HashSet<>();
    public static List<Car> cars = new ArrayList<>();
    public static List<Booking> histories = new ArrayList<>();
}
