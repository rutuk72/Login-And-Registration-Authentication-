package net.suhel.registrationlogin.service;

import net.suhel.registrationlogin.dto.UserDto;
import net.suhel.registrationlogin.entity.User;

import java.util.List;

public interface SaveUser{
    void saveUser(UserDto userDto);

    User findByEmail(String email);

    List<UserDto> findAll();
}
