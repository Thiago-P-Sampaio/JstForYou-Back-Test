package mediaapp.com.just4you.Services.domain;

import jakarta.persistence.EntityNotFoundException;

import mediaapp.com.just4you.DTOs.Create.ConteudoParaListaDTO;
import mediaapp.com.just4you.DTOs.Response.ListaUsuarioDTO;
import mediaapp.com.just4you.Entities.*;
import mediaapp.com.just4you.Exceptions.RecursoExistenteExcecao;
import mediaapp.com.just4you.Exceptions.RecursoNaoEncontradoExcecao;
import mediaapp.com.just4you.Repositories.ConteudosListaRepositorio;
import mediaapp.com.just4you.Repositories.ConteudosRepositorio;
import mediaapp.com.just4you.Repositories.ListaUsuarioRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Usuário não encontrado com o ID: " + usuarioId)); /// EXCEÇÃO

        // 2. Buscar a lista do usuário.
        EntidadeListaUsuario listaUsuario = listaUsuarioRepositorio.findByUsuario_UsuarioId(usuario.getUsuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Lista não encontrada para o usuário com ID: " + usuarioId)); /// EXCEÇÃO;


        TipoMedia tipoMedia = TipoMedia.fromValue(dto.tipoMedia());
        EntidadeConteudos conteudo = conteudosRepositorio.findByMediaIdAndMedia(dto.mediaId(), tipoMedia)
                .orElseGet(() -> {

                    EntidadeConteudos novoConteudo = new EntidadeConteudos();
                    novoConteudo.setMediaId(dto.mediaId());
                    novoConteudo.setMedia(tipoMedia);


                    Optional.ofNullable(dto.titulo())
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
            novaAssociacao.setAvaliacao(dto.avaliacao());
            conteudosListaRepositorio.save(novaAssociacao);
        } else {
            new RecursoExistenteExcecao(" Já existe o conteúdo com ID: " + dto.mediaId() + " na lista com ID: " + listaUsuario.getListaId());
        }

    }

    @Transactional
    public void removerConteudoLista(ConteudoParaListaDTO dto, Long usuarioId) {

        // 1. Validar se o usuário existe
        EntidadeUsuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Usuário não encontrado com o ID: " + usuarioId));

        // 2. Buscar a lista do usuário
        EntidadeListaUsuario listaUsuario = listaUsuarioRepositorio.findByUsuario_UsuarioId(usuario.getUsuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Lista não encontrada para o usuário com ID: " + usuarioId));

        // 3. Buscar o conteúdo
        TipoMedia tipoMedia = TipoMedia.fromValue(dto.tipoMedia());
        EntidadeConteudos conteudo = conteudosRepositorio.findByMediaIdAndMedia(dto.mediaId(), tipoMedia)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao(  /// EXCEÇÃO
                        "Conteúdo não encontrado na base para o mediaId: " + dto.mediaId() + " e tipo: " + tipoMedia
                ));

        // 4. Criar a chave composta (listaId + conteudoId)
        EntidadeListaConteudoPK id = new EntidadeListaConteudoPK(listaUsuario.getListaId(), conteudo.getConteudoId());

        // 5. Verificar se a associação existe
        boolean associacaoExiste = conteudosListaRepositorio.existsById(id);
        if (!associacaoExiste) {
            throw new EntityNotFoundException("O conteúdo não está presente na lista do usuário."); ///  VERIFICAR
        }

        // 6. Remover associação
        conteudosListaRepositorio.deleteById(id);
    }

    @Transactional(readOnly = true)
    public ListaUsuarioDTO buscarListaPorUsuario(Long usuarioId) {
        // 1. Validar se o usuário existe
        EntidadeUsuario usuario = usuarioRepositorio.findById(usuarioId)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Usuário não encontrado com ID: " + usuarioId));

        // 2. Buscar a lista do usuário
        EntidadeListaUsuario listaUsuario = listaUsuarioRepositorio.findByUsuario_UsuarioId(usuario.getUsuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Lista não encontrada para o usuário com ID: " + usuarioId));

        // 3. Retornar DTO
        return new ListaUsuarioDTO(listaUsuario);
    }
}
