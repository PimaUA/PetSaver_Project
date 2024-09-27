package ua.pima.petSaver.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.pima.petSaver.dto.SignUpUserDto;
import ua.pima.petSaver.entity.UserSecurityInfo;

@Repository
public interface UserSecurityInfoRepository  extends JpaRepository<UserSecurityInfo,String> {

    UserSecurityInfo findByUsername(String username);

    UserSecurityInfo save(SignUpUserDto signUpUserDto);
}
