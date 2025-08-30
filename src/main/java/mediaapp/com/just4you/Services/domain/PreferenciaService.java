package mediaapp.com.just4you.Services.domain;


import jakarta.validation.Valid;
import mediaapp.com.just4you.DTOs.Create.CriarPreferenciaDTO;
import mediaapp.com.just4you.DTOs.Response.PreferenciaDTO;
import mediaapp.com.just4you.Entities.EntidadePreferencia;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Repositories.PreferenciaRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestBody;

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
    public CriarPreferenciaDTO  criarPreferencia(CriarPreferenciaDTO dto){
        EntidadeUsuario entidadeExistente = usuarioRepositorio.findById(dto.getUsuarioId())
                .orElseThrow(() -> new RuntimeException("Usuário com ID " + dto.getUsuarioId() + " não encontrado."));

        EntidadePreferencia novaPreferencia = new EntidadePreferencia();
        Optional.ofNullable(dto.getDescricao())
                .filter(s -> !s.isBlank())
                .ifPresent(novaPreferencia::setDescricao);

        novaPreferencia.setUsuario(entidadeExistente);
         preferenciaRepositorio.save(novaPreferencia);
         return new CriarPreferenciaDTO(novaPreferencia);

    }

    @Transactional
    public void deletarPreferencia(Long id){
        EntidadePreferencia preferenciaExistente = preferenciaRepositorio.findById(id)
                .orElseThrow(() -> new RuntimeException("Preferencia com id: " + id + " não encontrado!"));

        preferenciaRepositorio.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<PreferenciaDTO> buscarPreferenciasPorUsuario(Long id){
        if (!usuarioRepositorio.existsById(id)) {
            throw new RuntimeException("Usuário com ID " + id + " não encontrado.");
        }
        List<EntidadePreferencia> preferenciasDoUsuario = preferenciaRepositorio.findByUsuario_UsuarioId(id);
        return preferenciasDoUsuario.stream()
                .map(PreferenciaDTO::new)
                .collect(Collectors.toList());
    }




}
