package br.com.tcse.service;

import br.com.tcse.dto.UserDto; // Importa o DTO que contém os dados do usuário a serem transferidos entre camadas
import br.com.tcse.model.User; // Importa a classe de modelo que representa o usuário no banco de dados
import org.springframework.stereotype.Service; // Importa a anotação Service para indicar que essa classe é um serviço de negócio
import org.springframework.validation.BindingResult;

import java.util.List;

// A interface UserService define os métodos que o serviço de usuário deve implementar. Ela é usada para encapsular a lógica de negócios relacionada a usuários.
@Service // Anotação que marca esta interface como um serviço, indicando que pode ser injetada e utilizada em outros componentes do Spring
public interface UserService {
    // Método para salvar um novo usuário no sistema, recebendo um DTO com os dados do usuário
    void saveUser(UserDto userDto);

    // Método para encontrar um usuário pelo seu e-mail, retornando um objeto UserBlog que representa o usuário no banco de dados
    User findUserByEmail(String email);

    User findUserByUsername(String username);

    boolean validarEmail(String email);

    String validarString(String linha);

    void validarRegistro(UserDto userDto, BindingResult result);
}
