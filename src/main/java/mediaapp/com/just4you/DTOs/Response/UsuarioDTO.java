package mediaapp.com.just4you.DTOs.Response;


import mediaapp.com.just4you.Entities.EntidadeUsuario;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record UsuarioDTO(

        Long usuarioId,
        String nome,
        String email,
        List<PreferenciaDTO> preferencias,
        ListaUsuarioDTO listaUsuario,
        AvatarDTO avatar
) {

    public UsuarioDTO(EntidadeUsuario entity) {
        this(
                entity.getUsuarioId(),
                entity.getNome(),
                entity.getEmail(),
                entity.getPreferencias() != null ?
                        entity.getPreferencias().stream().map(PreferenciaDTO::new).toList() :
                        Collections.emptyList(),
                entity.getListaUsuario() != null ? new ListaUsuarioDTO(entity.getListaUsuario()) : null,
                entity.getAvatar() != null ? new AvatarDTO(entity.getAvatar()) : null
        );
    }
}
