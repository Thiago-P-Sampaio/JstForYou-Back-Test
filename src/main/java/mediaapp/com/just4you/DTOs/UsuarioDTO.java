package mediaapp.com.just4you.DTOs;

import mediaapp.com.just4you.Entities.EntidadeListaUsuario;
import mediaapp.com.just4you.Entities.EntidadeUsuario;

import java.time.Instant;
import java.time.LocalDate;

public class UsuarioDTO {

    private Long id;
    private String nome;
    private String email;
    private LocalDate dataNascimento;
    private Instant dataCadastro;
    private EntidadeListaUsuario entidadeListaUsuario;

    public UsuarioDTO() {
    }

    public UsuarioDTO(Long id, String nome, String email, LocalDate dataNascimento, Instant dataCadastro) {
        this.id = id;
        this.nome = nome;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
    }

    public UsuarioDTO(EntidadeUsuario entity){
        id = entity.getUsuarioId();
        nome = entity.getNome();
        email = entity.getEmail();
        dataCadastro = entity.getDataCadastro();
        dataNascimento = entity.getDataNascimento();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public Instant getDataCadastro() {
        return dataCadastro;
    }

    public void setDataCadastro(Instant dataCriacao) {
        this.dataCadastro = dataCriacao;
    }
}
