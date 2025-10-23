package mediaapp.com.just4you.DTOs.UserAcess;

import jakarta.validation.constraints.*;
import mediaapp.com.just4you.Roles.PermissaoUsuario;
import mediaapp.com.just4you.Validators.annotations.Texto;

import java.time.Instant;
import java.time.LocalDate;

public record CadastrarDTO(

        @Texto
        @NotBlank(message = "O nome do usuário precisa ser informado!")
        String nome,
        @NotBlank(message = "O email do usuário precisa ser informado!")
        @Email
        String email,
        @NotBlank(message = "A senha do usuário precisa ser informado!")
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres!")
        String senha,
        @Past(message = "Inconsistência na data de nascimento informada")
        @NotNull(message = "É necessário informar a data de nascimento do usuário!")
        LocalDate dataNascimento,
        PermissaoUsuario role,
        Instant dataCadastro

) {
}
