package mediaapp.com.just4you.Services.domain;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Create.CriarConteudoDTO;
import mediaapp.com.just4you.DTOs.Put.EditarConteudo;
import mediaapp.com.just4you.DTOs.Response.ConteudoDTO;
import mediaapp.com.just4you.Entities.EntidadeConteudos;
import mediaapp.com.just4you.Entities.TipoMedia;
import mediaapp.com.just4you.Exceptions.RecursoExistenteExcecao;
import mediaapp.com.just4you.Exceptions.RecursoNaoEncontradoExcecao;
import mediaapp.com.just4you.Repositories.ConteudosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ConteudoService {

    @Autowired
    ConteudosRepositorio conteudosRepositorio;

    public ConteudoDTO adicionarConteudo(@RequestBody @Valid CriarConteudoDTO dto){

        Optional<EntidadeConteudos> exists = conteudosRepositorio.findByMediaIdAndMedia(dto.mediaId(), TipoMedia.fromValue(dto.tipoMedia()));
        if(exists.isPresent()) throw new RecursoExistenteExcecao("Conteúdo com ID(midia): " + dto.mediaId() + " e mídia: " + dto.tipoMedia() + " já existe!");  /// EXCEÇÃO

        EntidadeConteudos novoConteudo = new EntidadeConteudos();
        Optional.ofNullable(dto.titulo())
                .filter(s -> !s.isBlank())
                .ifPresent(novoConteudo::setTitulo);

        Optional.ofNullable(dto.tipoMedia())
                .filter(s -> !s.isBlank())
                .map(TipoMedia::fromValue)
                .ifPresent(novoConteudo::setMedia);

        novoConteudo.setMediaId(dto.mediaId());


       EntidadeConteudos entidadeSalva = conteudosRepositorio.save(novoConteudo);
       return new ConteudoDTO(entidadeSalva);
    }

    public void deletarConteudo(Long id){
        EntidadeConteudos conteudoExistente = conteudosRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Conteúdo com ID: " + id + " não encontrado!"));  /// EXCEÇÃO

        conteudosRepositorio.deleteById(id);

    }
    public Page<ConteudoDTO> listarConteudos(Pageable paginacao){
        Page<EntidadeConteudos> conteudos = conteudosRepositorio.findAll(paginacao);
        return conteudos.map(ConteudoDTO::new);
    }


    public ConteudoDTO buscarConteudoPorId(Long id){
        EntidadeConteudos conteudo = conteudosRepositorio.findById(id)
                .orElseThrow(() ->  new RecursoNaoEncontradoExcecao("Conteúdo com ID: " + id + " não encontrado!"));  /// EXCEÇÃO

        return new ConteudoDTO(conteudo);
    }

    public ConteudoDTO buscarPorMediaComId(Long mediaId, String media){
       TipoMedia tipoMedia = TipoMedia.fromValue(media);
        Optional<EntidadeConteudos> conteudoOptional = conteudosRepositorio.findByMediaIdAndMedia( mediaId, tipoMedia);

        return conteudoOptional
                .map(ConteudoDTO::new)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Conteudo(" + media +") e ID: " + mediaId + " não encontrado!")); /// EXCEÇÃO
    }


    public ConteudoDTO editarConteudo(EditarConteudo dto, Long id){
        EntidadeConteudos conteudo = conteudosRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Conteúdo com ID: " + id + " não encontrado"));

        Optional.ofNullable(dto.titulo())
                .filter(s -> !s.isBlank() || !dto.titulo().equals(conteudo.getTitulo()))
                .ifPresent(conteudo::setTitulo);

        Optional.ofNullable(dto.mediaId())
                .filter(s -> !s.equals(conteudo.getMediaId()))
                .ifPresent(conteudo::setMediaId);

        Optional.ofNullable(dto.mediaTipo())
                .filter(s -> !s.isBlank())
                .map(TipoMedia::fromValue)
                .filter(novoTipo -> !novoTipo.equals(conteudo.getMedia()))
                .ifPresent(conteudo::setMedia);

        EntidadeConteudos entidadeSalva = conteudosRepositorio.save(conteudo);
        return new ConteudoDTO(entidadeSalva);
    }


}
