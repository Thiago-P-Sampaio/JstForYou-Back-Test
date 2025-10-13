package mediaapp.com.just4you.DTOs.UserAcess;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import mediaapp.com.just4you.Roles.PermissaoUsuario;

import java.time.Instant;
import java.time.LocalDate;

public record CadastrarDTO(

        @NotBlank(message = "O nome do usuário precisa ser informado!")
        String nome,
        @NotBlank(message = "O email do usuário precisa ser informado!")
        @Email
        String email,
        @NotBlank(message = "A senha do usuário precisa ser informado!")
        String senha,
        @Past(message = "Inconsistência na data de nascimento informada")
        @NotNull(message = "É necessário informar a data de nascimento do usuário!")
        LocalDate dataNascimento,
        PermissaoUsuario role,
        Instant dataCadastro

) {
}
