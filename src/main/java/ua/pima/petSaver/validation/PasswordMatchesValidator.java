package ua.pima.petSaver.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import ua.pima.petSaver.dto.SignUpUserDto;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, SignUpUserDto> {

    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
    }

    @Override
    public boolean isValid(SignUpUserDto signUpUserDto, ConstraintValidatorContext constraintValidatorContext) {
        return signUpUserDto.getPassword().equals(signUpUserDto.getMatchingPassword());
    }
}
