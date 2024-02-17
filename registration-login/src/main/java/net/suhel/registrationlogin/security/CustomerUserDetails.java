package net.suhel.registrationlogin.security;

import lombok.AllArgsConstructor;
import net.suhel.registrationlogin.dto.UserDto;
import net.suhel.registrationlogin.entity.User;
import net.suhel.registrationlogin.repository.UserRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CustomerUserDetails implements UserDetailsService {
    UserRepository userRepository;


    @Override
    public UserDetails loadUserByUsername(String usernameOrEmail) throws UsernameNotFoundException {
        User user= userRepository.findByEmail(usernameOrEmail);
        if(user !=null){
            return new org.springframework.security.core.userdetails.User(
                    user.getName(),
                    user.getPassword(),
                    user.getRoles().stream()
                            .map(role->new SimpleGrantedAuthority(role.getName())
                            ).collect(Collectors.toList())
            );
        }

        else {
            throw new UsernameNotFoundException("Invalid User Name or Email");
        }
    }
}
