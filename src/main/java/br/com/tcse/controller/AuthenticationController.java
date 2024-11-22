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
public class AuthenticationController {
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

    @PostMapping("/register")
    public String registration(
            @Valid @ModelAttribute("user") UserDto userDto,
            BindingResult result,
            Model model) {

        // Realiza todas as validações com o serviço
        userService.validarRegistro(userDto, result);

        // Verifica se existem erros de validação
        if (result.hasErrors()) {
            model.addAttribute("user", userDto);
            return "register";
        }

        // Salva o novo usuário no banco de dados
        userService.saveUser(userDto);

        // Redireciona para a página de sucesso
        return "redirect:/register?success";
    }

    // Método de exemplo para funcionalidade "Esqueceu a Senha" (a implementar)
}
