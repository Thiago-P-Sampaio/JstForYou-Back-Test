package mediaapp.com.just4you.DTOs.Create;

public class ConteudoParaListaDTO {


    private Long conteudoId;
    private Boolean avaliacao;

    public ConteudoParaListaDTO() {
    }

    public ConteudoParaListaDTO(Long conteudoId, Boolean avaliacao) {
        this.conteudoId = conteudoId;
        this.avaliacao = avaliacao;
    }

    public Long getConteudoId() {
        return conteudoId;
    }

    public void setConteudoId(Long conteudoId) {
        this.conteudoId = conteudoId;
    }

    public Boolean getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Boolean avaliacao) {
        this.avaliacao = avaliacao;
    }
}
