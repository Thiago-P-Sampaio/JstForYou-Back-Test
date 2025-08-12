package mediaapp.com.just4you.DTOs.Create;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class CriarConteudoDTO {

    @NotBlank
    @NotNull
    private String titulo;

    public CriarConteudoDTO() {
    }

    public CriarConteudoDTO(String titulo) {
        this.titulo = titulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }
}
