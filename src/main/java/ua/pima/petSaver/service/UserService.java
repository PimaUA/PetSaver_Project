package ua.pima.petSaver.service;

import ua.pima.petSaver.dto.SignUpUserDto;
import ua.pima.petSaver.entity.UserSecurityInfo;

public interface UserService {

    UserSecurityInfo findByUsername(String username);

    void saveUserSecurityInfo(SignUpUserDto signUpUserDto);
}
