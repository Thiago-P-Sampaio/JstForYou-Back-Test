package mediaapp.com.just4you.Services.domain;


import mediaapp.com.just4you.DTOs.Create.CriarPreferenciaDTO;
import mediaapp.com.just4you.DTOs.Put.EditarPreferencia;
import mediaapp.com.just4you.DTOs.Response.PreferenciaDTO;
import mediaapp.com.just4you.Entities.EntidadePreferencia;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Exceptions.AcessoNegadoExcecao;
import mediaapp.com.just4you.Exceptions.RecursoNaoEncontradoExcecao;
import mediaapp.com.just4you.Repositories.PreferenciaRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PreferenciaService {


    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    @Autowired
    PreferenciaRepositorio preferenciaRepositorio;

    @Transactional
    public PreferenciaDTO  criarPreferencia(CriarPreferenciaDTO dto){
        EntidadeUsuario entidadeExistente = usuarioRepositorio.findById(dto.usuarioId())
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Usuário com ID " + dto.usuarioId() + " não encontrado.")); //EXCEÇÃO!!

        EntidadePreferencia novaPreferencia = new EntidadePreferencia();
        Optional.ofNullable(dto.descricao())
                .filter(s -> !s.isBlank())
                .ifPresent(novaPreferencia::setDescricao);

        novaPreferencia.setUsuario(entidadeExistente);
         preferenciaRepositorio.save(novaPreferencia);
         return new PreferenciaDTO(novaPreferencia);

    }

    @Transactional
    public void deletarPreferencia(Long id){
        EntidadePreferencia preferenciaExistente = preferenciaRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Preferencia com id: " + id + " não encontrado!"));

        preferenciaRepositorio.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PreferenciaDTO> buscarPreferenciasPorUsuario(Long id){
        if (!usuarioRepositorio.existsById(id)) {
            throw new RecursoNaoEncontradoExcecao("Usuário com ID " + id + " não encontrado.");
        }
        List<EntidadePreferencia> preferenciasDoUsuario = preferenciaRepositorio.findByUsuario_UsuarioId(id);
        return preferenciasDoUsuario.stream()
                .map(PreferenciaDTO::new)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public Page<PreferenciaDTO> listasPreferenciasPaginado(Pageable pagina){
        Page<EntidadePreferencia> entidadesPaginadas =  preferenciaRepositorio.findAll(pagina);
        return entidadesPaginadas.map(PreferenciaDTO::new);
    }

    public PreferenciaDTO editarPreferencia(EditarPreferencia dto, Long id, Long usuarioId){
        EntidadePreferencia preferencia = preferenciaRepositorio.findById(id)
                .orElseThrow(() -> new RecursoNaoEncontradoExcecao("Preferência com ID: "+ id + " Não encontrado!"));

        if(!preferencia.getUsuario().getUsuarioId().equals(usuarioId)){
            throw new AcessoNegadoExcecao("Preferência não pertence ao usuário");  ///TRATAR EXCEÇÃO!
        }

        Optional.of(dto.descricao())
                .filter(s -> !s.isBlank() && !s.equals(preferencia.getDescricao())) //VERIFICAR!
                .ifPresent(preferencia::setDescricao);

        EntidadePreferencia entidadeSalva = preferenciaRepositorio.save(preferencia);
        return new PreferenciaDTO(entidadeSalva);



    }




}
