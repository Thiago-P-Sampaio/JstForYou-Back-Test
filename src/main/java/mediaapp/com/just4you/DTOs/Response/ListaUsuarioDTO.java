package mediaapp.com.just4you.DTOs.Response;

import mediaapp.com.just4you.Entities.EntidadeListaUsuario;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class ListaUsuarioDTO {

    private Long listaId;
    private List<ConteudoDTO> conteudos;

    public ListaUsuarioDTO() {
    }

    public ListaUsuarioDTO(EntidadeListaUsuario entity) {
        this.listaId = entity.getListaId();
        this.conteudos =  entity.getListaConteudos() != null ?
                entity.getListaConteudos().stream()
                        .map(listaConteudo -> new ConteudoDTO(listaConteudo.getConteudo()))
                        .collect(Collectors.toList()) : Collections.emptyList();
    }

    public Long getListaId() {
        return listaId;
    }

    public void setListaId(Long listaId) {
        this.listaId = listaId;
    }

    public List<ConteudoDTO> getConteudos() {
        return conteudos;
    }

    public void setConteudos(List<ConteudoDTO> conteudos) {
        this.conteudos = conteudos;
    }
}
