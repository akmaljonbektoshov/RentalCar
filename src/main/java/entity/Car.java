package entity;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@EqualsAndHashCode(of = {"licensePlate"})
@Builder
public class Car {
    private final String id = UUID.randomUUID().toString();
    private final String brand;
    private final String model;
    private String licensePlate;
    private String color;
    private final Integer numberOfSeats;
    private final Integer yearOfManufacture;
    private Double rentalPricePerDay;
}