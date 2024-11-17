package ua.pima.petSaver.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.pima.petSaver.entity.user.UserInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    //?? can be replaced by findByID??
    Optional<UserInfo> findByUsername(String username);

    Optional<UserInfo> findByEmail(String email);

    //UserSecurityInfo save(SignUpUserDto signUpUserDto);

    //UserSecurityInfo saveDto(SignUpUserDto signUpUserDto);
}
