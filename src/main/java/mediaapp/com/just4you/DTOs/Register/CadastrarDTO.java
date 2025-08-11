package mediaapp.com.just4you.DTOs;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mediaapp.com.just4you.Roles.PermissaoUsuario;

import java.time.Instant;
import java.time.LocalDate;
import java.util.Date;

public record CadastrarDTO(String nome,
                           String email,
                           String senha,
                           LocalDate dataNascimento,
                           PermissaoUsuario role,
                           Instant dataCadastro

) {
}
