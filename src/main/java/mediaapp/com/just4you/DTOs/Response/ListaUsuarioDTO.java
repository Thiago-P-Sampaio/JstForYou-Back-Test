package mediaapp.com.just4you.DTOs.Response;

import mediaapp.com.just4you.Entities.EntidadeListaUsuario;
import mediaapp.com.just4you.Entities.EntidadeUsuario;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public record ListaUsuarioDTO(

     Long listaId,
     List<ConteudoDTO> conteudos
  )
{
    public ListaUsuarioDTO(EntidadeListaUsuario entity){
        this(
                entity.getListaId(),
                entity.getListaConteudos() != null ?
                        entity.getListaConteudos().stream()
                                .map(listaConteudo -> new ConteudoDTO(listaConteudo.getConteudo()))
                                .toList() :
                        Collections.emptyList()
        );
    }
}