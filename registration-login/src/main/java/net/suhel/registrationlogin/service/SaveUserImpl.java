package net.suhel.registrationlogin.service;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import net.suhel.registrationlogin.dto.UserDto;
import net.suhel.registrationlogin.entity.Role;
import net.suhel.registrationlogin.entity.User;
import net.suhel.registrationlogin.repository.RoleRepository;
import net.suhel.registrationlogin.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@NoArgsConstructor
@AllArgsConstructor
public class SaveUserImpl implements SaveUser {
@Autowired
    UserRepository userRepository;
@Autowired
    RoleRepository roleRepository;
@Autowired
PasswordEncoder passwordEncoder;
    @Override
    public void saveUser(UserDto userDto) {
        User user =new User();
        user.setName(userDto.getFirstName()+" "+ userDto.getLastName());
        user.setEmail(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));

     Role role=   roleRepository.findByName("ROLE_ADMIN");
        if(role==null) {
            role = checkRoleExist();
        }
        user.setRoles(Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
      return  userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAll() {
       List<User> users= userRepository.findAll();
     return  users.stream()
                .map((user -> convertToUserDto(user)))
                .collect(Collectors.toList());
    }
private UserDto convertToUserDto (User user){
        UserDto userDto =new UserDto();


      String [] str=  user.getName().split(" ");
       userDto.setFirstName(str[0]);
       userDto.setLastName(str[1]);
       userDto.setEmail(user.getEmail());
       userDto.setPassword(user.getPassword());
       userDto.setId(user.getId());
       return userDto;
}
    private Role checkRoleExist(){
        Role role=new Role();
        role.setName("ROLE_ADMIN");
        return role;
    }
}
