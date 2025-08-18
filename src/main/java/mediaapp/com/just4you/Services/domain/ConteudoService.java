package mediaapp.com.just4you.Services.domain;

import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Response.ConteudoDTO;
import mediaapp.com.just4you.Entities.EntidadeConteudos;
import mediaapp.com.just4you.Repositories.ConteudosRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;

@Service
public class ConteudoService {

    @Autowired
    ConteudosRepositorio conteudosRepositorio;

    public EntidadeConteudos adicionarConteudo(@RequestBody @Valid ConteudoDTO dto){
//
         // Implementar uma validação aqui para não lançar conteúdos duplicados!
         //
        EntidadeConteudos novoConteudo = new EntidadeConteudos();
        Optional.ofNullable(dto.getTitulo())
                .filter(s -> s.isBlank())
                .ifPresent(novoConteudo::setTitulo);

        return conteudosRepositorio.save(novoConteudo);
    }

    public void deletarConteudo(Long id){
        EntidadeConteudos conteudoExistente = conteudosRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Filme não encontrado"));

        conteudosRepositorio.deleteById(id);

    }


}
