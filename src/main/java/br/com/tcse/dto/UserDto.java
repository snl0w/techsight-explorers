package br.com.tcse.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {
    private Long id;

    @NotEmpty(message = "Digite o nome de usuário!")
    private String username;

    @NotEmpty(message = "Digite um email válido!")
    @Email
    private String email;

    @NotEmpty(message = "Digite uma senha válida!")
    private String password;

    @NotEmpty(message = "Confirme sua senha!")
    private String confirmPassword;
}
