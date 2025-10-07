package mediaapp.com.just4you.DTOs.Response;

import mediaapp.com.just4you.Entities.EntidadePreferencia;

public record PreferenciaDTO(

    Long preferenciaId,
    String descricao
    )
{
    public PreferenciaDTO(EntidadePreferencia entity){
        this(
                entity.getPreferenciaId(),
                entity.getDescricao()
        );
    }

}
