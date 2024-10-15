package ua.pima.petSaver.service;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ua.pima.petSaver.dto.SignUpUserDto;
import ua.pima.petSaver.entity.user.UserSecurityInfo;
import ua.pima.petSaver.repository.UserSecurityInfoRepository;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserSecurityInfoRepository userSecurityInfoRepository;
    @Autowired
    ModelMapper modelMapper;
    @Autowired
    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Optional<UserSecurityInfo> findByUsername(String username) {
        return userSecurityInfoRepository.findByUsername(username);
    }

    /*private boolean emailExists(String email) {
        return userRepository.findByEmail(email) != null;
    }*/

    @Override
    public void save(SignUpUserDto signUpUserDto) {
        UserSecurityInfo userSecurityInfo = new UserSecurityInfo(signUpUserDto.getUsername()
                , bCryptPasswordEncoder.encode(signUpUserDto.getPassword()), true, "ROLE_USER",
        signUpUserDto.getCountry());

        userSecurityInfoRepository.save(userSecurityInfo);

        /*signUpUserDto.setPassword(bCryptPasswordEncoder.encode(signUpUserDto.getPassword()));
        UserSecurityInfo userSecurityInfo = convertToUserSecurityInfo(signUpUserDto);
        userSecurityInfoRepository.save(userSecurityInfo);*/
    }

    @Override
    public List<UserSecurityInfo> getAllUsers() {
        return userSecurityInfoRepository.findAll();
    }

    /*private UserSecurityInfo convertToUserSecurityInfo(SignUpUserDto signUpUserDto) {
        return modelMapper.map(signUpUserDto, UserSecurityInfo.class);
    }*/


}
