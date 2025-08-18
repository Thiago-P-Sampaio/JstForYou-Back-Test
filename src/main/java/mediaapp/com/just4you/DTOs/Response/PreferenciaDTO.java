package mediaapp.com.just4you.DTOs.Response;

import mediaapp.com.just4you.Entities.EntidadePreferencia;

public class PreferenciaDTO {


    private Long preferenciaId;
    private String descricao;

    public PreferenciaDTO() {
    }

    public PreferenciaDTO(EntidadePreferencia entity) {
        this.preferenciaId = entity.getPreferenciaId();
        this.descricao = entity.getDescricao();
    }

    public Long getPreferenciaId() {
        return preferenciaId;
    }

    public void setPreferenciaId(Long preferenciaId) {
        this.preferenciaId = preferenciaId;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
