package mediaapp.com.just4you.DTOs.Response;

import mediaapp.com.just4you.Entities.EntidadeConteudos;

public class ConteudoDTO {

    private Long conteudoId;
    private String titulo;

    public ConteudoDTO() {
    }

    public ConteudoDTO(EntidadeConteudos entity) {
        this.conteudoId = entity.getConteudoId();
        this.titulo = entity.getTitulo();
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
}
