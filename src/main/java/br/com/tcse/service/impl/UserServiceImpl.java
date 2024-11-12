package br.com.tcse.service.impl;

import br.com.tcse.dto.UserDto;
import br.com.tcse.entity.User;
import br.com.tcse.exception.ResourceNotFoundException;
import br.com.tcse.mapper.UserMapper;
import br.com.tcse.repository.UserRepository;
import br.com.tcse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

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

    @Override
    public UserDto getUserById(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Usuário não existe: " +id));

        return UserMapper.mapToUserDto(user);
    }

    @Override
    public List<UserDto> getAllUsers(){
        List<User> users = userRepository.findAll();

        return users.stream().map((user) -> UserMapper.mapToUserDto(user))
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateuser(Long id, UserDto updatedUser) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não existe usuário com esse id: " +id)
        );


        user.setUsername(updatedUser.getUsername());
        user.setEmail(updatedUser.getEmail());

        User updatedUserObj = userRepository.save(user);

        return UserMapper.mapToUserDto(updatedUserObj);
    }

    @Override
    public void deleteUser(Long id) {

        User user = userRepository.findById(id).orElseThrow(
                () -> new ResourceNotFoundException("Não existe usuário com esse id: " +id)
        );

        userRepository.deleteById(id);
    }

    @Override
    public User findByEmail(String email) {
        return null;
    }

    @Override
    public User save() {
        return null;
    }


}