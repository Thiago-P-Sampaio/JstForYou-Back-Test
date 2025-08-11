package mediaapp.com.just4you.Entities;


import jakarta.persistence.*;

@Entity
@Table( name = "tb_preferencia_usuario")
public class EntidadePreferencia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long preferenciaId;
    private String descricao;


    //Relacionamentos


    @ManyToOne
    @JoinColumn( name = "usuario_id")
    private EntidadeUsuario usuario;

    //

    public EntidadePreferencia() {
    }

    public EntidadePreferencia(Long preferenciaId, String descricao) {
        this.preferenciaId = preferenciaId;
        this.descricao = descricao;
    }


    public Long getPreferenciaId() {
        return preferenciaId;
    }

    public void setPreferenciaId(Long preferenciaId) {
        this.preferenciaId = preferenciaId;
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
