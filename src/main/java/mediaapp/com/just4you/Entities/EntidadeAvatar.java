package mediaapp.com.just4you.Entities;


import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table (name = "tb_avatar")
public class EntidadeAvatar {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String url;


    private String descricao;

    @OneToMany(mappedBy = "avatar")
    private List<EntidadeUsuario> usuarios = new ArrayList<>();


    public EntidadeAvatar() {
    }


    public EntidadeAvatar(Long id, String url, String descricao, List<EntidadeUsuario> usuarios) {
        this.id = id;
        this.url = url;
        this.descricao = descricao;
        this.usuarios = usuarios;
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

    public List<EntidadeUsuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<EntidadeUsuario> usuarios) {
        this.usuarios = usuarios;
    }
}
