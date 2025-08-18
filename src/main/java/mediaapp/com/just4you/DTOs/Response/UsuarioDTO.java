package mediaapp.com.just4you.DTOs.Response;


import mediaapp.com.just4you.Entities.EntidadeUsuario;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class UsuarioDTO {

    private Long usuarioId;
    private String nome;
    private String email;
    private List<PreferenciaDTO> preferencias;
    private ListaUsuarioDTO listaUsuario;

    public UsuarioDTO() {
    }

    public UsuarioDTO(EntidadeUsuario entity) {
        this.usuarioId = entity.getUsuarioId();
        this.nome = entity.getNome();
        this.email = entity.getEmail();

        // Mapeia a lista de preferências para uma lista de PreferenciaDTO
        this.preferencias = entity.getPreferencias() != null ?
                entity.getPreferencias().stream()
                        .map(PreferenciaDTO::new) // Referência de método para o construtor
                        .collect(Collectors.toList()) : Collections.emptyList();

        // Verifica se o usuário possui uma lista antes de mapeá-la
        if (entity.getListaUsuario() != null) {
            this.listaUsuario = new ListaUsuarioDTO(entity.getListaUsuario());
        }
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PreferenciaDTO> getPreferencias() {
        return preferencias;
    }

    public void setPreferencias(List<PreferenciaDTO> preferencias) {
        this.preferencias = preferencias;
    }

    public ListaUsuarioDTO getListaUsuario() {
        return listaUsuario;
    }

    public void setListaUsuario(ListaUsuarioDTO listaUsuario) {
        this.listaUsuario = listaUsuario;
    }
}
