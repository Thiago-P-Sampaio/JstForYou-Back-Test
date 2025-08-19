package mediaapp.com.just4you.DTOs.Response;

import mediaapp.com.just4you.Entities.EntidadeConteudos;
import mediaapp.com.just4you.Entities.TipoMedia;

public class ConteudoDTO {

    private Long conteudoId;
    private String titulo;
    private String mediaTipo;
    private Long  mediaId;

    public ConteudoDTO() {
    }

    public ConteudoDTO(EntidadeConteudos entity) {
        this.conteudoId = entity.getConteudoId();
        this.titulo = entity.getTitulo();
        this.mediaId = entity.getMediaId();
        if (entity.getMedia() != null) {
            this.mediaTipo = entity.getMedia().getValue();
        }
    }

    public Long getConteudoId() {
        return conteudoId;
    }

    public void setConteudoId(Long conteudoId) {
        this.conteudoId = conteudoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getMediaTipo() {
        return mediaTipo;
    }

    public void setMediaTipo(String meditaTipo) {
        this.mediaTipo = meditaTipo;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }
}
