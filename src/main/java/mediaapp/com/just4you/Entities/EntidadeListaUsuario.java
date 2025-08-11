package mediaapp.com.just4you.Entities;


import jakarta.persistence.*;

import java.util.Set;

@Entity
@Table (name = "tb_lista_usuario")
public class EntidadeListaUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long listaId;


    //Relacionamentos

    @OneToOne
    @JoinColumn( name = "usuario_id")
    private EntidadeUsuario usuario;

    @OneToMany(mappedBy = "lista", cascade = CascadeType.ALL, orphanRemoval = true )
    private Set<EntidadeListaConteudo> listaConteudos;

    //

    public EntidadeListaUsuario() {
    }

    public EntidadeListaUsuario(Long listaId) {
        this.listaId = listaId;
    }

    public Long getListaId() {
        return listaId;
    }

    public void setListaId(Long listaId) {
        this.listaId = listaId;
    }

    public EntidadeUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(EntidadeUsuario usuario) {
        this.usuario = usuario;
    }
}
