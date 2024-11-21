package br.com.tcse.controller;

import br.com.tcse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Método para exibir a página inicial no endpoint /home
    @GetMapping("/home")
    public String home(Model model) {
        model.addAttribute("message", "Bem-vindo ao meu site!");
        return "home";
    }

    // Método para exibir o menu no endpoint /menu
    @GetMapping("/menu")
    public String javaTutorial() {
        return "menu";
    }

    // Método para exibir a página de membros no endpoint /members
    @GetMapping("/members")
    public String members() {
        return "members"; // Retorna a view correspondente à página de membros
    }
}
