package mediaapp.com.just4you.Entities;

import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table (name = "tb_conteudos")
public class EntidadeConteudos {

    @Id
    @GeneratedValue(strategy =  GenerationType.IDENTITY)
    private Long conteudoId;
    private String titulo;
    @Enumerated(EnumType.STRING)
    private TipoMedia media;
    private Long mediaId;

    //Relacionamentos

    @OneToMany( mappedBy = "conteudo",  cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<EntidadeListaConteudo> listaConteudos;


    public EntidadeConteudos() {
    }

    public EntidadeConteudos(Long conteudoId, String titulo,  TipoMedia media, Long mediaId) {
        this.conteudoId = conteudoId;
        this.titulo = titulo;
        this.media = media;
        this.mediaId = mediaId;
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

    public TipoMedia getMedia() {
        return media;
    }

    public void setMedia(TipoMedia media) {
        this.media = media;
    }

    public Long getMediaId() {
        return mediaId;
    }

    public void setMediaId(Long mediaId) {
        this.mediaId = mediaId;
    }
}
