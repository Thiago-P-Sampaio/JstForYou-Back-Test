package mediaapp.com.just4you.Entities;


import jakarta.persistence.*;

@Entity
@Table (name =  "tb_lista_conteudo")
public class EntidadeListaConteudo {

    @EmbeddedId
    private EntidadeListaConteudoPK id;

    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("listaId")
    @JoinColumn(name = "lista_id")
    private EntidadeListaUsuario lista;


    @ManyToOne(fetch = FetchType.LAZY)
    @MapsId("conteudoId")
    @JoinColumn(name = "conteudo_id")
    private EntidadeConteudos conteudo;


    private Boolean avaliacao;

    public EntidadeListaConteudo() {
    }


    public EntidadeListaConteudo(EntidadeListaConteudoPK id, EntidadeListaUsuario lista, EntidadeConteudos conteudo, Boolean avaliacao) {
        this.id = id;
        this.lista = lista;
        this.conteudo = conteudo;
        this.avaliacao = avaliacao;
    }

    public EntidadeListaConteudoPK getId() {
        return id;
    }

    public void setId(EntidadeListaConteudoPK id) {
        this.id = id;
    }

    public EntidadeListaUsuario getLista() {
        return lista;
    }

    public void setLista(EntidadeListaUsuario lista) {
        this.lista = lista;
    }

    public EntidadeConteudos getConteudo() {
        return conteudo;
    }

    public void setConteudo(EntidadeConteudos conteudo) {
        this.conteudo = conteudo;
    }

    public Boolean getAvaliacao() {
        return avaliacao;
    }

    public void setAvaliacao(Boolean avaliacao) {
        this.avaliacao = avaliacao;
    }

}
