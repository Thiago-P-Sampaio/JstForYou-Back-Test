package mediaapp.com.just4you.DTOs.Response;

import mediaapp.com.just4you.Entities.EntidadeConteudos;
import mediaapp.com.just4you.Entities.TipoMedia;

public record ConteudoDTO(


     Long conteudoId,
     String titulo,
     String mediaTipo,
     Long  mediaId
) {

        public ConteudoDTO(EntidadeConteudos entity){
            this(
                    entity.getConteudoId(),
                    entity.getTitulo(),
            (entity.getMediaId() != null) ? entity.getMedia().getValue() : null,
                    entity.getMediaId()
            );
        }

}
