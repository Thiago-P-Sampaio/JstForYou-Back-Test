package mediaapp.com.just4you.Services.domain;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Create.CriarConteudoDTO;
import mediaapp.com.just4you.DTOs.Response.ConteudoDTO;
import mediaapp.com.just4you.Entities.EntidadeConteudos;
import mediaapp.com.just4you.Entities.TipoMedia;
import mediaapp.com.just4you.Repositories.ConteudosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ConteudoService {

    @Autowired
    ConteudosRepositorio conteudosRepositorio;

//    public EntidadeConteudos adicionarConteudo(@RequestBody @Valid CriarConteudoDTO dto){
//
//        boolean exists = conteudosRepositorio.findByMediaAndMediaId(TipoMedia.fromValue(dto.getTipoMedia()), dto.getMediaId());
//        if(exists) throw new IllegalArgumentException("Conteúdo já existe no banco!");
//
//        EntidadeConteudos novoConteudo = new EntidadeConteudos();
//        Optional.ofNullable(dto.getTitulo())
//                .filter(s -> s.isBlank())
//                .ifPresent(novoConteudo::setTitulo);
//
//        Optional.ofNullable(dto.getTipoMedia())
//                .filter(s -> !s.isBlank())
//                .map(TipoMedia::fromValue)
//                .ifPresent(novoConteudo::setMedia);
//
//        novoConteudo.setMediaId(dto.getMediaId());
//
//
//        return conteudosRepositorio.save(novoConteudo);
//    }

    public void deletarConteudo(Long id){
        EntidadeConteudos conteudoExistente = conteudosRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        conteudosRepositorio.deleteById(id);

    }


}
