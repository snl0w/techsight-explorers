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

    // Injeção de dependência para o repositório de usuários
    @Autowired
    private UserRepository userRepository;

    // Injeção de dependência para o repositório de roles
    @Autowired
    private RoleRepository roleRepository;

    // Injeção de dependência para o codificador de senhas
    @Autowired
    private PasswordEncoder passwordEncoder;

    // Método para salvar um usuário no banco de dados
    @Override
    public void saveUser(UserDto userDto) {
        // Busca o papel (role) de 'USER' no banco de dados
        Role role = roleRepository.findByName(TbConstants.Roles.USER);

        // Se o papel não existir, cria um novo papel 'USER' e salva no banco
        if (role == null)
            role = roleRepository.save(new Role(TbConstants.Roles.USER));

        // Cria um novo usuário com os dados do UserDto, criptografa a senha e associa o papel 'USER'
        UserBlog user = new UserBlog(userDto.getUsername(), userDto.getEmail(), passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role));


        userRepository.save(user);
    }

    // Método para buscar um usuário no banco de dados pelo e-mail
    @Override
    public UserBlog findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
