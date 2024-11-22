package br.com.tcse.service.impl;

import br.com.tcse.dto.UserDto;
import br.com.tcse.model.Role;
import br.com.tcse.model.User;
import br.com.tcse.repository.RoleRepository;
import br.com.tcse.repository.UserRepository;
import br.com.tcse.service.UserService;
import br.com.tcse.util.TbConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;

import java.util.Arrays;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
        // Validar o email
        if (!validarEmail(userDto.getEmail())) {
            throw new IllegalArgumentException("O email fornecido não é válido.");
        }

        // Validar o username
        String usernameValido = validarString(userDto.getUsername());
        if (usernameValido.isEmpty()) {
            throw new IllegalArgumentException("O nome de usuário contém caracteres inválidos.");
        }

        // Buscar o papel 'USER'
        Role role = roleRepository.findByName(TbConstants.Roles.USER);
        if (role == null) {
            role = roleRepository.save(new Role(TbConstants.Roles.USER));
        }

        // Criar o usuário
        User user = new User(
                usernameValido,
                userDto.getEmail(),
                passwordEncoder.encode(userDto.getPassword()),
                Arrays.asList(role)
        );

        userRepository.save(user);
    }

    // Método para buscar usuário pelo email
    @Override
    public User findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    // Método para buscar usuário pelo username
    @Override
    public User findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Método para validar o email usando regex
    public boolean validarEmail(String email) {
        // Regex para validação de email
        Pattern pattern = Pattern.compile(
                "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                        + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$",
                Pattern.CASE_INSENSITIVE
        );

        return pattern.matcher(email).matches();
    }

    //Método para validar uma String
    public String validarString(String linha) {
        // Regex para validar strings com letras e números, sem caracteres especiais
        Pattern pattern = Pattern.compile("^[A-Za-z0-9]+$");
        Matcher matcher = pattern.matcher(linha);
        if (matcher.matches()) {
            return linha; // Retorna a string se for válida
        }
        return ""; // Retorna vazio se for inválida
    }

    public void validarRegistro(UserDto userDto, BindingResult result) {
        // Valida o email
        if (!validarEmail(userDto.getEmail())) {
            result.rejectValue("email", null, "O email fornecido não é válido.");
        } else if (findUserByEmail(userDto.getEmail()) != null) {
            result.rejectValue("email", null, "Esse email já está registrado!");
        }

        // Valida o nome de usuário
        if (validarString(userDto.getUsername()).isEmpty()) {
            result.rejectValue("username", null, "O nome de usuário contém caracteres inválidos.");
        } else if (findUserByUsername(userDto.getUsername()) != null) {
            result.rejectValue("username", null, "Esse nome de usuário já está em uso!");
        }

        // Valida as senhas
        if (!userDto.getPassword().equals(userDto.getConfirmPassword())) {
            result.rejectValue("confirmPassword", null, "As senhas não são iguais!");
        }
    }
}
