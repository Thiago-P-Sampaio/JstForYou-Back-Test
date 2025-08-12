package mediaapp.com.just4you.DTOs.UserAcess;

import mediaapp.com.just4you.Roles.PermissaoUsuario;

import java.time.Instant;
import java.time.LocalDate;

public record CadastrarDTO(String nome,
                           String email,
                           String senha,
                           LocalDate dataNascimento,
                           PermissaoUsuario role,
                           Instant dataCadastro

) {
}
