package mediaapp.com.just4you.DTOs.Response;


import mediaapp.com.just4you.Entities.EntidadeAvatar;
import mediaapp.com.just4you.Entities.EntidadeUsuario;

public class AvatarDTO {

    private Long id;
    private String url;
    private String descricao;

    public AvatarDTO() {
    }

    public AvatarDTO(EntidadeAvatar entity) {
        this.id = entity.getId();
        this.url = entity.getUrl();
        this.descricao = entity.getDescricao();
    }

    public AvatarDTO(Long id, String url, String descricao) {
        this.id = id;
        this.url = url;
        this.descricao = descricao;
    }



    public Long getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }

    public String getDescricao() {
        return descricao;
    }
}
