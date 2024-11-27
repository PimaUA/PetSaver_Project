package ua.pima.petSaver.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import ua.pima.petSaver.entity.user.Country;
import ua.pima.petSaver.validation.PasswordMatches;


@Getter
@Setter
@PasswordMatches
public class SignUpUserDto {
    @Size(min = 3, message = "name must be more than 2 symbols")
    private String username;
    @Email(regexp = "^[A-Za-z0-9]+(?:\\.[A-Za-z0-9])*@(?!Mail\\.com|Mail2\\.net)[A-Za-z0-9]+(?:\\.[A-Za-z0-9])*\\.\\w+$")
    private String email;
    @Size(min = 3, message = "minimum 3 characters")
    private String password;
    private String matchingPassword;

    private Country country;
    //private Timestamp dateTimeOfRegistry;
}
