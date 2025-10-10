package mediaapp.com.just4you.DTOs.Create;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CriarConteudoDTO(

        @NotBlank(message = "O título da obra deve ser informado!")
        String titulo,

        @NotBlank(message = "O tipo da mídia(movie/tv) deve ser informado!")
        String tipoMedia,

        @NotNull(message = "O ID da mídia deve ser informado!")
         Long mediaId
) {

}
