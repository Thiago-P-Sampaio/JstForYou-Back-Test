package mediaapp.com.just4you.DTOs.Put;

import jakarta.validation.constraints.Email;
import mediaapp.com.just4you.Validators.annotations.Texto;

public record EditarUsuario(

        @Texto
        String nome,
        @Email
        String email,
        Long avatar_id
) {

}
