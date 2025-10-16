package mediaapp.com.just4you.DTOs.Put;

import mediaapp.com.just4you.Validators.annotations.Texto;

public record EditarConteudo(


        String titulo,
        @Texto
        String mediaTipo,
        Long  mediaId
) {
}
