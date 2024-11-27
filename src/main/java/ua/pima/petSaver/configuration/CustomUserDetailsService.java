package ua.pima.petSaver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.pima.petSaver.entity.user.UserInfo;
import ua.pima.petSaver.repository.UserInfoRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    private UserInfoRepository userInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserInfo userInfo = userInfoRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username"));
        if (!userInfo.isEnabled()) {
            throw new UsernameNotFoundException("User is not enabled");
        }
        return new CustomUserDetails(userInfo);
    }
}
