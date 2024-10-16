package ua.pima.petSaver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.pima.petSaver.entity.user.UserSecurityInfo;

import java.util.Optional;

@Repository
public interface UserSecurityInfoRepository  extends JpaRepository<UserSecurityInfo,String> {

    //?? can be replaced by findByID??
    Optional<UserSecurityInfo> findByUsername(String username);

    Optional<UserSecurityInfo> findByEmail(String email);

    //UserSecurityInfo save(SignUpUserDto signUpUserDto);

    //UserSecurityInfo saveDto(SignUpUserDto signUpUserDto);
}
