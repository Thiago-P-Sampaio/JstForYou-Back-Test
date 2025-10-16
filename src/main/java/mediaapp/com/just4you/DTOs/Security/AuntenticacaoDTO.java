package mediaapp.com.just4you.DTOs.Security;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

public record AuntenticacaoDTO(

        @Email
        @NotBlank
        String email,
        @NotBlank
        String senha) {
}
