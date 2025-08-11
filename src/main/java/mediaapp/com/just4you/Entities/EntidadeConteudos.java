package mediaapp.com.just4you.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table (name = "tb_conteudos")
public class EntidadeConteudos {

    @Id
    private Long conteudoId;
    private String titulo;

    //Relacionamentos

    @OneToMany( mappedBy = "conteudo",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EntidadeListaConteudo> listaConteudos;


    public EntidadeConteudos() {
    }

    public EntidadeConteudos(Long conteudoId, String titulo) {
        this.conteudoId = conteudoId;
        this.titulo = titulo;
    }

    public Long getConteudoId() {
        return conteudoId;
    }

    public void setConteudoId(Long conteudoId) {
        this.conteudoId = conteudoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Set<EntidadeListaConteudo> getListaConteudos() {
        return listaConteudos;
    }

    public void setListaConteudos(Set<EntidadeListaConteudo> listaConteudos) {
        this.listaConteudos = listaConteudos;
    }
}
