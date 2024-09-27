package ua.pima.petSaver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.pima.petSaver.entity.UserSecurityInfo;
import ua.pima.petSaver.repository.UserSecurityInfoRepository;

import java.util.Collection;
import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {
    @Autowired
    UserSecurityInfoRepository userSecurityInfoRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurityInfo userSecurityInfo=userSecurityInfoRepository.findByUsername(username);
        if(userSecurityInfo==null){
            throw new UsernameNotFoundException("Username or Password not found");
        }
        return new CustomUserDetails(userSecurityInfo.getUsername(),userSecurityInfo.getPassword(),authorities());
    }

    public Collection<? extends GrantedAuthority> authorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }
}
