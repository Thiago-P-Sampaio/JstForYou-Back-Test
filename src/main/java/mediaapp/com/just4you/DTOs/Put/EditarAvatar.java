package mediaapp.com.just4you.DTOs.Put;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mediaapp.com.just4you.Validators.annotations.Texto;
import org.hibernate.validator.constraints.URL;

public record EditarAvatar(

        Long id,
        @URL
        String url,
        String descricao
) {
}
