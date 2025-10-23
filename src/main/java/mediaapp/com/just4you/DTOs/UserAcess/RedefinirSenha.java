package mediaapp.com.just4you.DTOs.UserAcess;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record RedefinirSenha(

        @NotBlank
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres!")
        String senha,
        @NotBlank
        @Size(min = 6, message = "A senha deve ter no mínimo 6 caracteres!")
        String confirmarSenha,
        @NotBlank
        String token
) {
}
