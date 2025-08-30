package mediaapp.com.just4you.DTOs.Create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import mediaapp.com.just4you.Entities.EntidadePreferencia;

public class CriarPreferenciaDTO {

    @NotNull
    private Long usuarioId;

    @NotNull
    @NotBlank
    private String descricao;


    public CriarPreferenciaDTO() {
    }

    public CriarPreferenciaDTO(EntidadePreferencia entity){
        usuarioId = entity.getUsuario().getUsuarioId();
        descricao = entity.getDescricao();
    }

    public CriarPreferenciaDTO(Long usuarioId, String descricao) {
        this.usuarioId = usuarioId;
        this.descricao = descricao;
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
