package br.com.tcse.dto;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class RegisterDto {

    @NotEmpty(message = "O nome de usuário é obrigatório.")
    private String username;

    @NotEmpty(message = "O email é obrigatório.")
    @Email(message = "O email deve ser válido.")
    private String email;

    @NotEmpty(message = "A senha é obrigatória.")
    @Size(min = 8, message = "A senha deve ter pelo menos 8 caracteres.")
    private String password;

    @NotEmpty(message = "Confirme sua senha.")
    private String confirmPassword;
}
