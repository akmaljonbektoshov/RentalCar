package entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Booking {
    private final String id = UUID.randomUUID().toString();
    private String userId;
    private String carId;
    private LocalDate startDate;
    private LocalDate endDate;
    private Double totalPrice;
    private LocalDateTime createAt;
}
