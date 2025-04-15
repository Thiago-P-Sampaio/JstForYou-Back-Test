package mediaapp.com.just4you.DTOs;

import mediaapp.com.just4you.Entities.EntidadePreferencias;

public class PreferenciaDTO {

    private Long id;
    private Long usuarioId;
    private String descricao;

    public PreferenciaDTO(Long id, Long usuarioId, String descricao) {
        this.id = id;
        this.usuarioId = usuarioId;
        this.descricao = descricao;
    }

    public PreferenciaDTO() {
    }

    public PreferenciaDTO(EntidadePreferencias entity){
        id = entity.getId();
        usuarioId = entity.getUsuario().getUsuarioId();
        descricao = entity.getDescricao();

    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
