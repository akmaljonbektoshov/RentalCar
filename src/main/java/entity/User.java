package entity;

import lombok.*;

import java.util.UUID;

@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = {"email"})
@Builder
public class User {
    private final String id = UUID.randomUUID().toString();
    private String name;
    private String email;
    private String password;
    private Role role;
}
