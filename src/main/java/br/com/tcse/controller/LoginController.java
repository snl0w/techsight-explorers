package br.com.tcse.controller;


import br.com.tcse.dto.UserDto;
import br.com.tcse.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LoginController {

    // Injeta o serviço de usuários para manipulação de dados do usuário
    @Autowired
    private UserService userService;

    // Método para exibir o formulário de login no endpoint /login
    @RequestMapping("/login")
    public String loginForm() {
        return "login";
    }

    // Método para exibir o formulário de registro no endpoint /register
    @GetMapping("/register")
    public String registrationForm(Model model) {
        UserDto user = new UserDto();
        model.addAttribute("user", user);
        return "register";
    }

    //OBS: Tem muitos ifs, não é uma boa prática
    // Método para processar o registro de novos usuários no endpoint /register
    @PostMapping("/register")
    public String registration(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {

        // Verifica se algum campo obrigatório está vazio
        if (userDto.getUsername() == null || userDto.getUsername().trim().isEmpty()) {
            result.rejectValue("username", null, "O nome de usuário é obrigatório!"); // Adiciona um erro de validação
        }
        if (userDto.getEmail() == null || userDto.getEmail().trim().isEmpty()) {
            result.rejectValue("email", null, "O email é obrigatório!");
        }
        if (userDto.getPassword() == null || userDto.getPassword().trim().isEmpty()) {
            result.rejectValue("password", null, "A senha é obrigatória!");
        }
        if (userDto.getConfirmPassword() == null || userDto.getConfirmPassword().trim().isEmpty()) {
            result.rejectValue("confirmPassword", null, "A confirmação de senha é obrigatória!");
        }

        // Verifica se o e-mail já está registrado no banco de dados
        if (!result.hasFieldErrors("email") && userService.findUserByEmail(userDto.getEmail()) != null) {
            result.rejectValue("email", null, "Esse email já está registrado!");
        }

        // Compara a senha e a confirmação de senha
        if (!result.hasFieldErrors("password") && !result.hasFieldErrors("confirmPassword") &&
                !userDto.getPassword().equals(userDto.getConfirmPassword())) {
            result.rejectValue("confirmPassword", null, "As senhas não são iguais!");
        }

        // Verifica se existem erros de validação
        if (result.hasErrors()) {
            model.addAttribute("user", userDto); // Adiciona o DTO de volta ao modelo para exibir os erros
            return "register"; // Retorna à página de registro com as mensagens de erro
        }

        // Salva o novo usuário no banco de dados
        userService.saveUser(userDto);

        // Redireciona para a página de sucesso
        return "redirect:/register?success";
    }

    // Método de exemplo para funcionalidade "Esqueceu a Senha" (a implementar)
}
