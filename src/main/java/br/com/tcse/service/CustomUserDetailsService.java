package br.com.tcse.service;

import br.com.tcse.model.UserBlog;
import br.com.tcse.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import java.util.stream.Collectors;


public class CustomUserDetailsService implements UserDetailsService {

    // Injeção de dependência para o repositório de usuários
    @Autowired
    private UserRepository userRepository;

    // Implementação do método loadUserByUsername que carrega os detalhes do usuário a partir do e-mail
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        // Busca o usuário no banco de dados pelo e-mail
        UserBlog user = userRepository.findByEmail(email);

        // Se o usuário for encontrado, cria e retorna um objeto UserDetails
        if (user != null) {
            return new org.springframework.security.core.userdetails.User(user.getEmail() // O nome de usuário será o e-mail
                    , user.getPassword() // A senha do usuário (já deve estar criptografada)
                    , user.getRoles().stream() // Converte a lista de roles do usuário para um formato esperado pelo Spring Security
                    .map((role) -> new SimpleGrantedAuthority(role.getName())) // Cria um objeto SimpleGrantedAuthority para cada role
                    .collect(Collectors.toList())); // Coleta as autoridades em uma lista
        } else {
            // Se o usuário não for encontrado, lança uma exceção com uma mensagem de erro
            throw new UsernameNotFoundException("Invalid email or password");
        }
    }
}
