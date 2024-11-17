package br.com.tcse.service;


import br.com.tcse.dto.UserDto;
import br.com.tcse.model.UserBlog;
import br.com.tcse.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public interface UserService{
    void saveUser(UserDto userDto);
    UserBlog findUserByEmail(String email);
}