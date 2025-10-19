package mediaapp.com.just4you.DTOs.UserAcess;

import jakarta.validation.constraints.NotBlank;

public record RedefinirSenha(

        @NotBlank
        String senha,
        @NotBlank
        String confirmarSenha,
        @NotBlank
        String token
) {
}
