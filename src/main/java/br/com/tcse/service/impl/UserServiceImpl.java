package br.com.tcse.service.impl;

import br.com.tcse.dto.UserDto;
import br.com.tcse.entity.User;
import br.com.tcse.mapper.UserMapper;
import br.com.tcse.repository.UserRepository;
import br.com.tcse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {

        User user = UserMapper.mapToUser(userDto);
        User savedUser = userRepository.save(user);
        return UserMapper.mapToUserDto(savedUser);
    }
}
