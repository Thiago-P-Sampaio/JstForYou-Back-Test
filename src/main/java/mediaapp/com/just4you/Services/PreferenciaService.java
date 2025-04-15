package mediaapp.com.just4you.Services;

import mediaapp.com.just4you.DTOs.AddPreferenciaDTO;
import mediaapp.com.just4you.DTOs.PreferenciaDTO;
import mediaapp.com.just4you.Entities.EntidadePreferencias;
import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Repositories.PreferenciaRepositorio;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PreferenciaService {

    @Autowired
    PreferenciaRepositorio preferenciaRepositorio;

    @Autowired
    UsuarioRepositorio usuarioRepositorio;

    public PreferenciaDTO addPreferencia( Long id, PreferenciaDTO dto){
            Optional<EntidadeUsuario> user =  usuarioRepositorio.findById(id);
            if(user.isPresent()){
                EntidadePreferencias preferencias = new EntidadePreferencias();
                preferencias.setDescricao(dto.getDescricao());
                EntidadeUsuario usuario = usuarioRepositorio.getReferenceById(id);
                preferencias.setUsuario(usuario);

                preferencias = preferenciaRepositorio.save(preferencias);
                return  new PreferenciaDTO(preferencias);
            }
            else  {
                return null;
            }

    }
}
