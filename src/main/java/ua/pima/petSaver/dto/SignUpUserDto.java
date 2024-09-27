package ua.pima.petSaver.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ua.pima.petSaver.entity.user.Country;
import ua.pima.petSaver.validation.PasswordMatches;

@Data
@PasswordMatches
public class SignUpUserDto {
    @NotBlank(message = "insert your username")
    @Size(min=3, message="name must be more that 3 symbols")
    private String username;
    @NotBlank(message = "choose your country from drop down list")
    private Country country;
    @NotBlank(message = "provide a valid email address")
    @Email(regexp = "^[A-Za-z0-9]+(?:\\.[A-Za-z0-9])*@(?!Mail\\.ru|Yandex\\.net)[A-Za-z0-9]+(?:\\.[A-Za-z0-9])*\\.\\w+$")
    private String email;
    @NotBlank(message = "insert your password")
    private String password;
    @NotBlank(message = "repeat your password")
    private String matchingPassword;
}
