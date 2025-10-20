package mediaapp.com.just4you.DTOs.Put;

import jakarta.validation.constraints.NotBlank;
import mediaapp.com.just4you.Validators.annotations.Texto;

public record EditarPreferencia(

        @Texto
        @NotBlank
        String descricao
) {
}
