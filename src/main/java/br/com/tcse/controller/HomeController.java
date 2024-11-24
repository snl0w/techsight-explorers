package br.com.tcse.controller;

import br.com.tcse.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    // Método para exibir a página inicial no endpoint /home
    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Bem-vindo ao meu site!");
        return "home";
    }

    // Método para exibir o menu no endpoint /menu
    @GetMapping("/menu")
    public String javaTutorial() {
        return "menu";
    }

    //Link para Blog
    @GetMapping("/java-history")
    public String javaHistory() {
        return "java-history";
    }

    //Link para Blog
    @GetMapping("/java-install")
    public String javaInstall() {
        return "java-install";
    }

    //Link para Blog
    @GetMapping("/eclipse-install")
    public String eclipseInstall() {
        return "eclipse-install";
    }

    //Link para Blog
    @GetMapping("/code-structure")
    public String codestructure() {
        return "code-structure";
    }


    // Método para exibir a página de membros no endpoint /members
    @GetMapping("/members")
    public String members() {
        return "members"; // Retorna a view correspondente à página de membros
    }
}
