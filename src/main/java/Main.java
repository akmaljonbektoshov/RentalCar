import entity.Car;
import entity.Role;
import entity.User;
import service.AuthService;

import static storage.DB.cars;
import static storage.DB.users;

public class Main {
    static {
        User admin = User.builder()
                .name("Admin")
                .email("admin")
                .password("123")
                .role(Role.ADMIN)
                .build();
        users.add(admin);

        Car car1 = Car.builder()
                .model("Champion smart")
                .brand("BYD")
                .color("black")
                .licensePlate("01a525ba")
                .numberOfSeats(5)
                .rentalPricePerDay(1_290_000.)
                .yearOfManufacture(2025)
                .build();

        Car car2 = Car.builder()
                .model("Chazor")
                .brand("BYD")
                .color("white")
                .licensePlate("30a140dd")
                .numberOfSeats(5)
                .rentalPricePerDay(980_000.)
                .yearOfManufacture(2019)
                .build();

        Car car3 = Car.builder()
                .model("Han smart")
                .brand("BYD")
                .color("gray")
                .licensePlate("10997bba")
                .numberOfSeats(5)
                .rentalPricePerDay(1_790_000.)
                .yearOfManufacture(2024)
                .build();

        Car car4 = Car.builder()
                .model("Tang")
                .brand("BYD")
                .color("black")
                .licensePlate("01001ooo")
                .numberOfSeats(7)
                .rentalPricePerDay(2_190_000.)
                .yearOfManufacture(2021)
                .build();

        cars.add(car4);
        cars.add(car3);
        cars.add(car2);
        cars.add(car1);
    }

    public static void main(String[] args) {
        new AuthService().service();
    }
}
