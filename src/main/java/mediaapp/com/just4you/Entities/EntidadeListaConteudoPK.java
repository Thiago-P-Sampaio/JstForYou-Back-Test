package mediaapp.com.just4you.Entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

import java.util.Objects;

@Embeddable
public class EntidadeListaConteudoPK {


    @Column(name = "lista_id")
    private Long listaId;

    @Column(name = "conteudo_id")
    private Long conteudoId;


    public EntidadeListaConteudoPK() {
    }

    public EntidadeListaConteudoPK(Long listaId, Long conteudoId) {
        this.listaId = listaId;
        this.conteudoId = conteudoId;
    }


    public Long getListaId() {
        return listaId;
    }

    public void setListaId(Long listaId) {
        this.listaId = listaId;
    }

    public Long getConteudoId() {
        return conteudoId;
    }

    public void setConteudoId(Long conteudoId) {
        this.conteudoId = conteudoId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EntidadeListaConteudoPK that = (EntidadeListaConteudoPK) o;
        return Objects.equals(listaId, that.listaId) &&
                Objects.equals(conteudoId, that.conteudoId);
    }
    @Override
    public int hashCode() {
        return Objects.hash(listaId, conteudoId);
    }

}
