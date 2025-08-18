package mediaapp.com.just4you.Services.domain;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Create.ConteudoParaListaDTO;
import mediaapp.com.just4you.DTOs.Create.CriarConteudoDTO;
import mediaapp.com.just4you.Entities.*;
import mediaapp.com.just4you.Repositories.ConteudosListaRepositorio;
import mediaapp.com.just4you.Repositories.ConteudosRepositorio;
import mediaapp.com.just4you.Repositories.ListaUsuarioRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ListaConteudoService {

    @Autowired
    ConteudoService conteudoService;

    @Autowired
    ConteudosRepositorio conteudosRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    ConteudosListaRepositorio conteudosListaRepositorio;

    @Autowired
    ListaUsuarioRepositorio listaUsuarioRepositorio;

    @Transactional
    public EntidadeListaUsuario adicionarConteudoLista(@RequestBody @Valid CriarConteudoDTO dto, Long usuarioId){
        boolean entidadeExiste = usuarioRepositorio.existsById(usuarioId);
        boolean conteudoExiste = conteudosRepositorio.findByMediaAndMediaId(TipoMedia.fromValue(dto.getTipoMedia()), dto.getMediaId());
        if(conteudoExiste) throw new IllegalArgumentException("Conteúdo já existe no banco!");

            if(entidadeExiste) {
                EntidadeConteudos novoConteudo = new EntidadeConteudos();
                Optional.ofNullable(dto.getTitulo())
                        .filter(s -> s.isBlank())
                        .ifPresent(novoConteudo::setTitulo);

                Optional.ofNullable(dto.getTipoMedia())
                        .filter(s -> !s.isBlank())
                        .map(TipoMedia::fromValue)
                        .ifPresent(novoConteudo::setMedia);

                novoConteudo.setMediaId(dto.getMediaId());
                conteudosRepositorio.save(novoConteudo);

            EntidadeUsuario usuarioExistente = usuarioRepositorio.findById(usuarioId).get();
            EntidadeListaUsuario listaUsuario = listaUsuarioRepositorio.findByUsuarioId(usuarioId);

            EntidadeListaConteudo listaConteudo = new EntidadeListaConteudo();

            listaConteudo.setConteudo(novoConteudo);
            listaConteudo.setLista(listaUsuario);
            listaConteudo.setId(new EntidadeListaConteudoPK(listaUsuario.getListaId(), novoConteudo.getConteudoId()));
            return conteudosListaRepositorio.save(listaConteudo);

            }





    }
}
