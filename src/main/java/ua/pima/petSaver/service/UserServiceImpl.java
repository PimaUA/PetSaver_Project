package ua.pima.petSaver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.pima.petSaver.dto.SignUpUserDto;
import ua.pima.petSaver.dto.UserSearch;
import ua.pima.petSaver.entity.user.UserInfo;
import ua.pima.petSaver.repository.UserInfoRepository;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Optional<UserInfo> findByUsername(String username) {
        return userInfoRepository.findByUsername(username);
    }

    @Override
    public Optional<UserInfo> findByEmail(String email) {
        return userInfoRepository.findByEmail(email);
    }

    @Override
    public void save(SignUpUserDto signUpUserDto) {
        UserInfo userInfo = new UserInfo(signUpUserDto.getUsername()
                , bCryptPasswordEncoder.encode(signUpUserDto.getPassword()), true, "ROLE_USER",
        signUpUserDto.getCountry(),signUpUserDto.getEmail());
        userInfoRepository.save(userInfo);
    }

    @Override
    public void deleteUser(UserInfo userInfo) {
        userInfoRepository.delete(userInfo);
    }

    @Override
    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    @Override
    public List<UserInfo> search(UserSearch userSearch) {
        UserInfo probe = new UserInfo();
        if (StringUtils.hasText(userSearch.value())) {
            probe.setUsername(userSearch.value());
            probe.setEmail(userSearch.value());
        }
        Example<UserInfo> example = Example.of(probe,
                ExampleMatcher.matchingAny()
                        .withIgnoreCase()
                        .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING));
        return userInfoRepository.findAll(example);
    }

    @Override
    public void toggleUserStatus(List<String> enableUsernames, List<String> disableUsernames) {
        // Fetch all users whose usernames are in the provided lists
        List<UserInfo> users = userInfoRepository.findByUsernameIn(
                Stream.concat(enableUsernames.stream(), disableUsernames.stream()).distinct().toList()
        );
        // Validate that all provided usernames exist
        Set<String> foundUsernames = users.stream()
                .map(UserInfo::getUsername)
                .collect(Collectors.toSet());
        if (!foundUsernames.containsAll(enableUsernames) || !foundUsernames.containsAll(disableUsernames)) {
            throw new UsernameNotFoundException("One or more usernames not found");
        }
        // Update the enable status for each user
        for (UserInfo user : users) {
            if (enableUsernames.contains(user.getUsername())) {
                user.setEnabled(true);
            } else if (disableUsernames.contains(user.getUsername())) {
                user.setEnabled(false);
            }
        }
        // Save the updated users
        userInfoRepository.saveAll(users);
    }
}
