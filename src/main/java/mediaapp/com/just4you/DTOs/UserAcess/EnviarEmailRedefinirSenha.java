package mediaapp.com.just4you.DTOs.UserAcess;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record EnviarEmailRedefinirSenha(
        @Email
        @NotBlank
        String email
) {
}
