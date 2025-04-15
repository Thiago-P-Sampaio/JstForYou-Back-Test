package mediaapp.com.just4you.Services;

import mediaapp.com.just4you.Entities.EntidadeUsuario;
import mediaapp.com.just4you.Repositories.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {

    @Autowired
    UsuarioRepositorio usuarioRepositorio;


    public List<EntidadeUsuario> buscarUsuarios(){
        return usuarioRepositorio.findAll();
    }

    public EntidadeUsuario buscarUsuario(Long id){
    return usuarioRepositorio.findById(id)
            .orElseThrow(() -> new RuntimeException("Usuário não encontrado"));

    }
}
