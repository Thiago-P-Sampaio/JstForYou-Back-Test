package mediaapp.com.just4you.DTOs.Response;


import mediaapp.com.just4you.Entities.EntidadeAvatar;
import mediaapp.com.just4you.Entities.EntidadeUsuario;

public record AvatarDTO(

         Long id,
         String url,
         String descricao
) {

    public AvatarDTO(EntidadeAvatar entity){
        this(
                entity.getId(),
                entity.getUrl(),
                entity.getUrl()
        );
    }
    
}
