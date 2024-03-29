package de.tekup.carrentalsystembackend.dto;


import de.tekup.carrentalsystembackend.model.enums.UserRole;
import lombok.*;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * DTO for {@link de.tekup.carrentalsystembackend.model.User}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto implements Serializable {
    Long id;
    String username;
    String firstname;
    String lastname;
    String email;
    String password;
    UserRole role;
    LocalDateTime createdOn;
    LocalDateTime updatedOn;
}