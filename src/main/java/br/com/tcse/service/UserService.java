package br.com.tcse.service;

import br.com.tcse.dto.UserDto;
import br.com.tcse.entity.User;

import java.util.List;

public interface UserService {
    UserDto createUser(UserDto userDto);
    UserDto getUserById(Long id);
    List<UserDto> getAllUsers();
    UserDto updateuser(Long id, UserDto updatedUser);
    void deleteUser(Long id);
    User findByEmail(String email);
    User save();
}