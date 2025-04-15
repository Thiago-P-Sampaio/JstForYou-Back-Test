package mediaapp.com.just4you.Entities;

import jakarta.persistence.*;
import org.yaml.snakeyaml.events.Event;

@Entity
@Table (name = "tb_preferencia_usuario")
public class EntidadePreferencias {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String descricao;

    @ManyToOne
    @JoinColumn(name = "usuarioId")
    private EntidadeUsuario usuario;

    public EntidadePreferencias(Long id, String descricao, EntidadeUsuario usuario) {
        this.id = id;
        this.descricao = descricao;
        this.usuario = usuario;
    }

    public EntidadePreferencias() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public EntidadeUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(EntidadeUsuario usuario) {
        this.usuario = usuario;
    }

}
