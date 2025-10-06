package mediaapp.com.just4you.DTOs.Create;


import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import org.hibernate.validator.constraints.URL;

public class AdicionarAvatarDTO {


    private Long id;
    @URL
    @NotBlank(message = "A URL não pode estar em branco!")
    private String url;
    @NotBlank(message = "A descrição não pode estar em branco!")
    private String descricao;

    public AdicionarAvatarDTO() {
    }

    public AdicionarAvatarDTO(Long id, String url, String descricao) {
        this.id = id;
        this.url = url;
        this.descricao = descricao;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

}
