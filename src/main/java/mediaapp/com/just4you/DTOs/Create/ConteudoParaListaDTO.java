package mediaapp.com.just4you.DTOs.Create;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ConteudoParaListaDTO {

    @NotBlank
    @NotNull
    private String titulo;

    @NotBlank
    @NotNull
    private String tipoMedia;

    @NotBlank
    @NotNull
    private Long mediaId;

    @NotBlank
    @NotNull
    private Boolean avaliacao;


    public ConteudoParaListaDTO(String titulo, String tipoMedia, Long mediaId, Boolean avaliacao) {
        this.titulo = titulo;
        this.tipoMedia = tipoMedia;
        this.mediaId = mediaId;
        this.avaliacao = avaliacao;
    }

    public ConteudoParaListaDTO() {
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getTipoMedia() {
        return tipoMedia;
    }

    public void setTipoMedia(String tipoMedia) {
        this.tipoMedia = tipoMedia;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }

    public Boolean getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Boolean avaliacao) {
        this.avaliacao = avaliacao;
    }
}
