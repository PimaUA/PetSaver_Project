package ua.pima.petSaver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.pima.petSaver.entity.user.UserSecurityInfo;
import ua.pima.petSaver.repository.UserSecurityInfoRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserSecurityInfoRepository userSecurityInfoRepository;

      @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<UserSecurityInfo> optionalUserSecurityInfo=userSecurityInfoRepository.findByUsername(username);
      return optionalUserSecurityInfo.map(CustomUserDetails::new)
              .orElseThrow(()->new UsernameNotFoundException("Invalid username"));
    }

   /* @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserSecurityInfo userSecurityInfo=userSecurityInfoRepository.findByUsername(username);
        if(userSecurityInfo==null){
            throw new UsernameNotFoundException("Username or Password not found");
        }
        return new CustomUserDetails(userSecurityInfo.getUsername(),userSecurityInfo.getPassword()
                ,getAuthorities(userSecurityInfo.getRoles()));
    }*/

    /*public Collection<? extends GrantedAuthority> authorities(){
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }*/

  /*  private static List<GrantedAuthority> getAuthorities (List<String> roles) {
        List<GrantedAuthority> authorities = new ArrayList<>();
        for (String role : roles) {
            authorities.add(new SimpleGrantedAuthority(role));
        }
        return authorities;
    }*/
}
