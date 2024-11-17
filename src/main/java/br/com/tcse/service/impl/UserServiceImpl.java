package br.com.tcse.service.impl;

import br.com.tcse.dto.UserDto;
import br.com.tcse.model.Role;
import br.com.tcse.model.UserBlog;
import br.com.tcse.repository.RoleRepository;
import br.com.tcse.repository.UserRepository;
import br.com.tcse.service.UserService;
import br.com.tcse.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));

        UserBlog user = new UserBlog(userDto.getUsername(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role));
        userRepository.save(user);
    }

    @Override
    public UserBlog findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
