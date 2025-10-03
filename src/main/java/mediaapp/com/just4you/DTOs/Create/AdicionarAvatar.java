package mediaapp.com.just4you.DTOs.Create;




public class AdicionarAvatar {


    private Long id;
    private String url;
    private String descricao;

    public AdicionarAvatar() {
    }

    public AdicionarAvatar(Long id, String url, String descricao) {
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
