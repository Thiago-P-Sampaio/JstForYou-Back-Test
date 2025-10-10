package mediaapp.com.just4you.DTOs.Create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConteudoParaListaDTO(

        @NotBlank(message = "O título não pode estar vazio!")
         String titulo,

        @NotBlank( message = "O tipo da mídia('tv'/'movie') deve ser declarado!")
        String tipoMedia,

        @NotNull ( message = "O ID da mídia deve ser informado!")
         Long mediaId,

        Boolean avaliacao

) {

}
