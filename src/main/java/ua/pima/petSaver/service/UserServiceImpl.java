package ua.pima.petSaver.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import ua.pima.petSaver.dto.SignUpUserDto;
import ua.pima.petSaver.dto.UserSearch;
import ua.pima.petSaver.entity.user.UserInfo;
import ua.pima.petSaver.repository.UserInfoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserInfoRepository userInfoRepository;
    @Autowired
    ModelMapper modelMapper;
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

        /*signUpUserDto.setPassword(bCryptPasswordEncoder.encode(signUpUserDto.getPassword()));
        UserSecurityInfo userSecurityInfo = convertToUserSecurityInfo(signUpUserDto);
        userSecurityInfoRepository.save(userSecurityInfo);*/
    }

    @Override
    public void deleteUser(UserInfo userInfo) {
        userInfoRepository.delete(userInfo);
    }

    @Override
    public List<UserInfo> getAllUsers() {
        return userInfoRepository.findAll();
    }

    /*@Override
    public List<UserInfo> getAllUsersForSearch(Example<UserInfo> example) {
        return userInfoRepository.findAll(example);
    }*/

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

    /*private UserSecurityInfo convertToUserSecurityInfo(SignUpUserDto signUpUserDto) {
        return modelMapper.map(signUpUserDto, UserSecurityInfo.class);
    }*/
}
