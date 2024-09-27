package ua.pima.petSaver.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import ua.pima.petSaver.dto.SignUpUserDto;
import ua.pima.petSaver.entity.UserSecurityInfo;
import ua.pima.petSaver.repository.UserSecurityInfoRepository;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserSecurityInfoRepository userSecurityInfoRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserSecurityInfo findByUsername(String username) {
        return userSecurityInfoRepository.findByUsername(username);
    }

    @Override
    public void saveUserSecurityInfo(SignUpUserDto signUpUserDto) {
        signUpUserDto.setPassword(bCryptPasswordEncoder.encode(signUpUserDto.getPassword()));
        UserSecurityInfo userSecurityInfo = convertToUserSecurityInfo(signUpUserDto);
        userSecurityInfoRepository.save(userSecurityInfo);
    }

    private UserSecurityInfo convertToUserSecurityInfo(SignUpUserDto signUpUserDto) {
        return modelMapper.map(signUpUserDto, UserSecurityInfo.class);
    }
}
