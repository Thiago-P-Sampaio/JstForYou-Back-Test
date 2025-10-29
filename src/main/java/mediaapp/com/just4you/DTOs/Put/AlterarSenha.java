package mediaapp.com.just4you.DTOs.Put;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record AlterarSenha(
        @NotBlank( message = "O campo deve ser preenchido!")
        String senhaAtual,
        @NotBlank( message = "Informe a nova senha!")
        @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres!")
        String novaSenha,
        @NotBlank( message = "Confirme a nova senha!")
        @Size(min = 6, message = "A senha deve conter no mínimo 6 caracteres!")
        String confirmarSenha
) {

}
