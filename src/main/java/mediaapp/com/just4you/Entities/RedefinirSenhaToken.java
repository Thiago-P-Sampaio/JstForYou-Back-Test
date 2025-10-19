package mediaapp.com.just4you.Entities;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
public class RedefinirSenhaToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String token;

    @OneToOne(targetEntity = EntidadeUsuario.class, fetch = FetchType.EAGER)
    @JoinColumn(nullable = false, name = "usuario_id")
    private EntidadeUsuario usuario;


    @Column(nullable = false)
    private LocalDateTime expiracao;


    public RedefinirSenhaToken(Long id, String token, EntidadeUsuario usuario, LocalDateTime expiracao) {
        this.id = id;
        this.token = token;
        this.usuario = usuario;
        this.expiracao = expiracao;
    }

    public RedefinirSenhaToken(String token, EntidadeUsuario usuario, LocalDateTime expiracao){
        this.token = token;
        this.usuario = usuario;
        this.expiracao = expiracao;
    }

    public RedefinirSenhaToken() {
    }

    public boolean Expirado(){
        return LocalDateTime.now().isAfter(this.expiracao);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public EntidadeUsuario getUsuario() {
        return usuario;
    }

    public void setUsuario(EntidadeUsuario usuario) {
        this.usuario = usuario;
    }

    public LocalDateTime getExpiracao() {
        return expiracao;
    }

    public void setExpiracao(LocalDateTime expiracao) {
        this.expiracao = expiracao;
    }

}
