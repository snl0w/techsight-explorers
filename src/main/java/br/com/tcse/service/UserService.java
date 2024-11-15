package br.com.tcse.service;


import br.com.tcse.model.UserBlog;
import br.com.tcse.repository.UserRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserBlog userBlog = userRepository.findByEmail(email);

        if(userBlog != null) {
            var loadUser = User.withUsername(userBlog.getUsername())
                    .password(userBlog.getPassword())
                    .roles(userBlog.getRole())
                    .build();

            return loadUser;
        }

        throw new UsernameNotFoundException("Usuário não encontrado com o email: " + email);

    }
}