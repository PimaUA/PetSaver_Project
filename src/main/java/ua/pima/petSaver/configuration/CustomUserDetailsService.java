package ua.pima.petSaver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.pima.petSaver.entity.user.UserInfo;
import ua.pima.petSaver.repository.UserInfoRepository;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserInfoRepository userSecurityInfoRepository;

      @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      Optional<UserInfo> optionalUserSecurityInfo=userSecurityInfoRepository.findByUsername(username);
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
