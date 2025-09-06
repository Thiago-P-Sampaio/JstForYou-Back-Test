package mediaapp.com.just4you.Services.domain;

import jakarta.persistence.EntityNotFoundException;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Create.ConteudoParaListaDTO;
import mediaapp.com.just4you.DTOs.Create.CriarConteudoDTO;
import mediaapp.com.just4you.DTOs.Response.ListaUsuarioDTO;
import mediaapp.com.just4you.Entities.*;
import mediaapp.com.just4you.Repositories.ConteudosListaRepositorio;
import mediaapp.com.just4you.Repositories.ConteudosRepositorio;
import mediaapp.com.just4you.Repositories.ListaUsuarioRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ListaConteudoService {

    @Autowired
    ConteudosRepositorio conteudosRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    ConteudosListaRepositorio conteudosListaRepositorio;

    @Autowired
    ListaUsuarioRepositorio listaUsuarioRepositorio;

    @Transactional
    public void adicionarConteudoLista(ConteudoParaListaDTO dto, Long usuarioId) {

        // 1. Validar se o usuário existe. Se não, lançar uma exceção.
        EntidadeUsuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + usuarioId));

        // 2. Buscar a lista do usuário.
        EntidadeListaUsuario listaUsuario = listaUsuarioRepositorio.findByUsuario_UsuarioId(usuario.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Lista não encontrada para o usuário com ID: " + usuarioId));


        TipoMedia tipoMedia = TipoMedia.fromValue(dto.getTipoMedia());
        EntidadeConteudos conteudo = conteudosRepositorio.findByMediaIdAndMedia(dto.getMediaId(), tipoMedia)
                .orElseGet(() -> {

                    EntidadeConteudos novoConteudo = new EntidadeConteudos();
                    novoConteudo.setMediaId(dto.getMediaId());
                    novoConteudo.setMedia(tipoMedia);


                    Optional.ofNullable(dto.getTitulo())
                            .filter(titulo -> !titulo.isBlank())
                            .ifPresent(novoConteudo::setTitulo);


                    return conteudosRepositorio.save(novoConteudo);
                });

        EntidadeListaConteudoPK id = new EntidadeListaConteudoPK(listaUsuario.getListaId(), conteudo.getConteudoId());


        boolean associacaoExiste = conteudosListaRepositorio.existsById(id);

        if (!associacaoExiste) {

            EntidadeListaConteudo novaAssociacao = new EntidadeListaConteudo();
            novaAssociacao.setId(id);
            novaAssociacao.setLista(listaUsuario);
            novaAssociacao.setConteudo(conteudo);
            novaAssociacao.setAvaliacao(dto.getAvaliacao());
            conteudosListaRepositorio.save(novaAssociacao);
        }

    }

    @Transactional
    public void removerConteudoLista(ConteudoParaListaDTO dto, Long usuarioId) {

        // 1. Validar se o usuário existe
        EntidadeUsuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com o ID: " + usuarioId));

        // 2. Buscar a lista do usuário
        EntidadeListaUsuario listaUsuario = listaUsuarioRepositorio.findByUsuario_UsuarioId(usuario.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Lista não encontrada para o usuário com ID: " + usuarioId));

        // 3. Buscar o conteúdo
        TipoMedia tipoMedia = TipoMedia.fromValue(dto.getTipoMedia());
        EntidadeConteudos conteudo = conteudosRepositorio.findByMediaIdAndMedia(dto.getMediaId(), tipoMedia)
                .orElseThrow(() -> new EntityNotFoundException(
                        "Conteúdo não encontrado na base para o mediaId: " + dto.getMediaId() + " e tipo: " + tipoMedia
                ));

        // 4. Criar a chave composta (listaId + conteudoId)
        EntidadeListaConteudoPK id = new EntidadeListaConteudoPK(listaUsuario.getListaId(), conteudo.getConteudoId());

        // 5. Verificar se a associação existe
        boolean associacaoExiste = conteudosListaRepositorio.existsById(id);
        if (!associacaoExiste) {
            throw new EntityNotFoundException("O conteúdo não está presente na lista do usuário.");
        }

        // 6. Remover associação
        conteudosListaRepositorio.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ListaUsuarioDTO buscarListaPorUsuario(Long usuarioId) {
        // 1. Validar se o usuário existe
        EntidadeUsuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + usuarioId));

        // 2. Buscar a lista do usuário
        EntidadeListaUsuario listaUsuario = listaUsuarioRepositorio.findByUsuario_UsuarioId(usuario.getUsuarioId())
                .orElseThrow(() -> new EntityNotFoundException("Lista não encontrada para o usuário com ID: " + usuarioId));

        // 3. Retornar DTO
        return new ListaUsuarioDTO(listaUsuario);
    }
}
