package br.com.tcse.controller;

import br.com.tcse.dto.UserDto;
import br.com.tcse.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("/api/users")
public class UserController {

    private UserService userService;

    //Add User
    @PostMapping
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto){
        UserDto savedUser = userService.createUser(userDto);
        return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
    }

    //Get User
    @GetMapping("{id}")
    public ResponseEntity<UserDto> getUserById(@PathVariable("id") Long id){
        UserDto userDto = userService.getUserById(id);
        return ResponseEntity.ok(userDto);
    }

    //Get all Users
    public ResponseEntity<List<UserDto>> getAllUsers(){
        List<UserDto> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    //Edit User
    @PutMapping("{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable("id") Long id,
                                              @RequestBody UserDto updatedUser){
        UserDto userDto = userService.updateuser(id, updatedUser);
        return ResponseEntity.ok(userDto);
    }

    //Delete User
    @DeleteMapping("{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id")Long id){
        userService.deleteUser(id);
        return ResponseEntity.ok("Usuário deletado com sucesso!");
    }

}
