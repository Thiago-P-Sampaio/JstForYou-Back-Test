package mediaapp.com.just4you.DTOs.Create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CriarPreferenciaDTO {

    @NotNull
    @NotBlank
    private Long usuarioId;

    @NotNull
    @NotBlank
    private String descricao;


    public CriarPreferenciaDTO() {
    }

    public CriarPreferenciaDTO(Long id, String descricao) {
        this.usuarioId = id;
        this.descricao = descricao;
    }

    public Long getId() {
        return usuarioId;
    }

    public void setId(Long id) {
        this.usuarioId = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}
