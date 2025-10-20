package mediaapp.com.just4you.DTOs.Create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mediaapp.com.just4you.Entities.EntidadePreferencia;
import mediaapp.com.just4you.Validators.annotations.Texto;

public record CriarPreferenciaDTO(

        @NotNull(message = "O usuário não pode ser nulo!")
        Long usuarioId,
        @NotBlank ( message = "A descrição não pode estar em branco!")
        @Texto
        String descricao
) {

}
