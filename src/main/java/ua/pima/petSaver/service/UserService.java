package ua.pima.petSaver.service;

import ua.pima.petSaver.dto.SignUpUserDto;
import ua.pima.petSaver.dto.UserSearch;
import ua.pima.petSaver.entity.user.UserInfo;

import java.util.List;
import java.util.Optional;

public interface UserService {

    Optional<UserInfo> findByUsername(String username);

    Optional<UserInfo> findByEmail(String email);

    void save(SignUpUserDto signUpUserDto);

    List<UserInfo> getAllUsers();

    List<UserInfo> search(UserSearch userSearch);

    void deleteUser(UserInfo userInfo);

    //List<UserInfo> getAllUsersForSearch(Example<UserInfo> example);

    //void saveUserSecurityInfo(SignUpUserDto signUpUserDto);
}
