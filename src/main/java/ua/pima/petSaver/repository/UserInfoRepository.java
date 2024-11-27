package ua.pima.petSaver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.pima.petSaver.entity.user.UserInfo;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo,String> {

    Optional<UserInfo> findByUsername(String username);

    Optional<UserInfo> findByEmail(String email);

    List<UserInfo> findByUsernameIn(List<String> usernames);

}
