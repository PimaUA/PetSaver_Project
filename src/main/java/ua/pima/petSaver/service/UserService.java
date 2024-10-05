package ua.pima.petSaver.service;

import ua.pima.petSaver.dto.SignUpUserDto;
import ua.pima.petSaver.entity.user.UserSecurityInfo;

import java.util.Optional;

public interface UserService {

    Optional<UserSecurityInfo> findByUsername(String username);

    void save(SignUpUserDto signUpUserDto);

    //void saveUserSecurityInfo(SignUpUserDto signUpUserDto);
}
