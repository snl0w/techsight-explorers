package br.com.tcse.controller;

import br.com.tcse.dto.UserDto;
import br.com.tcse.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserRepository userRepository;

    @GetMapping("/register")
    public String register(Model model){
        UserDto registerDto = new UserDto();
        model.addAttribute(registerDto);
        return "register";
    }


}