package mediaapp.com.just4you.Entities;

import jakarta.persistence.*;
import mediaapp.com.just4you.Roles.PermissaoUsuario;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.Instant;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity
@Table (name = "tb_usuario")
public class EntidadeUsuario implements UserDetails {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long usuarioId;

    @Column(nullable = false)
    private String nome;

    @Column( nullable = false)
    private String senha;

    @Column(nullable = false, unique = true)
    private String email;

    private LocalDate dataNascimento;

    @Column(updatable = false, nullable = false)
    private Instant dataCadastro;

    private PermissaoUsuario role;

    //Relacionamentos

    @OneToMany( mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EntidadePreferencia> preferencias = new ArrayList<>();

    @OneToOne(mappedBy = "usuario", cascade = CascadeType.ALL)
    private EntidadeListaUsuario listaUsuario;

    //



    public EntidadeUsuario() {
    }

    public EntidadeUsuario(Long usuarioId, String nome, String senha, String email, LocalDate dataNascimento, Instant dataCadastro, PermissaoUsuario role) {
        this.usuarioId = usuarioId;
        this.nome = nome;
        this.senha = senha;
        this.email = email;
        this.dataNascimento = dataNascimento;
        this.dataCadastro = dataCadastro;
        this.role = role;
    }


    public EntidadeUsuario(String nome, String email, String senhaCriptografada, LocalDate date, PermissaoUsuario role, Instant dataCadastro  ) {
    }

    public Long getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Long usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
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

    public void setDataCadastro(Instant dataCadastro) {
        this.dataCadastro = dataCadastro;
    }

    public List<EntidadePreferencia> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<EntidadePreferencia> preferencias) {
        this.preferencias = preferencias;
    }

    public EntidadeListaUsuario getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(EntidadeListaUsuario listaUsuario) {
        this.listaUsuario = listaUsuario;
    }

    public PermissaoUsuario getRole() {
        return role;
    }

    public void setRole(PermissaoUsuario role) {
        this.role = role;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority("ROLE_USER"));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
