package mediaapp.com.just4you.DTOs.Create;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mediaapp.com.just4you.Validators.annotations.Texto;
import org.hibernate.validator.constraints.URL;

public record AdicionarAvatarDTO(

        @URL
        @NotBlank(message = "A URL não pode estar em branco!")
        String url,
        @Texto
        @NotBlank(message = "A descrição não pode estar em branco!")
        String descricao
) {

}
