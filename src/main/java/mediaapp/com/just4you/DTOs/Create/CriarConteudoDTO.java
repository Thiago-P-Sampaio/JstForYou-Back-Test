package mediaapp.com.just4you.DTOs.Create;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CriarConteudoDTO {

    @NotBlank
    @NotNull
    private String titulo;

    @NotBlank
    @NotNull
    private String tipoMedia;

    @NotBlank
    @NotNull
    private Long mediaId;

    public CriarConteudoDTO() {
    }

    public CriarConteudoDTO(String titulo, String tipoMedia, Long mediaId) {
        this.titulo = titulo;
        this.tipoMedia = tipoMedia;
        this.mediaId = mediaId;
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
}
