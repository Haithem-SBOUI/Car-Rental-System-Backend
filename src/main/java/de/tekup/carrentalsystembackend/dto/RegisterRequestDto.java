package de.tekup.carrentalsystembackend.dto;

import de.tekup.carrentalsystembackend.model.enums.UserRole;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto implements Serializable {
    private String firstname;
    private String lastname;
    private String email;
    private String password;
    private UserRole role;
    private MultipartFile profileImage;

}
