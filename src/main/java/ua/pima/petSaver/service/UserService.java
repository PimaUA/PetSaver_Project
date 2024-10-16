package ua.pima.petSaver.service;

import ua.pima.petSaver.dto.SignUpUserDto;
import ua.pima.petSaver.entity.user.UserSecurityInfo;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserSecurityInfo> findByUsername(String username);

    Optional<UserSecurityInfo> findByEmail(String email);

    void save(SignUpUserDto signUpUserDto);

    List<UserSecurityInfo> getAllUsers();

    //void saveUserSecurityInfo(SignUpUserDto signUpUserDto);
}
