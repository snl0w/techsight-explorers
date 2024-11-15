package br.com.tcse.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;


@Controller
public class HomeController {

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("message", "Bem-vindo ao meu site!");
        return "home";
    }

    @GetMapping("/menu")
    public String javaTutorial() {
        return "menu";
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/forgotPassword")
    public String forgotPassword() {
        return "redefinir-senha";
    }

    @GetMapping("/members")
    public String members() {
        return "tse";
    }

}
