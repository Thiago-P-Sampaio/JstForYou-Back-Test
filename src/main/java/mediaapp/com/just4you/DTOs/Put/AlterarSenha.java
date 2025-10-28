package mediaapp.com.just4you.DTOs.Put;

import jakarta.validation.constraints.NotBlank;

public record AlterarSenha(
        @NotBlank
        String senhaAtual,
        @NotBlank
        String novaSenha,
        @NotBlank
        String confirmarSenha
) {

}
